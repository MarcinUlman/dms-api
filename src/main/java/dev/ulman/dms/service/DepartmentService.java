package dev.ulman.dms.service;

import dev.ulman.dms.model.Department;
import dev.ulman.dms.model.Employee;
import dev.ulman.dms.model.Estimator;
import dev.ulman.dms.model.Trader;

import java.util.Collection;

public interface DepartmentService {

    Collection<Department> getAllDepartments();

    Department getDepartmentById(long id);

    void add(Department newDepartment);

    void deleteDepartment(long id);

    void update(long id, Department incomingDepartment);

    Collection<Employee> getEmployees(long id);

    Collection<Trader> getTraders(long id);

    Collection<Estimator> getEstimetors(long id);

}
