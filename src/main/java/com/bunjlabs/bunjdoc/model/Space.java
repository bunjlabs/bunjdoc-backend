package com.bunjlabs.bunjdoc.model;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

import java.util.List;

@Entity()
public class Space {
    @Id
    private ObjectId id;

    @Indexed
    private String name;
    private String description;

    private List<UserAccess> accesses;

    private List<Page> pages;
}
