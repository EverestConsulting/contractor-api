package com.contractor.model.dao;

import com.contractor.model.entity.UserRole;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
public class UserRoleDao {

    @PersistenceContext
    private EntityManager entityManager;


    public Map<Integer, UserRole> fetchUserRoles() {
        Map<Integer, UserRole> result = new HashMap<>(0);
        String query = "FROM UserRole ORDER BY userRoleId ASC";
        List<UserRole> userRoles = (List<UserRole>) entityManager.createQuery(query).getResultList();

        userRoles.forEach(ur ->
                result.put((int) ur.getUserRoleId(), ur));

        return result;
    }
}
