package dev.ulman.dms.dao;

import dev.ulman.dms.model.Offer;
import dev.ulman.dms.model.OfferDetails;

public interface OfferDao {

    Offer getOfferById(long id);
    void add(Offer offer);
    void delete(long id);

    void removeProductFromOffer(long offerId, OfferDetails offerDetails);
    void changeQuantityOfProductInOffer(long offerId, OfferDetails offerDetails);
}
