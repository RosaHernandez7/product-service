package com.product.service.controller;

import com.product.service.dto.ProductRequest;
import com.product.service.dto.ProductUpdate;
import com.product.service.entity.Product;
import com.product.service.exception.ExceptionBusiness;
import com.product.service.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public ResponseEntity<Product> registerProduct(@Valid @RequestBody  ProductRequest request) {
        Product response = productService.registerProduct(request);
        log.info("Product created successfully. id={}, name={}",
                response.getIdProduct(), response.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@RequestParam(required = false) String name){
        List<Product> response = (name == null || name.isBlank())
                ? productService.findAll()
                : productService.findProductsByName(name);

        log.info("GET /products - returned {} products", response.size());

        return  response.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long idProduct,
                                                 @Valid @RequestBody ProductUpdate request){
        Product response = new Product();
        HttpStatus status = HttpStatus.OK;
        try{
            response =  productService.updateProduct(idProduct, request);
            log.info("Product updated successfully. id={}",idProduct);

        } catch (ExceptionBusiness e) {
            status = e.getStatus();
            log.error(e.getMessage(), e);
        }
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long idProduct){
        productService.deleteProduct(idProduct);
        log.info("Product deleted successfully. id={}",idProduct);
        return ResponseEntity.noContent().build();
    }

}
