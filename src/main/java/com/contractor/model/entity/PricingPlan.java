package com.contractor.model.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "pricing_plan", schema = "public", catalog = "contractor")
public class PricingPlan {
    private int pricingPlanId;
    private String pricingPlanName;
    private Collection<Pricing> pricingsByPricingPlanId;

    @Id
    @Column(name = "pricing_plan_id", nullable = false)
    public int getPricingPlanId() {
        return pricingPlanId;
    }

    public void setPricingPlanId(int pricingPlanId) {
        this.pricingPlanId = pricingPlanId;
    }

    @Basic
    @Column(name = "pricing_plan_name", nullable = false, length = 20)
    public String getPricingPlanName() {
        return pricingPlanName;
    }

    public void setPricingPlanName(String pricingPlanName) {
        this.pricingPlanName = pricingPlanName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PricingPlan that = (PricingPlan) o;

        if (pricingPlanId != that.pricingPlanId) return false;
        if (pricingPlanName != null ? !pricingPlanName.equals(that.pricingPlanName) : that.pricingPlanName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pricingPlanId;
        result = 31 * result + (pricingPlanName != null ? pricingPlanName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "pricingPlanByPricingPlanId")
    public Collection<Pricing> getPricingsByPricingPlanId() {
        return pricingsByPricingPlanId;
    }

    public void setPricingsByPricingPlanId(Collection<Pricing> pricingsByPricingPlanId) {
        this.pricingsByPricingPlanId = pricingsByPricingPlanId;
    }
}
