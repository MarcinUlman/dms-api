package dev.ulman.dms.service;

import dev.ulman.dms.dao.ContractDao;
import dev.ulman.dms.model.Contract;
import dev.ulman.dms.model.Employee;
import dev.ulman.dms.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;

@Service
public class ContractServiceImp implements ContractService {

    private final ContractDao contractDao;

    @Autowired
    public ContractServiceImp(ContractDao contractDao) {
        this.contractDao = contractDao;
    }

    @Override
    public Collection<Contract> getAllContracts() {
        return contractDao.getAllContracts();
    }

    @Override
    public Contract getContractById(long id) {
        return contractDao.getContractById(id);
    }

    @Override
    public void add(Contract contract) {
        contract.setCreatedDate(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("PLT", ZoneId.SHORT_IDS))));
        contractDao.add(contract);
    }

    @Override
    public void delete(long id) {
        contractDao.delete(id);
    }

    @Override
    public void update(long id, Contract contract) {
        contract.setCompletedDate(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("PLT", ZoneId.SHORT_IDS))));
        contractDao.update(id, contract);
    }

    @Override
    public Collection<Offer> getContractOffers(long id) {
        return contractDao.getContractOffers(id);
    }

    @Override
    public Collection<Employee> getContractTeam(long id) {
        return contractDao.getContractTeam(id);
    }
}
