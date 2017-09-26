package com.contractor.controller;

import com.contractor.App;
import com.contractor.api.ResponseFactory;
import com.contractor.model.dao.CompanyDao;
import com.contractor.model.entity.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

public class CompanyController {

    private static final Logger LOG = LoggerFactory.getLogger(CompanyController.class.getSimpleName());
    private static CompanyController INSTANCE;
    private CompanyDao COMPANY_DAO;

    private CompanyController() {
        //singleton pattern
        COMPANY_DAO = App.instance().getCompanyDao();
    }

    public static void init() {
        if (null != INSTANCE) {
            LOG.error(String.format("%s already initialized!!!", CompanyController.class.getSimpleName()));
            return;
        }

        INSTANCE = new CompanyController();
    }

    public static CompanyController instance() {
        if (null == INSTANCE) {
            LOG.error(String.format("%s not properly initialized!!!", CompanyController.class.getSimpleName()));
        }
        return INSTANCE;
    }

    public Response createCompany(Company company) {
        Integer companyId;

        companyId = COMPANY_DAO.create(company);

        if (null == companyId) {
            return ResponseFactory.getInternalError500();
        }
        company.setCompanyId(companyId);
        return ResponseFactory.getSuccess200(company);
    }

    public Response getCompany(Integer companyId) {
        Company company = COMPANY_DAO.get(companyId);

        if (null == company) {
            return ResponseFactory.getNotFound404();
        }

        return ResponseFactory.getSuccess200(company);
    }

    public Response updateCompany(Company company) {
        boolean success = COMPANY_DAO.update(company);

        if (!success) {
            return ResponseFactory.getBadRequest400("Couldn't update company", "Make sure all fields are present");
        }

        return ResponseFactory.getSuccess200(company);
    }

    public Response deleteCompany(Integer companyId) {
        Company company = COMPANY_DAO.get(companyId);

        if (null == company) {
            return ResponseFactory.getNotFound404();
        }

        if (COMPANY_DAO.delete(company)) {
            return ResponseFactory.getSuccess200();
        }

        return ResponseFactory.getInternalError500();
    }
}
