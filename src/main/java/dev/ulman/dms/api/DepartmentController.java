package dev.ulman.dms.api;


import dev.ulman.dms.model.Department;
import dev.ulman.dms.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@RequestMapping("api/depatrment")
@Controller
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllDepartments(){
        Collection<Department> departments = departmentService.gayAllDepartments();
        return new ResponseEntity<Collection<Department>>(departments, HttpStatus.OK);
    }


}
