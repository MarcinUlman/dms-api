package dev.ulman.dmsapi.dao;

import dev.ulman.dmsapi.model.Employee;
import dev.ulman.dmsapi.model.Estimator;
import dev.ulman.dmsapi.model.Trader;

import java.util.Collection;

public interface EmployeeDao {

    Collection<Employee> getAllEmployees();
    Collection<Trader> getAllTraders();
    Collection<Estimator> getAllEstimators();

    Employee getEmployeeById(long id);

    void addTrader(Trader trader);
    void addEstimator(Estimator estimator);

    void delete(long id);

    void updateTrader(long id, Trader incomingEmployee);
    void updateEstimator(long id, Estimator incomingEmployee);
}
