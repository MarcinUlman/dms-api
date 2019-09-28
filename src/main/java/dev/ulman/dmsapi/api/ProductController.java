package dev.ulman.dmsapi.api;

import dev.ulman.dmsapi.model.Product;
import dev.ulman.dmsapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("api/products")
@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        Collection<Product> products = productService.getAllProcusts();
        return new ResponseEntity<Collection<Product>>(products, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") long id){
        Product product = productService.getProductById(id);

        if (product == null)
                return new ResponseEntity<>("Product dose not exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewProduct(@RequestBody Product newProduct){
        productService.add(newProduct);

        return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") long id){
        productService.delete(id);

        return new ResponseEntity<>("Department is no longer in the database", HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> upgradeProduct(@PathVariable("id") long id, @RequestBody Product incomingProduct){
        productService.upgrade(id, incomingProduct);

        return new ResponseEntity<Product>(incomingProduct, HttpStatus.OK);
    }

}
