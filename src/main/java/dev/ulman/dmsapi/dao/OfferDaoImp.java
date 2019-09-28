package dev.ulman.dmsapi.dao;

import dev.ulman.dmsapi.model.Offer;
import dev.ulman.dmsapi.model.OfferDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
    public void removeProductFromOffer(long offerId, OfferDetails offerDetails) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<OfferDetails> criteriaQuery = criteriaBuilder.createQuery(OfferDetails.class);
        Root<OfferDetails> root = criteriaQuery.from(OfferDetails.class);

        criteriaQuery.where(
                criteriaBuilder.equal(root.get("offer"), offerId),
                criteriaBuilder.equal(root.get("product"), offerDetails.getProduct()));

        Query<OfferDetails> query = session.createQuery(criteriaQuery);
        OfferDetails foundOfferDetails = query.getSingleResult();

        foundOfferDetails.setOffer(session.get(Offer.class, offerId));
        session.delete(foundOfferDetails);

        transaction.commit();
    }

    @Override
    public void changeQuantityOfProductInOffer(long offerId, OfferDetails offerDetails) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<OfferDetails> criteriaQuery = criteriaBuilder.createQuery(OfferDetails.class);
        Root<OfferDetails> root = criteriaQuery.from(OfferDetails.class);

        criteriaQuery.where(
                criteriaBuilder.equal(root.get("offer"), offerId),
                criteriaBuilder.equal(root.get("product"), offerDetails.getProduct()));

        Query<OfferDetails> query = session.createQuery(criteriaQuery);

        try {
            OfferDetails foundOfferDetails = query.getSingleResult();
            foundOfferDetails.setQuantity(offerDetails.getQuantity());
            session.saveOrUpdate(foundOfferDetails);
        } catch (NoResultException e) {
            offerDetails.setOffer(session.get(Offer.class, offerId));
            session.save(offerDetails);
        }

        transaction.commit();
    }
}
