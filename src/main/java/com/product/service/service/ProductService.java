package com.product.service.service;

import com.product.service.dto.ProductRequest;
import com.product.service.dto.ProductUpdate;
import com.product.service.entity.Product;

import java.util.List;

public interface ProductService {
    public Product registerProduct(ProductRequest request);
    public List<Product> findProductsByName(String name);
    public Product updateProduct(Long idProduct, ProductUpdate product);
    public void deleteProduct(Long idProduct);
    List<Product> findAll();
}
