package com.bunjlabs.bunjdoc.session;

import com.bunjlabs.fuga.inject.Inject;
import com.bunjlabs.bunjdoc.session.messages.EchoMessage;
import com.bunjlabs.bunjdoc.session.messages.Message;
import dev.morphia.Datastore;
import org.slf4j.Logger;

public class ApplicationSessionHandler extends AbstractSessionHandler {

    private final Logger log;
    private final Session session;
    private final Datastore datastore;

    @Inject
    public ApplicationSessionHandler(Logger log, Session session, Datastore datastore) {
        this.log = log;
        this.session = session;
        this.datastore = datastore;
    }

    @Override
    public void onEcho(EchoMessage message) {
        session.sendMessage(message);
    }

    @Override
    public void unhandledMessage(Message message) {

    }
}
