package com.product.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.service.dto.ProductRequest;
import com.product.service.dto.ProductUpdate;
import com.product.service.entity.Product;
import com.product.service.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc; // simular el HTTP

    @MockBean
    ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturn201() throws Exception {
        Product response = new Product();
        response.setIdProduct(9L);
        response.setName("Bomba de baño");
        response.setPrice(BigDecimal.valueOf(160));

        ProductRequest request = ProductRequest.builder()
                .name("Bomba de baño")
                .price(BigDecimal.valueOf(160))
                .category("Higiene")
                .build();

        //Preparar el Mock(Arrange)

        //Cuando se llame al service, se regresa un product
        //Esta implementación causo problemas con el debug
        //        when(productService.registerProduct(any(ProductRequest.class)))
        //                .thenReturn(response);
        doReturn(response)
                .when(productService)
                        .registerProduct(any(ProductRequest.class));
        // Act + Assert
        //simular la llamada HTTP
        mockMvc.perform(post("/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())//Espero que el controller responda bien
                .andExpect(jsonPath("$.idProduct").value(9L))
                .andExpect(jsonPath("$.name").value("Bomba de baño"));
    }

    @Test
    void shouldReturn400WhenNameisMissing() throws Exception {
        // No se usa mockito
        //Valdar atributos obligatorios en ProductRequest
        ProductRequest request = ProductRequest.builder()
                .price(BigDecimal.valueOf(160))
                .category("Higiene")
                .build();

        // Act + Assert
        //simular la llmada HTTP
        mockMvc.perform(post("/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
        verify(productService,never()).registerProduct(any());
    }

    @Test
    void updateTest200() throws Exception {
        Product response = new Product();
        response.setCategory("face-cleansed");
        response.setPrice(BigDecimal.valueOf(100));

        ProductUpdate request = ProductUpdate.builder()
                .price(BigDecimal.valueOf(100))
                .category("face-cleansed")
                .build();

        doReturn(response)
                .when(productService)
                        .updateProduct(eq(1L),any(ProductUpdate.class));

        // Act + Assert
        //simular la llamada HTTP
        mockMvc.perform(patch("/products/{id}",1L)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(100))
                .andExpect(jsonPath("$.category").value("face-cleansed"));

        verify(productService).updateProduct(eq(1L), any(ProductUpdate.class));
    }
}
