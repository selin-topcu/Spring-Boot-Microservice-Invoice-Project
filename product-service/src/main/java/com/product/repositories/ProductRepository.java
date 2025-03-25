package com.product.repositories;

import com.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findAllByProductIdIn(List<String> productIds);
}
