package com.contractor.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Company {
    private Integer companyId;
    private String name;
    private String companyIdentificationNumber;
    private String companyRegistrationCode;
    private String streetName;
    private String streetNumber;
    private String country;
    private String region;
    private Integer zipCode;
    private String notes;
    private String phoneNumber;
    private String bankAccountNumber;
    private Boolean active;
    private Timestamp created;
    private Timestamp lastModified;

    @Id
    @Column(name = "company_id", nullable = false)
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "company_identification_number", nullable = false, length = 20)
    public String getCompanyIdentificationNumber() {
        return companyIdentificationNumber;
    }

    public void setCompanyIdentificationNumber(String companyIdentificationNumber) {
        this.companyIdentificationNumber = companyIdentificationNumber;
    }

    @Basic
    @Column(name = "company_registration_code", nullable = false, length = 30)
    public String getCompanyRegistrationCode() {
        return companyRegistrationCode;
    }

    public void setCompanyRegistrationCode(String companyRegistrationCode) {
        this.companyRegistrationCode = companyRegistrationCode;
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
    @Column(name = "street_number", nullable = false, length = 10)
    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
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
    @Column(name = "region", nullable = false, length = 255)
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "zip_code", nullable = true)
    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    @Basic
    @Column(name = "notes", nullable = false, length = 255)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
    @Column(name = "bank_account_number", nullable = false, length = 30)
    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    @Basic
    @Column(name = "active", nullable = false)
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Basic
    @Column(name = "created", nullable = false)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "last_modified", nullable = false)
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

        Company company = (Company) o;

        if (companyId != null ? !companyId.equals(company.companyId) : company.companyId != null) return false;
        if (name != null ? !name.equals(company.name) : company.name != null) return false;
        if (companyIdentificationNumber != null ? !companyIdentificationNumber.equals(company.companyIdentificationNumber) : company.companyIdentificationNumber != null)
            return false;
        if (companyRegistrationCode != null ? !companyRegistrationCode.equals(company.companyRegistrationCode) : company.companyRegistrationCode != null)
            return false;
        if (streetName != null ? !streetName.equals(company.streetName) : company.streetName != null) return false;
        if (streetNumber != null ? !streetNumber.equals(company.streetNumber) : company.streetNumber != null)
            return false;
        if (country != null ? !country.equals(company.country) : company.country != null) return false;
        if (region != null ? !region.equals(company.region) : company.region != null) return false;
        if (zipCode != null ? !zipCode.equals(company.zipCode) : company.zipCode != null) return false;
        if (notes != null ? !notes.equals(company.notes) : company.notes != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(company.phoneNumber) : company.phoneNumber != null) return false;
        if (bankAccountNumber != null ? !bankAccountNumber.equals(company.bankAccountNumber) : company.bankAccountNumber != null)
            return false;
        if (active != null ? !active.equals(company.active) : company.active != null) return false;
        if (created != null ? !created.equals(company.created) : company.created != null) return false;
        if (lastModified != null ? !lastModified.equals(company.lastModified) : company.lastModified != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = companyId != null ? companyId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (companyIdentificationNumber != null ? companyIdentificationNumber.hashCode() : 0);
        result = 31 * result + (companyRegistrationCode != null ? companyRegistrationCode.hashCode() : 0);
        result = 31 * result + (streetName != null ? streetName.hashCode() : 0);
        result = 31 * result + (streetNumber != null ? streetNumber.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (bankAccountNumber != null ? bankAccountNumber.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        return result;
    }
}
