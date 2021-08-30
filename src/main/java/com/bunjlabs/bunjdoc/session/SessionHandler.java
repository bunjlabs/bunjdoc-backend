package com.bunjlabs.bunjdoc.session;

import com.bunjlabs.bunjdoc.session.messages.AuthMessage;
import com.bunjlabs.bunjdoc.session.messages.AuthTokenMessage;
import com.bunjlabs.bunjdoc.session.messages.EchoMessage;

public interface SessionHandler {

    void onAuthentication(AuthMessage message);

    void onTokenAuthentication(AuthTokenMessage message);

    void onEcho(EchoMessage message);
}
