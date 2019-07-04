package dev.ulman.dms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "traders")
public class Trader extends Employee {

    private int level; //mlodszy specjalist, inzynier sprzedazy, dyrektor oddzilu
    @JsonIgnore
    @OneToMany (mappedBy = "trader", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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

    public void setCustomers(Collection<Customer> customers) {
        this.customers = customers;
    }

}