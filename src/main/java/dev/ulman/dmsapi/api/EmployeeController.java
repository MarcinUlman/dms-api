package dev.ulman.dmsapi.api;

import dev.ulman.dmsapi.model.Employee;
import dev.ulman.dmsapi.model.Estimator;
import dev.ulman.dmsapi.model.Trader;
import dev.ulman.dmsapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("employees")
@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees(){

        Collection<Employee> employees = employeeService.getAllEmployees();

        return new ResponseEntity<Collection<Employee>>(employees, HttpStatus.OK);
    }

    @GetMapping (path = "traders")
    public ResponseEntity<?> getAllTraders(){
        Collection<Trader> traders = employeeService.getAllTraders();
        return new ResponseEntity<Collection<Trader>>(traders, HttpStatus.OK);
    }
    @GetMapping(path = "estimators")
    public ResponseEntity<?> getAllEstimators(){
        Collection<Estimator> estimators = employeeService.getAllEstimators();
        return new ResponseEntity<Collection<Estimator>>(estimators, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") long id){
        Employee employee = employeeService.getEmployeeById(id);
        if(employee == null)
            return new ResponseEntity<>("Department dose not exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @PostMapping(path = "traders")
    public ResponseEntity<?> createNewTrader(@RequestBody Trader trader){
        employeeService.addTrader(trader);
        return new ResponseEntity<Trader>(trader,HttpStatus.CREATED);
    }

    @PostMapping(path = "estimators")
    public ResponseEntity<?> createNewEstimator(@RequestBody Estimator estimator){
        employeeService.addEstimator(estimator);
        return new ResponseEntity<Employee>(estimator, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") long id){
        employeeService.delete(id);

        return new ResponseEntity<>("Employee is no longer in the database", HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "traders/{id}")
    public ResponseEntity<?> updateTrader(@PathVariable("id") long id, @RequestBody Trader incomingEmployee){
        employeeService.updateTrader(id, incomingEmployee);
        return new ResponseEntity<Employee>(incomingEmployee, HttpStatus.OK);
    }

    @PutMapping(path = "estimators/{id}")
    public ResponseEntity<?> updateEstimator(@PathVariable("id") long id, @RequestBody Estimator incomingEmployee){
        employeeService.updateEstimator(id, incomingEmployee);
        return new ResponseEntity<Employee>(incomingEmployee, HttpStatus.OK);
    }
}
