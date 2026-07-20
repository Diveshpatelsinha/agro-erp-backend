package com.agroerp.modules.product.repository;

import com.agroerp.common.enums.ProductType;
import com.agroerp.modules.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByActiveTrueOrderByNameAsc();

    List<Product> findByProductTypeAndActiveTrueOrderByNameAsc(
            ProductType productType);

    List<Product> findByCategoryIdAndActiveTrueOrderByNameAsc(
            Long categoryId);

    Optional<Product> findByCodeIgnoreCase(String code);

    boolean existsByCodeIgnoreCase(String code);

    @Query("SELECT p FROM Product p WHERE p.active = true " +
            "AND p.stockQuantity <= p.minimumStock")
    List<Product> findLowStockProducts();
}