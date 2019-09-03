package dev.ulman.dms.api;


import dev.ulman.dms.model.Department;
import dev.ulman.dms.model.Employee;
import dev.ulman.dms.model.Estimator;
import dev.ulman.dms.model.Trader;
import dev.ulman.dms.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("api/departments")
@Controller
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public String getAllDepartments(Model model){
        Collection<Department> departments = departmentService.getAllDepartments();
//        return new ResponseEntity<Collection<Department>>(departments, HttpStatus.OK);
        model.addAttribute("departments", departments);

        return "departmentsList";
    }

    @GetMapping(path = "{id}")
    public String getDepartmentById(@PathVariable("id") long id, Model model){
        Department department = departmentService.getDepartmentById(id);

        if (department == null)
//            return new ResponseEntity<>("Department dose not exist", HttpStatus.NOT_FOUND);
            return "error/error-404";

//        return new ResponseEntity<Department>(department, HttpStatus.OK);

        model.addAttribute("department", department);

        return "department";
    }

    @PostMapping
    public ResponseEntity<?> createNewDepartment(@RequestBody Department department){

        departmentService.add(department);

        return new ResponseEntity<Department>(department, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") long id){

        departmentService.deleteDepartment(id);

        return new ResponseEntity<>( "Department is no longer in the database ", HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable("id") long id, @RequestBody Department incomingDepartment){

        departmentService.update(id, incomingDepartment);

        return new ResponseEntity<Department>(incomingDepartment, HttpStatus.OK);
    }

    @GetMapping(path = "{id}/employees")
    public ResponseEntity<?> getEmployees(@PathVariable("id") long id){

        Collection<Employee> employees = departmentService.getEmployees(id);
        if(employees == null)
            return new ResponseEntity<>("Department dose not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity<Collection<Employee>>(employees,HttpStatus.OK);
    }

    @GetMapping(path = "{id}/traders")
    public ResponseEntity<?> getTraders(@PathVariable("id") long id){

        Department department = departmentService.getDepartmentById(id);
        if(department == null)
            return new ResponseEntity<>("Department dose not exist", HttpStatus.NOT_FOUND);

        Collection<Trader> traders = departmentService.getTraders(id);

        return new ResponseEntity<Collection<Trader>>(traders, HttpStatus.OK);
    }

    @GetMapping(path = "{id}/estimators")
    public ResponseEntity<?> getEstimators(@PathVariable("id") long id){

        Department department = departmentService.getDepartmentById(id);
        if(department == null)
            return new ResponseEntity<>("Department dose not exist", HttpStatus.NOT_FOUND);

        Collection<Estimator> estimators = departmentService.getEstimetors(id);

        return new ResponseEntity<Collection<Estimator>>(estimators, HttpStatus.OK);
    }
}
