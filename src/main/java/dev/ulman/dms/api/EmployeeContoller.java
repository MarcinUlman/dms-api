package dev.ulman.dms.api;

import dev.ulman.dms.model.Employee;
import dev.ulman.dms.model.Estimator;
import dev.ulman.dms.model.Trader;
import dev.ulman.dms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("api/employees")
@Controller
public class EmployeeContoller {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeContoller(EmployeeService employeeService) {
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

    @GetMapping(path = {"id"})
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

    @PostMapping(path = "estimator")
    public ResponseEntity<?> createNewEstimator(@RequestBody Estimator estimator){
        employeeService.addEstimator(estimator);
        return new ResponseEntity<Employee>(estimator, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteEmpleyee(@PathVariable("id") long id){
        employeeService.delete(id);

        return new ResponseEntity<>("Employee is no longer in the database", HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "{id")
    public ResponseEntity<?> updeteEmployee(@PathVariable("id") long id, @RequestBody Employee incomingEmployee){
        employeeService.update(id, incomingEmployee);
        return new ResponseEntity<Employee>(incomingEmployee, HttpStatus.OK);
    }


}
