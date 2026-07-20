package com.agroerp.modules.product.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ProductRequest {

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 150, message = "Name must be between 2 and 150 characters")
    private String name;

    @NotBlank(message = "Product code is required")
    @Size(min = 2, max = 50, message = "Code must be between 2 and 50 characters")
    private String code;

    @NotBlank(message = "Product type is required")
    @Pattern(regexp = "FERTILIZER|SEED|PESTICIDE",
            message = "Type must be FERTILIZER, SEED or PESTICIDE")
    private String productType;

    @NotNull(message = "Category is required")
    private Long categoryId;

    private String brand;

    @NotBlank(message = "Unit is required")
    private String unit;

    @NotNull(message = "Purchase price is required")
    @DecimalMin(value = "0.0", message = "Purchase price cannot be negative")
    private BigDecimal purchasePrice;

    @NotNull(message = "Selling price is required")
    @DecimalMin(value = "0.0", message = "Selling price cannot be negative")
    private BigDecimal sellingPrice;

    @NotNull(message = "Stock quantity is required")
    @DecimalMin(value = "0.0", message = "Stock cannot be negative")
    private BigDecimal stockQuantity;

    @NotNull(message = "Minimum stock is required")
    @DecimalMin(value = "0.0", message = "Minimum stock cannot be negative")
    private BigDecimal minimumStock;

    private String description;

    public ProductRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getProductType() { return productType; }
    public void setProductType(String productType) { this.productType = productType; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public BigDecimal getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(BigDecimal purchasePrice) { this.purchasePrice = purchasePrice; }

    public BigDecimal getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(BigDecimal sellingPrice) { this.sellingPrice = sellingPrice; }

    public BigDecimal getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(BigDecimal stockQuantity) { this.stockQuantity = stockQuantity; }

    public BigDecimal getMinimumStock() { return minimumStock; }
    public void setMinimumStock(BigDecimal minimumStock) { this.minimumStock = minimumStock; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}