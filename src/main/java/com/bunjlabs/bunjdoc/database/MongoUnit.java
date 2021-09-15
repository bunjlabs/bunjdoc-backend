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

package com.bunjlabs.bunjdoc.database;

import fuga.inject.Configuration;
import fuga.inject.Singleton;
import fuga.inject.Unit;
import com.mongodb.client.MongoClient;
import dev.morphia.Datastore;

public class MongoUnit implements Unit {

    @Override
    public void setup(Configuration c) {
        c.bind(MongoSettings.class);
        c.bind(MongoClientProvider.class);
        c.bind(DatastoreProvider.class);

        c.bind(MongoClient.class).toProvider(MongoClientProvider.class).in(Singleton.class);
        c.bind(Datastore.class).toProvider(DatastoreProvider.class).in(Singleton.class);
    }
}
