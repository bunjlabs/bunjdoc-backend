package com.bunjlabs.bunjdoc;

import com.bunjlabs.fuga.settings.Settings;

@Settings("websocket")
public interface BunjdocServerSettings {

    String host();
    int port();
}
