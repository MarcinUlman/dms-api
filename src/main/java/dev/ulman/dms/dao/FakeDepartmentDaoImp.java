package dev.ulman.dms.dao;

import dev.ulman.dms.model.Address;
import dev.ulman.dms.model.Department;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository("fakeDao")
public class FakeDepartmentDaoImp implements DepartmentDao {

    private static Collection<Department> db = new ArrayList<>();

    public FakeDepartmentDaoImp() {
        Department department = new Department();
        department.setName("Departmen test1");
        department.setDepartmentId(1);
        department.setPhoneNumber(123456);
        department.setEmployees(new ArrayList<>());
        Address addres = new Address();
        addres.setCity("Krakow");
        addres.setPostCode(30633);
        addres.setState("Malopolskie");
        addres.setStreet("Wielicka 12");
        department.setAddress(addres);
        db.add(department);
    }

    @Override
    public Collection<Department> getAllDepartments() {
        return db;
    }
}
