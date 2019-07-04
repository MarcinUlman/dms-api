package dev.ulman.dms.dao;

import dev.ulman.dms.model.Department;
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
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.Optional;

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

//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
//        Root<Department> root = criteriaQuery.from(Department.class);
//
//        criteriaQuery.where(criteriaBuilder.equal(root.get("departmentId"), id));
//
//        Query<Department> query = session.createQuery(criteriaQuery);
//
//        Department department;
//        try {
//           department = query.getSingleResult();
//        } catch (NoResultException e){
//            department = null;
//        }
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
        Transaction transaction = session.getTransaction();

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

        for (Employee e :employees
             ) {
            System.out.println(e.getName() + " " + e.getSurname());

        }

        return employees;
    }

    @Override
    public Collection<Trader> getTraders(long id) {
        return null;
    }

    @Override
    public Collection<Estimator> getEstimetors(long id) {
        return null;
    }



}
