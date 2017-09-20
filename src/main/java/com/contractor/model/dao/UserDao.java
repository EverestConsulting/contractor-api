package com.contractor.model.dao;

import com.contractor.model.entity.Users;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import javax.persistence.Query;

public class UserDao extends AbstractDao<Users> {

    public UserDao() {
        super(Users.class);
    }
}
