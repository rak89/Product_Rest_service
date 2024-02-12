package com.cloud.bees.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cloud.bees.exception.ProductNotFoundException;
import com.cloud.bees.pojo.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = { ProductServiceImpl.class })
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    /**
     * test create Product implementation
     */
    @Test
    void testCreateProduct() {
        Product product = new Product("42", "Name", "The characteristics of someone or something", 10.0d, 1);


        Product actualCreateProductResult = productServiceImpl.createProduct(product);

        // Assert
        assertEquals("42", actualCreateProductResult.getProductId());
        assertEquals(3, productServiceImpl.productMap.size());
        assertSame(product, actualCreateProductResult);
    }

    /**
     * test create Product implementation
     */
    @Test
    void testGetProduct() throws ProductNotFoundException {
        // Arrange, Act and Assert
        assertThrows(ProductNotFoundException.class, () -> productServiceImpl.getProduct("10000"));
    }

    /**
     * test update product implementation
     */
    @Test
    void testUpdateProduct() {
        // Arrange, Act and Assert
        assertThrows(ProductNotFoundException.class, () -> productServiceImpl.updateProduct("12345",
                new Product("12345", "Name", "The characteristics of someone or something", 10.0d, 1)));
    }

    /**
     * test delete product  implementation
     */
    @Test
    void testDeleteProduct() {
        // Arrange, Act and Assert
        assertThrows(ProductNotFoundException.class, () -> productServiceImpl.deleteProduct("-123456"));
        assertThrows(ProductNotFoundException.class, () -> productServiceImpl.deleteProduct("Product Id"));
    }

    /**
     *
     * test applyDiscountOrTax  implementation
     */
    @Test
    void testApplyDiscountOrTax() {
        // Arrange, Act and Assert
        assertThrows(ProductNotFoundException.class,
                () -> productServiceImpl.applyDiscountOrTax("1234567", 10.0d, true));
    }
}
