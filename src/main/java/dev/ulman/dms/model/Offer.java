package dev.ulman.dms.model;

import javax.persistence.*;
import java.util.Collection;


@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "offer_id")
    private long offerId;
    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;
    @OneToMany(mappedBy = "offer")
    private Collection<OfferDetails> offerDatails;


    //Getters @ Setters--------------------->

    public Collection<OfferDetails> getOfferDatails() {
        return offerDatails;
    }

    public void setOfferDatails(Collection<OfferDetails> offerDatails) {
        this.offerDatails = offerDatails;
    }

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
}