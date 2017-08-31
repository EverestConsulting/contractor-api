package com.contractor.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Users {
    private long userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String streetName;
    private Short streetNumber;
    private String country;
    private Short zipCode;
    private String email;
    private short userRoleId;
    private String phoneNumber;
    private Timestamp created;
    private Timestamp lastModified;
    private Collection<Jobs> jobsByUserId;
    private Collection<Jobs> jobsByUserId_0;
    private Collection<SessionToken> sessionTokensByUserId;
    private UserRole userRoleByUserRoleId;

    @Id
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 255)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 255)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "street_name", nullable = false, length = 255)
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Basic
    @Column(name = "street_number", nullable = true)
    public Short getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Short streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Basic
    @Column(name = "country", nullable = false, length = 255)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "zip_code", nullable = true)
    public Short getZipCode() {
        return zipCode;
    }

    public void setZipCode(Short zipCode) {
        this.zipCode = zipCode;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "user_role_id", nullable = false)
    public short getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(short userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Basic
    @Column(name = "phone_number", nullable = false, length = 30)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
    @Column(name = "last_modified", nullable = true)
    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (userId != users.userId) return false;
        if (userRoleId != users.userRoleId) return false;
        if (userName != null ? !userName.equals(users.userName) : users.userName != null) return false;
        if (firstName != null ? !firstName.equals(users.firstName) : users.firstName != null) return false;
        if (lastName != null ? !lastName.equals(users.lastName) : users.lastName != null) return false;
        if (password != null ? !password.equals(users.password) : users.password != null) return false;
        if (streetName != null ? !streetName.equals(users.streetName) : users.streetName != null) return false;
        if (streetNumber != null ? !streetNumber.equals(users.streetNumber) : users.streetNumber != null) return false;
        if (country != null ? !country.equals(users.country) : users.country != null) return false;
        if (zipCode != null ? !zipCode.equals(users.zipCode) : users.zipCode != null) return false;
        if (email != null ? !email.equals(users.email) : users.email != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(users.phoneNumber) : users.phoneNumber != null) return false;
        if (created != null ? !created.equals(users.created) : users.created != null) return false;
        if (lastModified != null ? !lastModified.equals(users.lastModified) : users.lastModified != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (streetName != null ? streetName.hashCode() : 0);
        result = 31 * result + (streetNumber != null ? streetNumber.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (int) userRoleId;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "usersByJobCreatedByUserId")
    public Collection<Jobs> getJobsByUserId() {
        return jobsByUserId;
    }

    public void setJobsByUserId(Collection<Jobs> jobsByUserId) {
        this.jobsByUserId = jobsByUserId;
    }

    @OneToMany(mappedBy = "usersByJobAssignedToUserId")
    public Collection<Jobs> getJobsByUserId_0() {
        return jobsByUserId_0;
    }

    public void setJobsByUserId_0(Collection<Jobs> jobsByUserId_0) {
        this.jobsByUserId_0 = jobsByUserId_0;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<SessionToken> getSessionTokensByUserId() {
        return sessionTokensByUserId;
    }

    public void setSessionTokensByUserId(Collection<SessionToken> sessionTokensByUserId) {
        this.sessionTokensByUserId = sessionTokensByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "user_role_id", referencedColumnName = "user_role_id", nullable = false)
    public UserRole getUserRoleByUserRoleId() {
        return userRoleByUserRoleId;
    }

    public void setUserRoleByUserRoleId(UserRole userRoleByUserRoleId) {
        this.userRoleByUserRoleId = userRoleByUserRoleId;
    }
}
