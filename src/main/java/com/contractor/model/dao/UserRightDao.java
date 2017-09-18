package com.contractor.model.dao;

import com.contractor.model.entity.UserRight;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRightDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Map<Integer, UserRight> fetchUserRights() {
        Map<Integer, UserRight> result = new HashMap<>(0);
        String query = "FROM UserRight ORDER BY userRightId ASC";
        List<UserRight> userRoles = (List<UserRight>) entityManager.createQuery(query).getResultList();

        userRoles.forEach(ur ->
                result.put((int) ur.getUserRightId(), ur));

        return result;
    }

}
