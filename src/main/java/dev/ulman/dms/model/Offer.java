package dev.ulman.dms.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;


@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long offerId;
    @ManyToOne
    private Contract contract;
    @ManyToMany
    private Collection<Product> products;
    private BigDecimal totalCost;


    //Getters @ Setters--------------------->

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}