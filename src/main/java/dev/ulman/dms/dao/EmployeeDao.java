package dev.ulman.dms.dao;

import dev.ulman.dms.model.Employee;
import dev.ulman.dms.model.Estimator;
import dev.ulman.dms.model.Trader;

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
