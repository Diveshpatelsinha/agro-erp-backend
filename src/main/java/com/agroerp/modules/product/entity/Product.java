package com.agroerp.modules.product.entity;

import com.agroerp.common.enums.ProductType;
import com.agroerp.modules.category.entity.Category;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type", nullable = false, length = 20)
    private ProductType productType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(length = 100)
    private String brand;

    @Column(nullable = false, length = 30)
    private String unit;

    @Column(name = "purchase_price", nullable = false,
            precision = 10, scale = 2)
    private BigDecimal purchasePrice = BigDecimal.ZERO;

    @Column(name = "selling_price", nullable = false,
            precision = 10, scale = 2)
    private BigDecimal sellingPrice = BigDecimal.ZERO;

    @Column(name = "stock_quantity", nullable = false,
            precision = 10, scale = 2)
    private BigDecimal stockQuantity = BigDecimal.ZERO;

    @Column(name = "minimum_stock", nullable = false,
            precision = 10, scale = 2)
    private BigDecimal minimumStock = BigDecimal.ZERO;

    @Column(length = 255)
    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Product() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCode() { return code; }
    public ProductType getProductType() { return productType; }
    public Category getCategory() { return category; }
    public String getBrand() { return brand; }
    public String getUnit() { return unit; }
    public BigDecimal getPurchasePrice() { return purchasePrice; }
    public BigDecimal getSellingPrice() { return sellingPrice; }
    public BigDecimal getStockQuantity() { return stockQuantity; }
    public BigDecimal getMinimumStock() { return minimumStock; }
    public String getDescription() { return description; }
    public boolean isActive() { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCode(String code) { this.code = code; }
    public void setProductType(ProductType productType) { this.productType = productType; }
    public void setCategory(Category category) { this.category = category; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setUnit(String unit) { this.unit = unit; }
    public void setPurchasePrice(BigDecimal purchasePrice) { this.purchasePrice = purchasePrice; }
    public void setSellingPrice(BigDecimal sellingPrice) { this.sellingPrice = sellingPrice; }
    public void setStockQuantity(BigDecimal stockQuantity) { this.stockQuantity = stockQuantity; }
    public void setMinimumStock(BigDecimal minimumStock) { this.minimumStock = minimumStock; }
    public void setDescription(String description) { this.description = description; }
    public void setActive(boolean active) { this.active = active; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}