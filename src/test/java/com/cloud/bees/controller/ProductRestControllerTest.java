package com.cloud.bees.controller;

import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.cloud.bees.pojo.Product;
import com.cloud.bees.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = { ProductRestController.class })
@ExtendWith(SpringExtension.class)
class ProductRestControllerTest {
    @Autowired
    private ProductRestController productRestController;

    @MockBean
    private ProductService productService;

    /**
     * test read api
     */
    @Test

    void testGetProduct() throws Exception {
        // Arrange
        when(productService.getProduct(Mockito.<String> any()))
                .thenReturn(
                        new Product("42", "Name", "The characteristics of someone or something", 10.0d, 1));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/{productId}",
                "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    /**
     * Method under test:
     * test apply discount  api
     */
    @Test
    void testApplyDiscount() throws Exception {
        // Arrange
        when(productService.applyDiscountOrTax(Mockito.<String> any(), anyDouble(), anyBoolean()))
                .thenReturn(
                        new Product("42", "Name", "The characteristics of someone or something", 10.0d, 1));
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/products/{productId}/apply-discount",
                "42");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("discount", String.valueOf(10.0d));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * test create product
     */
    @Test
    void testCreateProduct() throws Exception {
        // Arrange
        when(productService.createProduct(Mockito.<Product> any()))
                .thenReturn(
                        new Product("42", "Name", "The characteristics of someone or something", 10.0d, 1));
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper
                .writeValueAsString(
                        new Product("42", "Name", "The characteristics of someone or something", 10.0d, 1)));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     *
     * test update product
     */
    @Test
    void testUpdateProduct() throws Exception {
        // Arrange
        doNothing().when(productService).updateProduct(Mockito.<String> any(), Mockito.<Product> any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.put("/products/{productId}",
                        "42")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper
                .writeValueAsString(
                        new Product("42", "Name", "The characteristics of someone or something", 10.0d, 1)));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Product updated successfully"));
    }

    /**
     *  test delete product
     */
    @Test
    void testDeleteProduct() throws Exception {
        // Arrange
        doNothing().when(productService).deleteProduct(Mockito.<String> any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/products/{productId}",
                "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Product deleted successfully"));
    }


    /**
     *  test apply-tax product
     */
    @Test
    void testApplyTax() throws Exception {
        // Arrange
        when(productService.applyDiscountOrTax(Mockito.<String> any(), anyDouble(), anyBoolean()))
                .thenReturn(
                        new Product("42", "Name", "The characteristics of someone or something", 10.0d, 1));
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/products/{productId}/apply-tax", "42");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("tax", String.valueOf(10.0d));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }
}
