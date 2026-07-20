package com.agroerp.modules.product.service;

import com.agroerp.common.enums.ProductType;
import com.agroerp.exception.BusinessException;
import com.agroerp.exception.ResourceNotFoundException;
import com.agroerp.modules.category.entity.Category;
import com.agroerp.modules.category.repository.CategoryRepository;
import com.agroerp.modules.product.dto.ProductRequest;
import com.agroerp.modules.product.dto.ProductResponse;
import com.agroerp.modules.product.entity.Product;
import com.agroerp.modules.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository  = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public ProductResponse create(ProductRequest request) {
        if (productRepository.existsByCodeIgnoreCase(request.getCode())) {
            throw new BusinessException(
                    "Product code '" + request.getCode() + "' already exists."
            );
        }
        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category", "id", request.getCategoryId()));

        Product product = new Product();
        mapRequestToProduct(request, product, category);
        return ProductResponse.fromEntity(productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product", "id", id));

        productRepository.findByCodeIgnoreCase(request.getCode())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new BusinessException(
                                "Product code '" + request.getCode() + "' already exists."
                        );
                    }
                });

        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category", "id", request.getCategoryId()));

        mapRequestToProduct(request, product, category);
        return ProductResponse.fromEntity(productRepository.save(product));
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getById(Long id) {
        return productRepository.findById(id)
                .map(ProductResponse::fromEntity)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product", "id", id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getAll() {
        return productRepository.findByActiveTrueOrderByNameAsc()
                .stream()
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getAllActive() {
        return productRepository.findByActiveTrueOrderByNameAsc()
                .stream()
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getLowStockProducts() {
        return productRepository.findLowStockProducts()
                .stream()
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product", "id", id));
        product.setActive(false);
        productRepository.save(product);
    }

    private void mapRequestToProduct(ProductRequest request,
                                     Product product,
                                     Category category) {
        product.setName(request.getName().trim());
        product.setCode(request.getCode().trim().toUpperCase());
        product.setProductType(ProductType.valueOf(request.getProductType()));
        product.setCategory(category);
        product.setBrand(request.getBrand());
        product.setUnit(request.getUnit());
        product.setPurchasePrice(request.getPurchasePrice());
        product.setSellingPrice(request.getSellingPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setMinimumStock(request.getMinimumStock());
        product.setDescription(request.getDescription());
        product.setActive(true);
    }
}