package dev.ulman.dms.service;

import dev.ulman.dms.model.Contract;
import dev.ulman.dms.model.Employee;
import dev.ulman.dms.model.Offer;

import java.util.Collection;

public interface ContractService {

    Collection<Contract> getAllContracts();
    Contract getContractById(long id);

    void add(Contract contract);
    void delete(long id);
    void  update(long id, Contract contract);

    Collection<Offer> getContractOffers(long id);
    Collection<Employee> getContractTeam(long id);
}
