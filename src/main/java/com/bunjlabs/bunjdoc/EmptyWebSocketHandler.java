package com.bunjlabs.bunjdoc;

import com.bunjlabs.bunjdoc.websocket.WebSocket;
import com.bunjlabs.bunjdoc.websocket.WebSocketHandler;

import java.nio.ByteBuffer;

public abstract class EmptyWebSocketHandler implements WebSocketHandler {
    @Override
    public void onOpen(WebSocket socket) {

    }

    @Override
    public void onClose(WebSocket socket, int code, String reason, boolean remote) {

    }

    @Override
    public void onMessage(WebSocket socket, String message) {

    }

    @Override
    public void onMessage(WebSocket socket, ByteBuffer bytes) {

    }

    @Override
    public void onError(WebSocket socket, Throwable ex) {

    }

    @Override
    public void onStart() {

    }
}
