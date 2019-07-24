package dev.ulman.dms.service;

import dev.ulman.dms.dao.EmployeeDao;
import dev.ulman.dms.model.Employee;
import dev.ulman.dms.model.Estimator;
import dev.ulman.dms.model.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImp(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public Collection<Trader> getAllTraders() {
        return employeeDao.getAllTraders();
    }

    @Override
    public Collection<Estimator> getAllEstimators() {
        return employeeDao.getAllEstimators();
    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeDao.getEmployeeById(id);
    }

    @Override
    public void addTrader(Trader trader) {
        employeeDao.addTrader(trader);
    }

    @Override
    public void addEstimator(Estimator estimator) {
        employeeDao.addEstimator(estimator);
    }

    @Override
    public void delete(long id) {
        employeeDao.delete(id);
    }

    @Override
    public void updateTrader(long id, Trader incomingEmployee) {
        employeeDao.updateTrader(id, incomingEmployee);
    }
    @Override
    public void updateEstimator(long id, Estimator incomingEmployee) {
        employeeDao.updateEstimator(id, incomingEmployee);
    }
}
