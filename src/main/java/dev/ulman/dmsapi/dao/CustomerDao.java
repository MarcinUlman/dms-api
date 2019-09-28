package dev.ulman.dmsapi.dao;

import dev.ulman.dmsapi.model.Contract;
import dev.ulman.dmsapi.model.Customer;

import java.util.Collection;

public interface CustomerDao {

    Collection<Customer> getAllCustomers();
    Customer getCustomerById(long id);
    void add (Customer newCustomer);
    void delete (long id);
    void update (long in, Customer incomingCustomer);

    Collection<Contract> getContracts(long id);

}
