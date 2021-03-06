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

package com.bunjlabs.bunjdoc.network;

import fuga.inject.Configuration;
import fuga.inject.Singleton;
import fuga.inject.Unit;

public class NetworkSupportUnit implements Unit {

    @Override
    public void setup(Configuration c) {
        c.bind(EventLoopGroupSettings.class);

        c.bind(DefaultEventLoopGroupManager.class).in(Singleton.class);
        c.bind(EventLoopGroupManager.class).to(DefaultEventLoopGroupManager.class);

        c.bind(DefaultConnectionRegistry.class).in(Singleton.class);
        c.bind(ConnectionRegistry.class).to(DefaultConnectionRegistry.class);
    }
}
