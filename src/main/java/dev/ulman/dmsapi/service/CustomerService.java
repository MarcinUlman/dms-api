package dev.ulman.dmsapi.service;

import dev.ulman.dmsapi.model.Contract;
import dev.ulman.dmsapi.model.Customer;

import java.util.Collection;

public interface CustomerService {

    Collection<Customer> getAllCustomerts();
    Customer getCustomerById(long id);
    void add (Customer newCustomer);
    void delete(long id);
    void update(long id, Customer incomingCustomer);

    Collection<Contract> getContracts(long id);

}
