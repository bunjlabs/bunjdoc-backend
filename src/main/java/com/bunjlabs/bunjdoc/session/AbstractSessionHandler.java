package com.bunjlabs.bunjdoc.session;

import com.bunjlabs.bunjdoc.session.messages.AuthMessage;
import com.bunjlabs.bunjdoc.session.messages.AuthTokenMessage;
import com.bunjlabs.bunjdoc.session.messages.EchoMessage;
import com.bunjlabs.bunjdoc.session.messages.Message;

public abstract class AbstractSessionHandler implements SessionHandler {

    @Override
    public void onAuthentication(AuthMessage message) {
        unhandledMessage(message);
    }

    public void onTokenAuthentication(AuthTokenMessage message) {
        unhandledMessage(message);
    }

    public void onEcho(EchoMessage message) {
        unhandledMessage(message);
    }

    public abstract void unhandledMessage(Message message);
}
