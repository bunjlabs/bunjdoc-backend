package com.bunjlabs.bunjdoc.session;

import com.bunjlabs.fuga.inject.Configuration;
import com.bunjlabs.fuga.inject.Inject;
import com.bunjlabs.fuga.inject.Injector;
import com.bunjlabs.bunjdoc.handlers.AppHandler;
import com.bunjlabs.bunjdoc.session.messages.Message;
import org.slf4j.Logger;

public class Session {

    private final Injector injector;
    private final SwitchableSessionHandler sessionHandler;
    private final AppHandler appHandler;

    @Inject
    public Session(Logger log, Injector injector, AppHandler appHandler) {
        this.injector = injector.createChildInjector(this::configureSession);
        this.sessionHandler = new SwitchableSessionHandler(injector.getInstance(AuthSessionHandler.class));
        this.appHandler = appHandler;
    }

    private void configureSession(Configuration c) {
        c.bind(Session.class).toInstance(this);

        c.bind(AuthSessionHandler.class).auto();
    }

    public Injector getInjector() {
        return injector;
    }

    public SwitchableSessionHandler getHandler() {
        return sessionHandler;
    }

    public void sendMessage(Message message) {
        appHandler.send(message);
    }
}
