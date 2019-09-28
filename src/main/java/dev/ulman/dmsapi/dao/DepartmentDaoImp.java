package dev.ulman.dmsapi.dao;

import dev.ulman.dmsapi.model.Department;
import dev.ulman.dmsapi.model.Employee;
import dev.ulman.dmsapi.model.Estimator;
import dev.ulman.dmsapi.model.Trader;
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

@Repository("DBconnection")
public class DepartmentDaoImp implements DepartmentDao {


    private SessionFactory sessionFactory;

    @Autowired
    public DepartmentDaoImp(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    @Override
    public Collection<Department> getAllDepartments() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
        Root<Department> root = criteriaQuery.from(Department.class);

        criteriaQuery.select(root);

        Query<Department> query = session.createQuery(criteriaQuery);

        Collection<Department> departments = query.getResultList();

        transaction.commit();
        return departments;
    }

    @Override
    public Department getDepartmentById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Department department = session.get(Department.class, id);
        transaction.commit();
        return department;
    }

    @Override
    public void add(Department newDepartment) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(newDepartment);

        transaction.commit();
    }

    @Override
    public void delete(long id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Department department = session.get(Department.class, id);
        if (department != null)
            session.delete(department);

        transaction.commit();
    }

    @Override
    public void update(long id, Department incomingDepartment) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        incomingDepartment.setDepartmentId(id);
        session.saveOrUpdate(incomingDepartment);

        transaction.commit();
    }

    @Override
    public Collection<Employee> getEmployees(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Department department = session.get(Department.class, id);
        if (department == null) return null;
        Collection<Employee> employees = department.getEmployees();

        transaction.commit();
        return employees;
    }

    @Override
    public Collection<Trader> getTraders(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Trader> criteriaQuery = criteriaBuilder.createQuery(Trader.class);
        Root<Trader> traderRoot = criteriaQuery.from(Trader.class);
        criteriaQuery.where(criteriaBuilder.equal(traderRoot.get("department"), id));

        Query<Trader> query = session.createQuery(criteriaQuery);
        Collection<Trader> traders = query.getResultList();
        transaction.commit();

        return traders;
    }

    @Override
    public Collection<Estimator> getEstimetors(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Estimator> criteriaQuery = criteriaBuilder.createQuery(Estimator.class);
        Root<Estimator> estimatorRoot = criteriaQuery.from(Estimator.class);
        criteriaQuery.where(criteriaBuilder.equal(estimatorRoot.get("department"), id));

        Query<Estimator> query = session.createQuery(criteriaQuery);
        Collection<Estimator> estimators = query.getResultList();

        transaction.commit();
        return estimators;
    }



}
