package com.contractor.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "session_token", schema = "public", catalog = "contractor")
public class SessionToken {
    private Integer sessionTokenId;
    private Integer userId;
    private String sessionToken;
    private Timestamp created;
    private Timestamp validity;

    @Id
    @Column(name = "session_token_id", nullable = false)
    public Integer getSessionTokenId() {
        return sessionTokenId;
    }

    public void setSessionTokenId(Integer sessionTokenId) {
        this.sessionTokenId = sessionTokenId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "session_token", nullable = false, length = 32)
    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    @Basic
    @Column(name = "created", nullable = true)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "validity", nullable = true)
    public Timestamp getValidity() {
        return validity;
    }

    public void setValidity(Timestamp validity) {
        this.validity = validity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionToken that = (SessionToken) o;

        if (sessionTokenId != null ? !sessionTokenId.equals(that.sessionTokenId) : that.sessionTokenId != null)
            return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (sessionToken != null ? !sessionToken.equals(that.sessionToken) : that.sessionToken != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (validity != null ? !validity.equals(that.validity) : that.validity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sessionTokenId != null ? sessionTokenId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (sessionToken != null ? sessionToken.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (validity != null ? validity.hashCode() : 0);
        return result;
    }
}
