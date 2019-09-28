package dev.ulman.dmsapi.api;


import dev.ulman.dmsapi.model.Product;
import dev.ulman.dmsapi.model.Supplier;
import dev.ulman.dmsapi.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("api/supplier")
@Controller
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<?> getAllSupplier(){
        Collection<Supplier> suppliers = supplierService.getAllSuppliers();
        return new ResponseEntity<Collection<Supplier>>(suppliers, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable("id") long id){
        Supplier supplier = supplierService.getSupplierById(id);

        if(supplier == null)
            return new ResponseEntity<>("Supplier dose not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createSupplier(@RequestBody Supplier newSupplier){

        supplierService.add(newSupplier);

       return new ResponseEntity<Supplier>(newSupplier, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteSuplier(@PathVariable("id") long id){

        supplierService.delete(id);

        return new ResponseEntity<>( "Supplier is no longer in the database ", HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> updateSuppier(@PathVariable("id") long id, @RequestBody Supplier incomingSupplier){

        supplierService.update(id, incomingSupplier);

        return new ResponseEntity<Supplier>(incomingSupplier, HttpStatus.OK);
    }

    @GetMapping(path = "{id}/products")
    public ResponseEntity<?> getProducts(@PathVariable("id") long id){

        Collection<Product> products = supplierService.getSupplierProdacts(id);
        if(products == null)
            return new ResponseEntity<>("Supplier dose not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity<Collection<Product>>(products,HttpStatus.OK);
    }
}
