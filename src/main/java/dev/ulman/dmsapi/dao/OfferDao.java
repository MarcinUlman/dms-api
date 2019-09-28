package dev.ulman.dmsapi.dao;

import dev.ulman.dmsapi.model.Offer;
import dev.ulman.dmsapi.model.OfferDetails;

public interface OfferDao {

    Offer getOfferById(long id);
    void add(Offer offer);
    void delete(long id);

    void removeProductFromOffer(long offerId, OfferDetails offerDetails);
    void changeQuantityOfProductInOffer(long offerId, OfferDetails offerDetails);
}
