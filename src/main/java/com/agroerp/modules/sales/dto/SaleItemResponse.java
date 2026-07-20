package com.agroerp.modules.sales.dto;

import com.agroerp.modules.sales.entity.SaleItem;
import java.math.BigDecimal;

public class SaleItemResponse {

    private Long id;
    private Long productId;
    private String productName;
    private String productCode;
    private String unit;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    public SaleItemResponse() {}

    public static SaleItemResponse fromEntity(SaleItem item) {
        SaleItemResponse r = new SaleItemResponse();
        r.id          = item.getId();
        r.productId   = item.getProduct().getId();
        r.productName = item.getProduct().getName();
        r.productCode = item.getProduct().getCode();
        r.unit        = item.getProduct().getUnit();
        r.quantity    = item.getQuantity();
        r.unitPrice   = item.getUnitPrice();
        r.totalPrice  = item.getTotalPrice();
        return r;
    }

    public Long getId() { return id; }
    public Long getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getProductCode() { return productCode; }
    public String getUnit() { return unit; }
    public BigDecimal getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public BigDecimal getTotalPrice() { return totalPrice; }

    public void setId(Long id) { this.id = id; }
    public void setProductId(Long productId) { this.productId = productId; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
    public void setUnit(String unit) { this.unit = unit; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
}