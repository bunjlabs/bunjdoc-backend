package com.bunjlabs.bunjdoc.model;

import dev.morphia.annotations.Reference;

import java.sql.Timestamp;

public class UserAccess {
    private Timestamp createdAt;

    @Reference
    private User user;
    private Level level;

    public enum Level {
        OWNER, EDITOR, VIEWER
    }
}
