package com.agroerp.modules.category.service;

import com.agroerp.exception.BusinessException;
import com.agroerp.exception.ResourceNotFoundException;
import com.agroerp.modules.category.dto.CategoryRequest;
import com.agroerp.modules.category.dto.CategoryResponse;
import com.agroerp.modules.category.entity.Category;
import com.agroerp.modules.category.repository.CategoryRepository;
import com.agroerp.modules.product.dto.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public CategoryResponse create(CategoryRequest request) {
        if (categoryRepository.existsByNameIgnoreCase(request.getName())) {
            throw new BusinessException(
                    "Category '" + request.getName() + "' already exists."
            );
        }
        Category category = new Category();
        category.setName(request.getName().trim());
        category.setDescription(request.getDescription());
        category.setActive(true);
        return CategoryResponse.fromEntity(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public CategoryResponse update(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "id", id)
                );

        categoryRepository.findByNameIgnoreCase(request.getName())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new BusinessException(
                                "Category '" + request.getName() + "' already exists."
                        );
                    }
                });

        category.setName(request.getName().trim());
        category.setDescription(request.getDescription());
        return CategoryResponse.fromEntity(categoryRepository.save(category));
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getById(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryResponse::fromEntity)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "id", id)
                );
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> getAll() {
        return categoryRepository.findByActiveTrueOrderByNameAsc()
                .stream()
                .map(CategoryResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllActive() {
        return categoryRepository.findByActiveTrueOrderByNameAsc()
                .stream()
                .map(CategoryResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "id", id)
                );
        category.setActive(false);
        categoryRepository.save(category);
    }
}