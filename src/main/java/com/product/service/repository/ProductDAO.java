package com.product.service.repository;

import com.product.service.dto.ProductRequest;
import com.product.service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDAO extends JpaRepository<Product,Long>, QueryByExampleExecutor<Product> {
    List<Product> findByNameContainingIgnoreCase(String name);

}
