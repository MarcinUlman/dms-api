package dev.ulman.dms.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "estimators")
public class Estimator extends Employee {

    @OneToMany (mappedBy = "estimator")
    private Collection<Contract> contracts;

    //Getters @ Setters--------------------->
    public Collection<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Collection<Contract> contracts) {
        this.contracts = contracts;
    }
}