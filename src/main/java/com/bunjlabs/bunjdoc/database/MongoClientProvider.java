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

import com.bunjlabs.fuga.inject.Inject;
import com.bunjlabs.fuga.inject.Provider;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoClientProvider implements Provider<MongoClient> {

    private final MongoSettings settings;

    @Inject
    public MongoClientProvider(MongoSettings settings) {
        this.settings = settings;
    }

    @Override
    public MongoClient get() {
        var mongoUri = new MongoClientURI(settings.uri());
        return MongoClients.create(settings.uri());
    }
}
