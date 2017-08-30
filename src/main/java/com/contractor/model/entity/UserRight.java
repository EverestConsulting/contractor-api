package com.contractor.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_right", schema = "public", catalog = "contractor")
public class UserRight {
    private int userRightId;
    private String userRight;

    @Id
    @Column(name = "user_right_id", nullable = false)
    public int getUserRightId() {
        return userRightId;
    }

    public void setUserRightId(int userRightId) {
        this.userRightId = userRightId;
    }

    @Basic
    @Column(name = "user_right", nullable = false, length = 20)
    public String getUserRight() {
        return userRight;
    }

    public void setUserRight(String userRight) {
        this.userRight = userRight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRight userRight1 = (UserRight) o;

        if (userRightId != userRight1.userRightId) return false;
        if (userRight != null ? !userRight.equals(userRight1.userRight) : userRight1.userRight != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userRightId;
        result = 31 * result + (userRight != null ? userRight.hashCode() : 0);
        return result;
    }
}
