package dev.ulman.dmsapi.service;

import dev.ulman.dmsapi.model.Offer;
import dev.ulman.dmsapi.model.OfferDetails;

public interface OfferService {

    Offer getOfferById(long id);
    void add(Offer offer);
    void delete(long id);

    void editOffer(long offerId, OfferDetails offerDetails);

}
