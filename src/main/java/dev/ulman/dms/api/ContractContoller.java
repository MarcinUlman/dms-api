package dev.ulman.dms.api;

import dev.ulman.dms.model.Contract;
import dev.ulman.dms.model.Employee;
import dev.ulman.dms.model.Offer;
import dev.ulman.dms.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("api/contracts")
@Controller
public class ContractContoller {

    private final ContractService contractService;

    @Autowired
    public ContractContoller(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    public ResponseEntity<?> getAllContracts(){
        Collection<Contract> contracts = contractService.getAllContracts();
        return new ResponseEntity<Collection<Contract>>(contracts, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> getContractById(@PathVariable("id") long id){
        Contract contract = contractService.getContractById(id);
        if(contract == null)
            return new ResponseEntity<>("Contract does not exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<Contract>(contract, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewContract(@RequestBody Contract newContract){
        contractService.add(newContract);
        return new ResponseEntity<Contract>(newContract, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteContreact(@PathVariable("id") long id){
        contractService.delete(id);
        return new ResponseEntity<>("Contract is no longer in the database", HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> updateContract(@PathVariable("id") long id, @RequestBody Contract contract){
        contractService.update(id,contract);
        return new ResponseEntity<Contract>(contract, HttpStatus.OK);
    }

    @GetMapping(path = "{id}/offers")
    public ResponseEntity<?> getContractOffers(@PathVariable("id") long id){
        Contract contract = contractService.getContractById(id);
        if(contract == null)
            return new ResponseEntity<>("Contract does not exist", HttpStatus.NOT_FOUND);
        Collection<Offer> offers = contractService.getContractOffers(id);
        return new ResponseEntity<Collection<Offer>>(offers, HttpStatus.OK);
    }

    @GetMapping(path = "{id}/team")
    public ResponseEntity<?> getContractTeam(@PathVariable("id") long id){
        Contract contract = contractService.getContractById(id);
        if(contract == null)
            return new ResponseEntity<>("Contract does not exist", HttpStatus.NOT_FOUND);
        Collection<Employee> employees = contractService.getContractTeam(id);
        return new ResponseEntity<Collection<Employee>>(employees,HttpStatus.OK);
    }
}
