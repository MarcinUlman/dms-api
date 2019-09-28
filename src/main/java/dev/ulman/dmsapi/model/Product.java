package dev.ulman.dmsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private long productId;
    private String name;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    private BigDecimal price;
    @JsonIgnore
    @OneToMany (mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<OfferDetails> offerDatails;


    //Getters @ Setters--------------------->

    public Collection<OfferDetails> getOfferDatails() {
        return offerDatails;
    }

    public void setOfferDatails(Collection<OfferDetails> offerDatails) {
        this.offerDatails = offerDatails;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}