package dev.ulman.dms.service;

import dev.ulman.dms.dao.ProductDao;
import dev.ulman.dms.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductServiceImp implements ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductServiceImp(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Collection<Product> getAllProcusts() {
        return productDao.getAllProcusts();
    }

    @Override
    public Product getProductById(long id) {
        return productDao.getProductById(id);
    }

    @Override
    public void add(Product product) {
        productDao.add(product);
    }

    @Override
    public void delete(long id) {
        productDao.delete(id);
    }

    @Override
    public void upgrade(long id, Product incomingProduct) {
        productDao.upgrade(id, incomingProduct);
    }
}
