package dev.ulman.dmsapi.dao;

import dev.ulman.dmsapi.model.Product;
import dev.ulman.dmsapi.model.Supplier;

import java.util.Collection;

public interface SupplierDao {

    Collection<Supplier> getAllSuppliers();
    Supplier getSupplierById(long id);
    void add(Supplier newSupplier);
    void delete(long id);
    void update(long id, Supplier incomingSupplier);

    Collection<Product> getSupplierProdacts(long id);

}
