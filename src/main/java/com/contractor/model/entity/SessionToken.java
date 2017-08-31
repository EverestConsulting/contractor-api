package com.contractor.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "session_token", schema = "public", catalog = "contractor")
public class SessionToken {
    private long sessionTokenId;
    private long userId;
    private String sessionToken;
    private Timestamp created;
    private Timestamp validity;
    private Users usersByUserId;

    @Id
    @Column(name = "session_token_id", nullable = false)
    public long getSessionTokenId() {
        return sessionTokenId;
    }

    public void setSessionTokenId(long sessionTokenId) {
        this.sessionTokenId = sessionTokenId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "session_token", nullable = false, length = 20)
    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    @Basic
    @Column(name = "created", nullable = false)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "validity", nullable = false)
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

        if (sessionTokenId != that.sessionTokenId) return false;
        if (userId != that.userId) return false;
        if (sessionToken != null ? !sessionToken.equals(that.sessionToken) : that.sessionToken != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (validity != null ? !validity.equals(that.validity) : that.validity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (sessionTokenId ^ (sessionTokenId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (sessionToken != null ? sessionToken.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (validity != null ? validity.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public Users getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(Users usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}
