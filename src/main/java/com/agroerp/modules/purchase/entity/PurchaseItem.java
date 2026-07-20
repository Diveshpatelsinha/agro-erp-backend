package com.agroerp.modules.purchase.entity;

import com.agroerp.modules.product.entity.Product;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "purchase_items")
public class PurchaseItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal quantity;

    @Column(name = "unit_price",
            nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "total_price",
            nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    public PurchaseItem() {}

    public Long getId() { return id; }
    public Purchase getPurchase() { return purchase; }
    public Product getProduct() { return product; }
    public BigDecimal getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public BigDecimal getTotalPrice() { return totalPrice; }

    public void setId(Long id) { this.id = id; }
    public void setPurchase(Purchase purchase) { this.purchase = purchase; }
    public void setProduct(Product product) { this.product = product; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
}