package dev.ulman.dmsapi.service;

import dev.ulman.dmsapi.dao.CustomerDao;
import dev.ulman.dmsapi.model.Contract;
import dev.ulman.dmsapi.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomerServiceImp implements  CustomerService {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImp(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Collection<Customer> getAllCustomerts() {
        return customerDao.getAllCustomers();
    }

    @Override
    public Customer getCustomerById(long id) {
        return customerDao.getCustomerById(id);
    }

    @Override
    public void add(Customer newCustomer) {
        customerDao.add(newCustomer);
    }

    @Override
    public void delete(long id) {
        customerDao.delete(id);
    }

    @Override
    public void update(long id, Customer incomingCustomer) {
        customerDao.update(id, incomingCustomer);
    }

    @Override
    public Collection<Contract> getContracts(long id) {
        return customerDao.getContracts(id);
    }
}
