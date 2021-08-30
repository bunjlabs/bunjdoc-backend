package com.bunjlabs.bunjdoc.model;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

import java.sql.Timestamp;
import java.util.List;

@Entity()
public class User {
    @Id
    private ObjectId id;
    private Timestamp createdAt;

    private String userName;

    @Indexed
    private String email;

    private String passwordHash;
    private String passwordPepper;

    private List<UserToken> tokens;

    public User() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordPepper() {
        return passwordPepper;
    }

    public void setPasswordPepper(String passwordPepper) {
        this.passwordPepper = passwordPepper;
    }

    public List<UserToken> getTokens() {
        return tokens;
    }

    public void setTokens(List<UserToken> tokens) {
        this.tokens = tokens;
    }
}
