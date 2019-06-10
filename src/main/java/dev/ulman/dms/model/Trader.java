package dev.ulman.dms.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.List;

@Entity
public class Trader extends Employee {

    private int level; //mlodszy specjalist, inzynier sprzedazy, dyrektor oddzilu
    @OneToMany
    private Collection<Customer> customers;


    //Getters @ Setters--------------------->
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Collection<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

}