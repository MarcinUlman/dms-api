package dev.ulman.dmsapi.service;

import dev.ulman.dmsapi.dao.DepartmentDao;
import dev.ulman.dmsapi.model.Department;
import dev.ulman.dmsapi.model.Employee;
import dev.ulman.dmsapi.model.Estimator;
import dev.ulman.dmsapi.model.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DepartmentServideImpl implements DepartmentService {

    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentServideImpl(@Qualifier("DBconnection") DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public Collection<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }

    @Override
    public Department getDepartmentById(long id) {
        return departmentDao.getDepartmentById(id);
    }

    @Override
    public void add(Department newDepartment) {
        departmentDao.add(newDepartment);
    }

    @Override
    public void deleteDepartment(long id) {
        departmentDao.delete(id);
    }

    @Override
    public void update(long id, Department incomingDepartment) {
        departmentDao.update(id, incomingDepartment);
    }

    @Override
    public Collection<Employee> getEmployees(long id) {
        return departmentDao.getEmployees(id);
    }

    @Override
    public Collection<Trader> getTraders(long id) {
        return departmentDao.getTraders(id);
    }

    @Override
    public Collection<Estimator> getEstimetors(long id) {
        return departmentDao.getEstimetors(id);
    }

}
