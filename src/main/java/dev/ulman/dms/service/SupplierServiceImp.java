package dev.ulman.dms.service;

import dev.ulman.dms.dao.SupplierDao;
import dev.ulman.dms.model.Product;
import dev.ulman.dms.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class SupplierServiceImp implements SupplierService {

    private final SupplierDao supplierDao;

    @Autowired
    public SupplierServiceImp(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    @Override
    public Collection<Supplier> getAllSuppliers() {
        return supplierDao.getAllSuppliers();
    }

    @Override
    public Supplier getSupplierById(long id) {
        return supplierDao.getSupplierById(id);
    }

    @Override
    public void add(Supplier newSupplier) {
        supplierDao.add(newSupplier);
    }

    @Override
    public void delete(long id) {
        supplierDao.delete(id);
    }

    @Override
    public void update(long id, Supplier incomingSupplier) {
        supplierDao.update(id, incomingSupplier);
    }

    @Override
    public Collection<Product> getSupplierProdacts(long id) {
        return supplierDao.getSupplierProdacts(id);
    }
}
