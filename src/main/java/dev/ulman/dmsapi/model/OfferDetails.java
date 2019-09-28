package dev.ulman.dmsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "offer_details")
public class OfferDetails implements Serializable {

    @JsonIgnore
    @Id
    @ManyToOne
    private Offer offer;

    @Id
    @ManyToOne
    private Product product;

    private double quantity;

    //Getters @ Setters--------------------->


    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
