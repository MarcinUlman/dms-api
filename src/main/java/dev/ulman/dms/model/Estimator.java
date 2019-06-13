package dev.ulman.dms.model;

import dev.ulman.dms.model.Employee;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.List;

@Entity
public class Estimator extends Employee {

    @OneToMany (mappedBy = "contract_id")
    private Collection<Contract> contracts;

    //Getters @ Setters--------------------->
    public Collection<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }
}