/*
 * Copyright 2019 Bunjlabs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bunjlabs.bunjdoc.websocket.server.netty;

import fuga.inject.Inject;
import com.bunjlabs.bunjdoc.network.ConnectionRegistry;
import com.bunjlabs.bunjdoc.network.EventLoopGroupManager;
import com.bunjlabs.bunjdoc.websocket.server.WebSocketHandlerFactory;
import com.bunjlabs.bunjdoc.websocket.server.WebSocketServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;

import java.net.SocketAddress;

public class NettyWebSocketServer implements WebSocketServer {

    private final Logger logger;
    private final EventLoopGroupManager loopGroupManager;
    private final ConnectionRegistry connectionRegistry;

    @Inject
    public NettyWebSocketServer(Logger logger, EventLoopGroupManager loopGroupManager, ConnectionRegistry connectionRegistry) {
        this.logger = logger;
        this.loopGroupManager = loopGroupManager;
        this.connectionRegistry = connectionRegistry;
    }

    @Override
    public void start(SocketAddress socketAddress, WebSocketHandlerFactory handlerFactory) {
        var workerGroup = loopGroupManager.getEventLoopGroup();

        var b = new ServerBootstrap();
        b.group(workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new NettyWebSocketServerInitializer(handlerFactory, connectionRegistry));

        try {
            b.bind(socketAddress).sync();
        } catch (InterruptedException e) {
            logger.error("Unable to start netty websocket server", e);
        }
    }
}
