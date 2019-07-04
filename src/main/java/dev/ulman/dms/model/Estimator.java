package dev.ulman.dms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "estimators")
public class Estimator extends Employee {

    @JsonIgnore
    @OneToMany (mappedBy = "estimator", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<Contract> contracts;

    //Getters @ Setters--------------------->
    public Collection<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Collection<Contract> contracts) {
        this.contracts = contracts;
    }
}