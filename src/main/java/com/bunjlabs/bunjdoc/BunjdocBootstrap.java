package com.bunjlabs.bunjdoc;

import com.bunjlabs.fuga.context.FugaBoot;
import com.bunjlabs.fuga.inject.Configuration;
import com.bunjlabs.fuga.logging.LoggingUnitBuilder;
import com.bunjlabs.fuga.settings.SettingsUnitBuilder;
import com.bunjlabs.fuga.settings.source.LocalFilesSettingsSource;
import com.bunjlabs.bunjdoc.database.MongoUnit;
import com.bunjlabs.bunjdoc.handlers.AppHandler;
import com.bunjlabs.bunjdoc.network.NetworkSupportUnit;
import com.bunjlabs.bunjdoc.websocket.WebSocketHandler;
import com.bunjlabs.bunjdoc.websocket.server.NettyWebSocketServerUnit;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BunjdocBootstrap {

    public static void main(String[] args) {
        new BunjdocBootstrap().start();
    }

    private void start() {
        var context = FugaBoot.start(this::configure);
        var injector = context.getInjector();
        var app = injector.getInstance(BunjdocApplication.class);

        app.start();
    }

    private void configure(Configuration c) {
        c.install(this::configureLogging);
        c.install(this::configureSettings);
        c.install(this::configureDatabase);
        c.install(this::configureWebSocket);
        c.install(this::configureJackson);
        c.install(this::configureApplication);
    }

    private void configureLogging(Configuration c) {
        c.install(new LoggingUnitBuilder().build());
    }

    private void configureSettings(Configuration c) {
        c.install(new SettingsUnitBuilder()
                .withSettingsSources(new LocalFilesSettingsSource(".", "settings.yaml"))
                .build());
    }

    private void configureDatabase(Configuration c) {
        c.install(new MongoUnit());
    }

    private void configureWebSocket(Configuration c) {
        c.install(new NetworkSupportUnit());
        c.install(new NettyWebSocketServerUnit());
    }

    private void configureJackson(Configuration c) {
        c.bind(ObjectMapper.class).toInstance(new ObjectMapper());
    }

    private void configureApplication(Configuration c) {
        c.bind(BunjdocServerSettings.class).auto();
        c.bind(WebSocketHandler.class).to(AppHandler.class);
        c.bind(AppHandler.class).auto();
        c.bind(BunjdocApplication.class).auto();
    }

}
