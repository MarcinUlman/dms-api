package dev.ulman.dms.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private boolean active;
    private String roles = "";
    private String permissions = "";

    @OneToOne
    private Employee employee;

    public User(String username, String password, String roles, String permissions, Employee employee) {
        this.username = username;
        this.password = password;
        this.active = true;
        this.roles = roles;
        this.permissions = permissions;
        this.employee = employee;
    }

    protected User(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<String> getRolesList() {
        if (roles.length() > 0)
            return Arrays.asList(this.roles.split(","));
        return new ArrayList<>();
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<String> getPermissionsList() {
        if (permissions.length() > 0)
            return Arrays.asList(this.permissions.split(","));
        return new ArrayList<>();
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
