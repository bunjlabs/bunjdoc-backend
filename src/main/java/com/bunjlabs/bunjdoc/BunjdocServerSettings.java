package com.bunjlabs.bunjdoc;

import fuga.settings.Settings;

@Settings("websocket")
public interface BunjdocServerSettings {

    String host();
    int port();
}
