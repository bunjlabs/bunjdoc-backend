package com.bunjlabs.bunjdoc.database;

import com.bunjlabs.fuga.inject.Inject;
import com.bunjlabs.fuga.inject.Provider;
import com.mongodb.client.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

public class DatastoreProvider implements Provider<Datastore> {
    private final MongoClient mongoClient;
    private final MongoSettings settings;

    @Inject
    public DatastoreProvider(MongoClient mongoClient, MongoSettings settings) {
        this.mongoClient = mongoClient;
        this.settings = settings;
    }

    @Override
    public Datastore get() {
        var datastore = Morphia.createDatastore(mongoClient, settings.database());
        datastore.getMapper().mapPackage("com.bunjlabs.notable.model");
        datastore.ensureIndexes();

        return datastore;
    }
}
