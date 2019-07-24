package dev.ulman.dms.dao;

import dev.ulman.dms.model.Employee;
import dev.ulman.dms.model.Estimator;
import dev.ulman.dms.model.Trader;
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
public class EmployeeDaoImp implements EmployeeDao {

    private SessionFactory sessionFactory;

    @Autowired
    public EmployeeDaoImp(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null)
            throw new NullPointerException("factory is not a hibernate factory");
        sessionFactory = factory.unwrap(SessionFactory.class);
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        criteriaQuery.select(root);

        Query<Employee> query = session.createQuery(criteriaQuery);

        Collection<Employee> employees = query.getResultList();

        transaction.commit();
        return employees;
    }

    @Override
    public Collection<Trader> getAllTraders() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Trader> criteriaQuery = criteriaBuilder.createQuery(Trader.class);
        Root<Trader> root = criteriaQuery.from(Trader.class);

        criteriaQuery.select(root);

        Query<Trader> query = session.createQuery(criteriaQuery);

        Collection<Trader> traders = query.getResultList();

        transaction.commit();
        return traders;
    }

    @Override
    public Collection<Estimator> getAllEstimators() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Estimator> criteriaQuery = criteriaBuilder.createQuery(Estimator.class);
        Root<Estimator> root = criteriaQuery.from(Estimator.class);

        criteriaQuery.select(root);

        Query<Estimator> query = session.createQuery(criteriaQuery);

        Collection<Estimator> estimators = query.getResultList();

        transaction.commit();
        return estimators;
    }

    @Override
    public Employee getEmployeeById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = session.get(Employee.class, id);

        transaction.commit();
        return employee;
    }

    @Override
    public void addTrader(Trader trader) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(trader);

        transaction.commit();
    }

    @Override
    public void addEstimator(Estimator estimator) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(estimator);

        transaction.commit();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = session.get(Employee.class, id);
        if(employee != null)
            session.delete(employee);

        transaction.commit();
    }

    @Override
    public void updateTrader(long id, Trader incomingEmployee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        incomingEmployee.setEmployeeId(id);
        session.saveOrUpdate(incomingEmployee);

        transaction.commit();
    }

    @Override
    public void updateEstimator(long id, Estimator incomingEmployee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        incomingEmployee.setEmployeeId(id);
        session.saveOrUpdate(incomingEmployee);

        transaction.commit();
    }
}
