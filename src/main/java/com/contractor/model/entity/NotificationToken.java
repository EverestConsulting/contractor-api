package com.contractor.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "notification_token", schema = "public", catalog = "contractor")
public class NotificationToken {
    private Integer notificationTokenId;
    private Integer userId;
    private String tokenType;
    private String notificationToken;
    private Timestamp created;

    @Id
    @Column(name = "notification_token_id", nullable = false)
    public Integer getNotificationTokenId() {
        return notificationTokenId;
    }

    public void setNotificationTokenId(Integer notificationTokenId) {
        this.notificationTokenId = notificationTokenId;
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
    @Column(name = "token_type", nullable = false, length = 10)
    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @Basic
    @Column(name = "notification_token", nullable = true, length = 255)
    public String getNotificationToken() {
        return notificationToken;
    }

    public void setNotificationToken(String notificationToken) {
        this.notificationToken = notificationToken;
    }

    @Basic
    @Column(name = "created", nullable = true)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotificationToken that = (NotificationToken) o;

        if (notificationTokenId != null ? !notificationTokenId.equals(that.notificationTokenId) : that.notificationTokenId != null)
            return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (tokenType != null ? !tokenType.equals(that.tokenType) : that.tokenType != null) return false;
        if (notificationToken != null ? !notificationToken.equals(that.notificationToken) : that.notificationToken != null)
            return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = notificationTokenId != null ? notificationTokenId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (tokenType != null ? tokenType.hashCode() : 0);
        result = 31 * result + (notificationToken != null ? notificationToken.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }
}
