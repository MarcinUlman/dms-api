package dev.ulman.dms.dao;

import dev.ulman.dms.model.Contract;
import dev.ulman.dms.model.Customer;

import java.util.Collection;

public interface CustomerDao {

    Collection<Customer> getAllCustomers();
    Customer getCustomerById(long id);
    void add (Customer newCustomer);
    void delete (long id);
    void update (long in, Customer incomingCustomer);

    Collection<Contract> getContracts(long id);

}
