package com.bunjlabs.bunjdoc.session;

import fuga.inject.Inject;
import com.bunjlabs.bunjdoc.model.User;
import com.bunjlabs.bunjdoc.model.UserToken;
import com.bunjlabs.bunjdoc.session.messages.AuthMessage;
import com.bunjlabs.bunjdoc.session.messages.AuthTokenMessage;
import com.bunjlabs.bunjdoc.session.messages.ErrorMessage;
import com.bunjlabs.bunjdoc.session.messages.Message;
import dev.morphia.Datastore;
import dev.morphia.query.experimental.filters.Filters;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class AuthSessionHandler extends AbstractSessionHandler {

    private final Logger log;
    private final Session session;
    private final Datastore datastore;

    @Inject
    public AuthSessionHandler(Logger log, Session session, Datastore datastore) {
        this.log = log;
        this.session = session;
        this.datastore = datastore;
    }

    @Override
    public void onAuthentication(AuthMessage message) {
        var user = datastore.find(User.class).filter(Filters.eq("email", message.getEmail())).first();

        if (user == null) {
            session.sendMessage(new ErrorMessage("invalid email or password"));
            return;
        }

        var encryptedPassword = DigestUtils.sha3_256Hex(message.getPassword() + user.getPasswordPepper());
        if (!encryptedPassword.equals(user.getPasswordHash())) {
            session.sendMessage(new ErrorMessage("invalid email or password"));
            return;
        }

        var token = DigestUtils.sha3_256Hex(RandomStringUtils.randomAscii(256) + System.currentTimeMillis());
        var userToken = new UserToken(Timestamp.from(Instant.now()), token);
        var userTokenList = user.getTokens();
        userTokenList.add(userToken);

        while (userTokenList.size() > 10) {
            userTokenList.remove(0);
        }

        datastore.save(user);

        session.sendMessage(new AuthTokenMessage(user.getEmail(), token));
        session.getHandler().switchHandler(session.getInjector().getInstance(ApplicationSessionHandler.class));
    }

    @Override
    public void onTokenAuthentication(AuthTokenMessage message) {
        var user = datastore.find(User.class).filter(Filters.eq("email", message.getEmail())).first();

        if (user == null) {
            session.sendMessage(new ErrorMessage("invalid email or token"));
            return;
        }

        var now = Instant.now();
        var token = user.getTokens().stream().filter(t -> t.getToken().equals(message.getToken())).findAny();
        if (token.isEmpty() || token.get().getCreatedAt().toInstant().plus(1, ChronoUnit.MONTHS).isBefore(now)) {
            session.sendMessage(new ErrorMessage("invalid email or token"));
            return;
        }

        token.get().setCreatedAt(Timestamp.from(now));
        datastore.save(user);

        session.sendMessage(new AuthTokenMessage(user.getEmail(), message.getToken()));
        session.getHandler().switchHandler(session.getInjector().getInstance(ApplicationSessionHandler.class));
    }

    @Override
    public void unhandledMessage(Message message) {
        log.debug("Invalid auth request");
    }
}
