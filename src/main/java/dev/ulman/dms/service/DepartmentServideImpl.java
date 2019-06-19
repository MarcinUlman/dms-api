package dev.ulman.dms.service;

import dev.ulman.dms.dao.DepartmentDao;
import dev.ulman.dms.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DepartmentServideImpl implements DepartmentService {

    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentServideImpl(@Qualifier("DBconnection") DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public Collection<Department> gayAllDepartments() {
        return departmentDao.getAllDepartments();
    }
}
