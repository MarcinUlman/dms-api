package dev.ulman.dmsapi.dao;

import dev.ulman.dmsapi.model.Product;
import dev.ulman.dmsapi.model.Supplier;
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
public class SupplierDaoImp implements SupplierDao {

    private SessionFactory sessionFactory;

    @Autowired
    public SupplierDaoImp(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    @Override
    public Collection<Supplier> getAllSuppliers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Supplier> criteriaQuery = criteriaBuilder.createQuery(Supplier.class);
        Root<Supplier> root = criteriaQuery.from(Supplier.class);

        criteriaQuery.select(root);

        Query<Supplier> query = session.createQuery(criteriaQuery);

        Collection<Supplier> suppliers = query.getResultList();

        transaction.commit();
        return suppliers;
    }

    @Override
    public Supplier getSupplierById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Supplier supplier = session.get(Supplier.class, id);
        transaction.commit();
        return supplier;    }

    @Override
    public void add(Supplier newSupplier) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(newSupplier);

        transaction.commit();
    }

    @Override
    public void delete(long id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Supplier supplier = session.get(Supplier.class, id);
        if (supplier != null)
            session.delete(supplier);

        transaction.commit();
    }

    @Override
    public void update(long id, Supplier incomingSupplier) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        incomingSupplier.setSupplierId(id);
        session.saveOrUpdate(incomingSupplier);

        transaction.commit();
    }

    @Override
    public Collection<Product> getSupplierProdacts(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Supplier department = session.get(Supplier.class, id);
        if (department == null) return null;
        Collection<Product> products = department.getProducts();

        transaction.commit();
        return products;
    }
}
