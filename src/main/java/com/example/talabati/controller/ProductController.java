package com.example.talabati.controller;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.talabati.model.Product;
import com.example.talabati.service.ProductService;

/**
 *
 * @author Blancko
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //// POST Method 
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        try {
            productService.createOrder(product);
            //  productDao.addProduct(product); // this is the service concept 
            return ResponseEntity.ok("Product Created Successfully");
        } catch (Exception e) {

            return ResponseEntity.status(500).body("Error  creating product : >> " + e.getMessage());
        }

    }

    //// GET specific product Method
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
        try {
            Product product = productService.getProductById(id);
            //  Product product = productDao.getProductById(id);
            if (product == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(product);

        } catch (Exception e) {
            System.out.println("Error While getting product number #{product.getId} :>>>" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //// GET all products method
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(products);

        } catch (Exception e) {
            System.out.println("Error while get all products  :>> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        try {
            productService.UpdateProduct(product, id);
            return ResponseEntity.status(HttpStatus.OK).body("Product Updated successfully");
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in updating product  number >> " + id + "   Error message >> " + e.getMessage());

        }

    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        try {
            productService.deleteProduct(id);
            return "Product deleted successfully !";
        } catch (Exception e) {
            return "Error while deleting product" + e.getMessage();
        }

    }

    @ExceptionHandler(RuntimeErrorException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Customer error message :" + ex.getMessage());
    }

    /*     private static Map<String, Product> productRep = new HashMap();

    static {

        Product p1 = new Product(1, "Pizza", 30);
        productRep.put(String.valueOf(p1.getId()), p1);
        Product p2 = new Product(2, "Ice Coffe", 20);
        productRep.put(String.valueOf(p2.getId()), p2);

    } */
    // Get Method
/*     @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProducts() {
        return new ResponseEntity<>(productRep.values(), HttpStatus.OK);
    }

    // Post Method
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        productRep.put(String.valueOf(product.getId()), product);
        return new ResponseEntity<>("Product is create Successfully", HttpStatus.OK);
    }
     */
}
