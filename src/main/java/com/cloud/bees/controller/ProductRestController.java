package com.cloud.bees.controller;

import com.cloud.bees.exception.ProductNotFoundException;
import com.cloud.bees.pojo.Product;
import com.cloud.bees.pojo.ProductRestErrorResponse;
import com.cloud.bees.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for Product
 *
 */

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);

    @Autowired
    private ProductService productService;


    @GetMapping("/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable(value="productId")  String productId) {
        Product product = productService.getProduct(productId);
        LOGGER.info("Product read successfully");
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public  ResponseEntity<?> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        LOGGER.info("Product created successfully");
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable String productId, @RequestBody Product product) {
        productService.updateProduct(productId, product);
        LOGGER.info("Product updated successfully");
        return ResponseEntity.ok("Product updated successfully");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
        LOGGER.info("Product deleted successfully");
        return ResponseEntity.ok("Product deleted successfully");
    }
    // request url example==http://localhost:8080/productservice/products/2/apply-discount?discount=10.0

    @PostMapping("/{productId}/apply-discount")
    public ResponseEntity<Product> applyDiscount(@PathVariable String productId, @RequestParam double discount) {
        Product updatedProduct = productService.applyDiscountOrTax(productId, discount, true);
        LOGGER.info("Applied discount on product successfully");
        return ResponseEntity.ok(updatedProduct);
    }
  // request url example== http://localhost:8080/productservice/products/1/apply-tax?tax=10.0

    @PostMapping("/{productId}/apply-tax")
    public ResponseEntity<Product> applyTax(@PathVariable String productId, @RequestParam double tax) {
        Product updatedProduct = productService.applyDiscountOrTax(productId, tax, false);
        LOGGER.info("Applied tax on product successfully");
        return ResponseEntity.ok(updatedProduct);
    }



}
