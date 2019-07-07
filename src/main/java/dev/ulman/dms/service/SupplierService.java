package dev.ulman.dms.service;

import dev.ulman.dms.model.Product;
import dev.ulman.dms.model.Supplier;

import java.util.Collection;

public interface SupplierService {

    Collection<Supplier> getAllSuppliers();
    Supplier getSupplierById(long id);
    void add(Supplier newSupplier);
    void delete(long id);
    void update(long id, Supplier incomingSupplier);

    Collection<Product> getSupplierProdacts(long id);

}
