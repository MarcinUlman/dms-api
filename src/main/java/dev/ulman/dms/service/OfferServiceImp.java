package dev.ulman.dms.service;

import dev.ulman.dms.dao.OfferDao;
import dev.ulman.dms.model.Offer;
import dev.ulman.dms.model.OfferDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImp implements OfferService {

    private final OfferDao offerDao;

    @Autowired
    public OfferServiceImp(OfferDao offerDao) {
        this.offerDao = offerDao;
    }

    @Override
    public Offer getOfferById(long id) {
        return offerDao.getOfferById(id);
    }

    @Override
    public void add(Offer offer) {
        offerDao.add(offer);
    }

    @Override
    public void delete(long id) {
        offerDao.delete(id);
    }

    @Override
    public void editOffer(long offerId, OfferDetails offerDetails) {
        if (offerDetails.getQuantity() <= 0)
            offerDao.removeProductFromOffer(offerId, offerDetails);
        else
            offerDao.changeQuantityOfProductInOffer(offerId, offerDetails);

    }
}
