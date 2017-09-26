package com.contractor.model.dao;

import com.contractor.model.entity.Company;

public class CompanyDao extends AbstractDao<Company> {
    public CompanyDao() {
        super(Company.class);
    }
}
