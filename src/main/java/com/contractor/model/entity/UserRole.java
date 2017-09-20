package com.contractor.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_role", schema = "public", catalog = "contractor")
public class UserRole {
    private Integer userRoleId;
    private String userRoleName;

    @Id
    @Column(name = "user_role_id", nullable = false)
    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (userRoleId != null ? !userRoleId.equals(userRole.userRoleId) : userRole.userRoleId != null) return false;
        if (userRoleName != null ? !userRoleName.equals(userRole.userRoleName) : userRole.userRoleName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userRoleId != null ? userRoleId.hashCode() : 0;
        result = 31 * result + (userRoleName != null ? userRoleName.hashCode() : 0);
        return result;
    }
}
