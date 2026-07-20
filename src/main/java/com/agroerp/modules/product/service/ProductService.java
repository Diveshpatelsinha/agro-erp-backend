package com.agroerp.modules.product.service;

import com.agroerp.modules.product.dto.ProductRequest;
import com.agroerp.modules.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest request);
    ProductResponse update(Long id, ProductRequest request);
    ProductResponse getById(Long id);
    List<ProductResponse> getAll();
    List<ProductResponse> getAllActive();
    List<ProductResponse> getLowStockProducts();
    void delete(Long id);
}