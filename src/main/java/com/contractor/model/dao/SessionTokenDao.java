package com.contractor.model.dao;

import com.contractor.model.entity.SessionToken;
import com.contractor.util.Utils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

public class SessionTokenDao {
    @PersistenceContext
    private EntityManager entityManager;

    public SessionToken createSessionToken(Long userId) {

        SessionToken sessionToken = new SessionToken();
        sessionToken.setCreated(Timestamp.valueOf(String.valueOf(System.currentTimeMillis())));
        sessionToken.setValidity(sessionToken.getCreated());
        sessionToken.setSessionToken(generateToken());
        sessionToken.setUserId(userId);

        entityManager.persist(sessionToken);

        return getSessionTokenBySessionTokenValue(sessionToken.getSessionToken());
    }

    public SessionToken getSessionTokenBySessionTokenValue(String token) {
        String query = "FROM SessionToken WHERE sessionToken = ?";
        return (SessionToken) entityManager.createQuery(query).setParameter(0, token).getSingleResult();
    }

    public List<SessionToken> getSessionTokenByUserId(Long userId) {
        String query = "FROM SessionToken WHERE userId = ?";
        return (List<SessionToken>) entityManager.createQuery(query).setParameter(0, userId).getResultList();
    }

    public boolean tokenValid(String token) {
        SessionToken sessionToken = getSessionTokenBySessionTokenValue(token);
        return null != sessionToken && Utils.compareTimestamp(sessionToken.getValidity(), Utils.timestampFromMillis());
    }

    public void deleteSessionTokenByUserId(Long userId) {
        entityManager.remove(getSessionTokenByUserId(userId));
    }

    private String generateToken() {
        SecureRandom sr = new SecureRandom();
        return new BigInteger(130, sr).toString(32);
    }
}
