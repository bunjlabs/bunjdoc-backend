package com.bunjlabs.bunjdoc.model;

import java.sql.Timestamp;

public class UserToken {

    private Timestamp createdAt;
    private String token;

    public UserToken() {
    }

    public UserToken(Timestamp createdAt, String token) {
        this.createdAt = createdAt;
        this.token = token;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
