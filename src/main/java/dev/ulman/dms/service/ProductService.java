package dev.ulman.dms.service;

import dev.ulman.dms.model.Product;

import java.util.Collection;

public interface ProductService {

    Collection<Product> getAllProcusts();
    Product getProductById(long id);
    void add(Product product);
    void delete(long id);
    void upgrade(long id, Product incomingProduct);
}
