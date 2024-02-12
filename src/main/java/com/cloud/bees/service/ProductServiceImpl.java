package com.cloud.bees.service;

import com.cloud.bees.exception.ProductNotFoundException;
import com.cloud.bees.pojo.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER=LoggerFactory.getLogger(ProductServiceImpl.class);

    Map<String, Product> productMap = new ConcurrentHashMap<String, Product>() {
        {
            put("1", new Product("1", "Smartphone", "High-end smartphone with advanced features", 10000.00,
                    50));
            put("2", new Product("2", "Laptop", "High-performance laptop with latest specifications",
                    100000.00, 20));
        }
    };

    @Override
    public Product createProduct(Product product) {
        String productId = null;
        if (null != product.getProductId()) {
            productId = product.getProductId();
        } else {
            productId = UUID.randomUUID().toString();
        }
        product.setProductId(productId);
        productMap.put(productId, product);
        return product;
    }

    @Override
    public Product getProduct(String productId) throws ProductNotFoundException {
        if (!productMap.containsKey(productId)) {
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }
        return productMap.get(productId);
    }

    @Override
    public void updateProduct(String productId, Product updatedProduct) {
        if (!productMap.containsKey(productId)) {
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }
        updatedProduct.setProductId(productId);
        productMap.put(productId, updatedProduct);
    }

    @Override
    public void deleteProduct(String productId) {
        if (!productMap.containsKey(productId)) {
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }
        productMap.remove(productId);
    }

    @Override
    public Product applyDiscountOrTax(String productId, double percentageOrRate, boolean isDiscount) {
        if (!productMap.containsKey(productId)) {
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }
        Product product = productMap.get(productId);
        if (isDiscount) {
            product.setPrice(product.getPrice() * (1 - (percentageOrRate / 100)));
        } else {
            product.setPrice(product.getPrice() * (1 + (percentageOrRate / 100)));
        }
        return productMap.get(productId);
    }
}

