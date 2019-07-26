package dev.ulman.dms.service;

import dev.ulman.dms.dao.OfferDao;
import dev.ulman.dms.model.Offer;
import dev.ulman.dms.model.OfferDetails;
import org.springframework.stereotype.Service;

public interface OfferService {

    Offer getOfferById(long id);
    void add(Offer offer);
    void delete(long id);

    void editOffer(long offerId, OfferDetails offerDetails);

}
