package com.bunjlabs.bunjdoc.session;

import com.bunjlabs.bunjdoc.session.messages.AuthMessage;
import com.bunjlabs.bunjdoc.session.messages.AuthTokenMessage;
import com.bunjlabs.bunjdoc.session.messages.EchoMessage;

public class SwitchableSessionHandler implements SessionHandler {
    private SessionHandler handler;

    public SwitchableSessionHandler(SessionHandler handler) {
        this.handler = handler;
    }

    public void switchHandler(SessionHandler handler) {
        this.handler = handler;
    }

    @Override
    public void onAuthentication(AuthMessage message) {
        this.handler.onAuthentication(message);
    }

    @Override
    public void onTokenAuthentication(AuthTokenMessage message) {
        this.handler.onTokenAuthentication(message);

    }

    @Override
    public void onEcho(EchoMessage message) {
        this.handler.onEcho(message);
    }
}
