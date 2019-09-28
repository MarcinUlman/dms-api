package dev.ulman.dmsapi.service;

import dev.ulman.dmsapi.model.Product;

import java.util.Collection;

public interface ProductService {

    Collection<Product> getAllProcusts();
    Product getProductById(long id);
    void add(Product product);
    void delete(long id);
    void upgrade(long id, Product incomingProduct);
}
