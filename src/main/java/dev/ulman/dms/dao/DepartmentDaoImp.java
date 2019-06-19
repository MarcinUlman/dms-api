package dev.ulman.dms.dao;

import dev.ulman.dms.model.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.Collection;
import java.util.List;

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
        List<Department> departments =  session.createQuery("FROM Department").list();

        return departments;
    }
}
