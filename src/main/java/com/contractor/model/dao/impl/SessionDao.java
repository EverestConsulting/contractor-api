package com.contractor.model.dao.impl;

import com.contractor.model.entity.SessionToken;

public class SessionDao extends AbstractDao<SessionToken> {
    public SessionDao() {
        super(SessionToken.class);
    }
}
