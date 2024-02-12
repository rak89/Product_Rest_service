package com.cloud.bees.service;

import com.cloud.bees.pojo.Product;

public interface ProductService {

    Product createProduct(Product product);

    Product getProduct(String productId);

    void updateProduct(String productId, Product product);

    void deleteProduct(String productId);

    Product applyDiscountOrTax(String productId, double percentageOrRate, boolean isDiscount);

}
