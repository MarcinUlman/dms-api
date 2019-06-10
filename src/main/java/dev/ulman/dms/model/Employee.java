package dev.ulman.dms.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public abstract class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long EmployeeId;
    private String name;
    private String surname;

    private Date employmenDate;
    private BigDecimal salary;
    @ManyToOne
    private Department department;
    private int phoneNumber;
    private String email;

    public long getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(long employeeId) {
        EmployeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getEmploymenDate() {
        return employmenDate;
    }

    public void setEmploymenDate(Date employmenDate) {
        this.employmenDate = employmenDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}