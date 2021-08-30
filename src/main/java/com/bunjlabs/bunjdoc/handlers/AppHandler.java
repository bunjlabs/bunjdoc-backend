package com.bunjlabs.bunjdoc.handlers;

import com.bunjlabs.fuga.inject.Configuration;
import com.bunjlabs.fuga.inject.Inject;
import com.bunjlabs.fuga.inject.Injector;
import com.bunjlabs.bunjdoc.EmptyWebSocketHandler;
import com.bunjlabs.bunjdoc.session.Session;
import com.bunjlabs.bunjdoc.session.messages.AuthMessage;
import com.bunjlabs.bunjdoc.session.messages.Message;
import com.bunjlabs.bunjdoc.websocket.WebSocket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

public class AppHandler extends EmptyWebSocketHandler {

    private final Logger log;
    private final ObjectMapper mapper;
    private final Injector injector;

    private Session session;
    private WebSocket socket;

    @Inject
    public AppHandler(Logger log, ObjectMapper mapper, Injector injector) {
        this.log = log;
        this.mapper = mapper;
        this.injector = injector.createChildInjector(this::configureHandler);
    }

    private void configureHandler(Configuration c) {
        c.bind(AppHandler.class).toInstance(this);
    }

    @Override
    public void onStart() {
        log.debug("Handler created");

        session = injector.getInstance(Session.class);
    }

    @Override
    public void onOpen(WebSocket socket) {
        log.debug("New connection from {}", socket.getRemoteSocketAddress());

        this.socket = socket;
    }

    @Override
    public void onClose(WebSocket socket, int code, String reason, boolean remote) {
        log.debug("Connection {} closed: {}", socket.getRemoteSocketAddress(), reason);
    }

    @Override
    public void onMessage(WebSocket socket, String rawMessage) {
        try {
            var message = mapper.readValue(rawMessage, Message.class);

            var handler = session.getHandler();
            if (message instanceof AuthMessage) {
                handler.onAuthentication((AuthMessage) message);
            }

        } catch (JsonProcessingException e) {
            log.error("Error while parsing a message", e);
        }
    }

    public void send(Message message) {
        try {
            var json = mapper.writeValueAsString(message);

            if (socket != null) {
                socket.send(json);
            }
        } catch (JsonProcessingException e) {
            log.error("Error while serializing a message", e);
        }
    }
}
