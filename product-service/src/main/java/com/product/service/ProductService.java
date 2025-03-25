package com.product.service;


import com.api.gateway.dto.ProductDTO;
import com.api.gateway.dto.ProductRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.entities.Product;
import com.product.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    public void storeAllProducts(ProductRequest productRequest) {

        List<ProductDTO> productDTOList = productRequest.getProductDTOList();
        List<Product> products = objectMapper.convertValue(productDTOList, new TypeReference<>() {});

        productRepository.saveAll(products);
    }

}
