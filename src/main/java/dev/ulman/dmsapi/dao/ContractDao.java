package dev.ulman.dmsapi.dao;

import dev.ulman.dmsapi.model.Contract;
import dev.ulman.dmsapi.model.Employee;
import dev.ulman.dmsapi.model.Offer;

import java.util.Collection;

public interface ContractDao {

    Collection<Contract> getAllContracts();
    Contract getContractById(long id);

    void add(Contract contract);
    void delete(long id);
    void update(long id, Contract contract);

    Collection<Offer> getContractOffers(long id);
    Collection<Employee> getContractTeam(long id);
}
