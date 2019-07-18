package dev.ulman.dms.dao;

import dev.ulman.dms.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

@Repository
public class ProductDaoImp implements ProductDao {

    private SessionFactory sessionFactory;

    @Autowired
    public ProductDaoImp(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        sessionFactory = factory.unwrap(SessionFactory.class);
    }

    @Override
    public Collection<Product> getAllProcusts() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);

        criteriaQuery.select(root);

        Query<Product> query = session.createQuery(criteriaQuery);

        Collection<Product> products = query.getResultList();

       transaction.commit();
        return products;
    }

    @Override
    public Product getProductById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Product product = session.get(Product.class, id);

        transaction.commit();
        return product;
    }

    @Override
    public void add(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(product);

        transaction.commit();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Product product = session.get(Product.class, id);
        if (product != null)
            session.delete(product);

        transaction.commit();
    }

    @Override
    public void upgrade(long id, Product incomingProduct) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        incomingProduct.setProductId(id);
        session.saveOrUpdate(incomingProduct);

        transaction.commit();
    }
}