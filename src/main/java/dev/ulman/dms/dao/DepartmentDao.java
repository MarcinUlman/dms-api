package dev.ulman.dms.dao;

import dev.ulman.dms.model.Department;

import java.util.Collection;

public interface DepartmentDao {

    Collection<Department> getAllDepartments();
}
