package dev.ulman.dms.dao;

import dev.ulman.dms.model.Offer;
import dev.ulman.dms.model.OfferDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

@Repository
public class OfferDaoImp implements OfferDao {

    private SessionFactory sessionFactory;

    @Autowired
    public OfferDaoImp(EntityManagerFactory factory){
        if(factory.unwrap(SessionFactory.class) == null)
            throw new NullPointerException("factory is not a hibernate factory");
        sessionFactory = factory.unwrap(SessionFactory.class);
    }

    @Override
    public Offer getOfferById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Offer offer = session.get(Offer.class, id);

        transaction.commit();
        return offer;
    }

    @Override
    public void add(Offer offer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(offer);

        transaction.commit();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Offer offer = session.get(Offer.class, id);
        if(offer != null)
            session.delete(offer);

        transaction.commit();
    }

    @Override
    public void addProductToOffer(long offerId, OfferDetails offerDetails){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Offer offer = session.get(Offer.class, offerId);
        offerDetails.setOffer(offer);
        offer.getOfferDatails().add(offerDetails);

        transaction.commit();
    }

    @Override
    public void removeProductFromOffer(long offerId, long productId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Offer offer = session.get(Offer.class, offerId);
        offer.getOfferDatails().removeIf(x -> x.getProduct().equals(productId));

        transaction.commit();
    }

    @Override
    public void changeQuantityOfProductInOffer(long offerId, OfferDetails offerDetails) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Offer offer = session.get(Offer.class, offerId);
        offer.getOfferDatails().removeIf(x -> x.getProduct().equals(offerDetails.getProduct()));

        offerDetails.setOffer(offer);
        offer.getOfferDatails().add(offerDetails);

        transaction.commit();
    }
}
