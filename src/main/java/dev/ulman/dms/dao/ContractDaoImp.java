package dev.ulman.dms.dao;

import dev.ulman.dms.model.Contract;
import dev.ulman.dms.model.Employee;
import dev.ulman.dms.model.Offer;
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
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class ContractDaoImp implements ContractDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public ContractDaoImp(EntityManagerFactory factory){
        if(factory.unwrap(SessionFactory.class) == null)
            throw new NullPointerException("factory is not a hibernate factory");

        sessionFactory =  factory.unwrap(SessionFactory.class);
    }

    @Override
    public Collection<Contract> getAllContracts() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Contract> criteriaQuery = criteriaBuilder.createQuery(Contract.class);
        Root<Contract> root = criteriaQuery.from(Contract.class);
        criteriaQuery.select(root);

        Query<Contract> query = session.createQuery(criteriaQuery);

        Collection<Contract> contracts = query.getResultList();

        transaction.commit();
        session.close();
        return contracts;
    }

    @Override
    public Contract getContractById(long id) {
       Session session = sessionFactory.openSession();
       Transaction transaction = session.beginTransaction();
       Contract contract = session.get(Contract.class, id);
       transaction.commit();
       session.close();
       return contract;
    }

    @Override
    public void add(Contract contract) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(contract);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Contract contract = session.get(Contract.class, id);
        if (contract != null)
            session.delete(contract);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(long id, Contract contract) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        contract.setContractId(id);
        session.saveOrUpdate(contract);

        transaction.commit();
        session.close();
    }

    @Override
    public Collection<Offer> getContractOffers(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Offer> criteriaQuery = criteriaBuilder.createQuery(Offer.class);
        Root<Offer> root = criteriaQuery.from(Offer.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("contract"), id));

        Query<Offer> query = session.createQuery(criteriaQuery);

        Collection<Offer> offers = query.getResultList();

        transaction.commit();
        session.close();

        return offers;
    }

    @Override
    public Collection<Employee> getContractTeam(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Contract contract = session.get(Contract.class, id);
        long traderId = contract.getCustomer().getTrader().getEmployeeId();
        long estimatorId = contract.getEstimator().getEmployeeId();

        Collection<Employee> employees = new ArrayList<>();
        employees.add(session.get(Employee.class, traderId));
        employees.add(session.get(Employee.class, estimatorId));

        transaction.commit();
        session.close();

        return employees;
    }
}
