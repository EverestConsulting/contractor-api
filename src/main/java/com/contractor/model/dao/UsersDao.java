package com.contractor.model.dao;

import com.contractor.model.entity.Users;
import com.contractor.util.Crypt;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UsersDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Users fetchUserById(Long userId) {
        return entityManager.find(Users.class, userId);
    }

    public boolean userIsValid(String email, String password) {
        Users user = findByEmail(email);
        return userIsValid(email, password, user);

    }

    public boolean userIsValid(String email, String password, Users user) {
        return null != user && Crypt.hashMatches(password, user.getPassword());
    }

    public Users findByEmail(String email) {
        String query = "FROM Users WHERE userName = ?";
        return (Users) entityManager.createQuery(query).getSingleResult();
    }

    public Users createUser(Users user) {
        entityManager.persist(user);

        return findByEmail(user.getEmail());
    }

}
