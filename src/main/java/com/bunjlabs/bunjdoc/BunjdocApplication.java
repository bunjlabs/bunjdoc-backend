package com.bunjlabs.bunjdoc;


import fuga.inject.Inject;
import fuga.inject.Injector;
import com.bunjlabs.bunjdoc.websocket.WebSocketHandler;
import com.bunjlabs.bunjdoc.websocket.server.WebSocketServer;
import dev.morphia.Datastore;
import org.slf4j.Logger;

import java.net.InetSocketAddress;

public class BunjdocApplication {

    private final Logger log;
    private final Datastore datastore;
    private final WebSocketServer ws;
    private final BunjdocServerSettings settings;
    private final Injector injector;

    @Inject
    public BunjdocApplication(Logger log, Datastore datastore, WebSocketServer ws, BunjdocServerSettings settings, Injector injector) {
        this.log = log;
        this.datastore = datastore;
        this.ws = ws;
        this.settings = settings;
        this.injector = injector;
    }

    public void start() {
        log.info("Starting notable server");

        var bindAddress = new InetSocketAddress(settings.host(), settings.port());
        ws.start(bindAddress, () -> injector.getInstance(WebSocketHandler.class));

        log.info("Started on {}", bindAddress);
    }
}
