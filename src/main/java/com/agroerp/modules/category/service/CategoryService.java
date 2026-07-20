package com.agroerp.modules.category.service;

import com.agroerp.modules.category.dto.CategoryRequest;
import com.agroerp.modules.category.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CategoryRequest request);
    CategoryResponse update(Long id, CategoryRequest request);
    CategoryResponse getById(Long id);
    List<CategoryResponse> getAll();
    List<CategoryResponse> getAllActive();
    void delete(Long id);
}