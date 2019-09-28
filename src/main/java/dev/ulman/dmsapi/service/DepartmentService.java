package dev.ulman.dmsapi.service;

import dev.ulman.dmsapi.model.Department;
import dev.ulman.dmsapi.model.Employee;
import dev.ulman.dmsapi.model.Estimator;
import dev.ulman.dmsapi.model.Trader;

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
