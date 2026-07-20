package com.agroerp.modules.product.dto;

import com.agroerp.modules.product.entity.Product;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductResponse {

    private Long id;
    private String name;
    private String code;
    private String productType;
    private Long categoryId;
    private String categoryName;
    private String brand;
    private String unit;
    private BigDecimal purchasePrice;
    private BigDecimal sellingPrice;
    private BigDecimal stockQuantity;
    private BigDecimal minimumStock;
    private String description;
    private boolean active;
    private boolean lowStock;
    private LocalDateTime createdAt;

    public ProductResponse() {}

    public static ProductResponse fromEntity(Product p) {
        ProductResponse r = new ProductResponse();
        r.id            = p.getId();
        r.name          = p.getName();
        r.code          = p.getCode();
        r.productType   = p.getProductType().name();
        r.categoryId    = p.getCategory().getId();
        r.categoryName  = p.getCategory().getName();
        r.brand         = p.getBrand();
        r.unit          = p.getUnit();
        r.purchasePrice = p.getPurchasePrice();
        r.sellingPrice  = p.getSellingPrice();
        r.stockQuantity = p.getStockQuantity();
        r.minimumStock  = p.getMinimumStock();
        r.description   = p.getDescription();
        r.active        = p.isActive();
        r.lowStock      = p.getStockQuantity()
                .compareTo(p.getMinimumStock()) <= 0;
        r.createdAt     = p.getCreatedAt();
        return r;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCode() { return code; }
    public String getProductType() { return productType; }
    public Long getCategoryId() { return categoryId; }
    public String getCategoryName() { return categoryName; }
    public String getBrand() { return brand; }
    public String getUnit() { return unit; }
    public BigDecimal getPurchasePrice() { return purchasePrice; }
    public BigDecimal getSellingPrice() { return sellingPrice; }
    public BigDecimal getStockQuantity() { return stockQuantity; }
    public BigDecimal getMinimumStock() { return minimumStock; }
    public String getDescription() { return description; }
    public boolean isActive() { return active; }
    public boolean isLowStock() { return lowStock; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCode(String code) { this.code = code; }
    public void setProductType(String productType) { this.productType = productType; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setUnit(String unit) { this.unit = unit; }
    public void setPurchasePrice(BigDecimal purchasePrice) { this.purchasePrice = purchasePrice; }
    public void setSellingPrice(BigDecimal sellingPrice) { this.sellingPrice = sellingPrice; }
    public void setStockQuantity(BigDecimal stockQuantity) { this.stockQuantity = stockQuantity; }
    public void setMinimumStock(BigDecimal minimumStock) { this.minimumStock = minimumStock; }
    public void setDescription(String description) { this.description = description; }
    public void setActive(boolean active) { this.active = active; }
    public void setLowStock(boolean lowStock) { this.lowStock = lowStock; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}