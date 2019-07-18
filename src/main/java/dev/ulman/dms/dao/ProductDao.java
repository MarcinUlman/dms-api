package dev.ulman.dms.dao;

import dev.ulman.dms.model.Product;

import java.util.Collection;

public interface ProductDao {

    Collection<Product> getAllProcusts();
    Product getProductById(long id);
    void add(Product product);
    void delete(long id);
    void upgrade(long id, Product incomingProduct);

}
