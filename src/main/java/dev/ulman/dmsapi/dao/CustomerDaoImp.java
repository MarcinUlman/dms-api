package dev.ulman.dmsapi.dao;

import dev.ulman.dmsapi.model.Contract;
import dev.ulman.dmsapi.model.Customer;
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
public class CustomerDaoImp implements CustomerDao {

    public final SessionFactory sessionFactory;

    @Autowired
    public CustomerDaoImp(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        sessionFactory = factory.unwrap(SessionFactory.class);
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> root = criteriaQuery.from(Customer.class);

        criteriaQuery.select(root);

        Query<Customer> query = session.createQuery(criteriaQuery);

        Collection<Customer> customers = query.getResultList();

        transaction.commit();
        return customers;
    }

    @Override
    public Customer getCustomerById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, id);

        transaction.commit();
        return customer;
    }

    @Override
    public void add(Customer newCustomer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(newCustomer);

        transaction.commit();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, id);
        if(customer != null)
            session.delete(customer);

        transaction.commit();
    }

    @Override
    public void update(long id, Customer incomingCustomer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        incomingCustomer.setCustomerId(id);
        session.saveOrUpdate(incomingCustomer);

        transaction.commit();
    }

    @Override
    public Collection<Contract> getContracts(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, id);
        Collection<Contract> contracts = customer.getContracts();

        transaction.commit();
        return contracts;
    }
}
