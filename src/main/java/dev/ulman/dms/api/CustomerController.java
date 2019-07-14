package dev.ulman.dms.api;

import dev.ulman.dms.model.Contract;
import dev.ulman.dms.model.Customer;
import dev.ulman.dms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("api/customers")
@Controller
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers(){
       Collection<Customer> customers = customerService.getAllCustomerts();

       return new ResponseEntity<Collection<Customer>>(customers, HttpStatus.OK);
   }

   @GetMapping(path = "{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") long id){
        Customer customer = customerService.getCustomerById(id);

        if (customer == null){
            return new ResponseEntity<>("Customer dose not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
   }

   @PostMapping
    public ResponseEntity<?> createNewCustomer(@RequestBody Customer newCustomer){

        customerService.add(newCustomer);

        return new ResponseEntity<Customer>(newCustomer, HttpStatus.OK);
   }

   @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteCustorem (@PathVariable("id") long id){

        customerService.delete(id);

        return new ResponseEntity<>("Customer is no longer in the database", HttpStatus.NO_CONTENT);
   }

   @PutMapping(path = "{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") long id, @RequestBody Customer incomingCustomer){

        customerService.update(id, incomingCustomer);

        return new ResponseEntity<Customer>(incomingCustomer, HttpStatus.OK);
   }

   @GetMapping(path = "{id}/contracts")
    public ResponseEntity<?> getContracts(@PathVariable("id") long id){

        Collection<Contract> contracts = customerService.getContracts(id);
        if(contracts == null){
            return new ResponseEntity<>("Customer dose not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<Contract>>(contracts, HttpStatus.OK);
   }
}
