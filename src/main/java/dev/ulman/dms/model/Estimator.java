package dev.ulman.dms.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Estimator extends Employee {

    @OneToMany (mappedBy = "contract_id")
    private Collection<Contract> contracts;

    //Getters @ Setters--------------------->
    public Collection<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Collection<Contract> contracts) {
        this.contracts = contracts;
    }
}