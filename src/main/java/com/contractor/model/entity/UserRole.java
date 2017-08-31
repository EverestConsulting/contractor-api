package com.contractor.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "user_role", schema = "public", catalog = "contractor")
public class UserRole {
    private short userRoleId;
    private String userRoleName;
    private Serializable userRight;
    private Collection<Users> usersByUserRoleId;

    @Id
    @Column(name = "user_role_id", nullable = false)
    public short getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(short userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Basic
    @Column(name = "user_role_name", nullable = false, length = 20)
    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    @Basic
    @Column(name = "user_right", nullable = false)
    public Serializable getUserRight() {
        return userRight;
    }

    public void setUserRight(Serializable userRight) {
        this.userRight = userRight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (userRoleId != userRole.userRoleId) return false;
        if (userRoleName != null ? !userRoleName.equals(userRole.userRoleName) : userRole.userRoleName != null)
            return false;
        if (userRight != null ? !userRight.equals(userRole.userRight) : userRole.userRight != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) userRoleId;
        result = 31 * result + (userRoleName != null ? userRoleName.hashCode() : 0);
        result = 31 * result + (userRight != null ? userRight.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userRoleByUserRoleId")
    public Collection<Users> getUsersByUserRoleId() {
        return usersByUserRoleId;
    }

    public void setUsersByUserRoleId(Collection<Users> usersByUserRoleId) {
        this.usersByUserRoleId = usersByUserRoleId;
    }
}
