package com.product.service.service;

import com.product.service.dto.ProductRequest;
import com.product.service.dto.ProductUpdate;
import com.product.service.entity.Product;
import com.product.service.exception.InvalidStockException;
import com.product.service.exception.ProductNotFoundException;
import com.product.service.repository.ProductDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Product registerProduct(ProductRequest request) {
        Product productNew = new Product();
        productNew.setName(request.getName());
        productNew.setDescription(request.getDescription());
        productNew.setPrice(request.getPrice());
        productNew.setCategory(request.getCategory());
        productNew.setActive(true);
        productNew.setStock(0L);
        return productDAO.save(productNew);
    }

    @Override
    public List<Product> findProductsByName(String name) {
        return productDAO.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Product updateProduct(Long idProduct, ProductUpdate request) {

        Product product = productDAO.findById(idProduct).orElseThrow(
                () -> new ProductNotFoundException("Product not found with id: "+ idProduct));

        Optional.ofNullable(request.getName())
                .ifPresent(product::setName);

        Optional.ofNullable(request.getCategory())
                .ifPresent(product::setCategory);

        Optional.ofNullable(request.getPrice())
                .ifPresent(product::setPrice);

        Optional.ofNullable(request.getDescription())
                .ifPresent(product::setDescription);

        Optional.ofNullable(request.getActive())
                .ifPresent(product::setActive);

        Optional.ofNullable(request.getStock()).ifPresent(delta -> {
            long newStock = product.getStock() + delta;
            if (newStock < 0) {
                throw new InvalidStockException("Stock cannot be negative.");
            }
            product.setStock(newStock);
        });

        return  productDAO.save(product);
    }

    @Override
    public void deleteProduct(Long idProduct) {
        if (!productDAO.existsById(idProduct)) {
            throw new ProductNotFoundException("Product not found");
        }
        productDAO.deleteById(idProduct);
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

}
