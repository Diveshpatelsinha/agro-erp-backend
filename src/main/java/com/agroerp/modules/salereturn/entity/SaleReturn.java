package com.agroerp.modules.salereturn.entity;

import com.agroerp.modules.customer.entity.Customer;
import com.agroerp.modules.sales.entity.Sale;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sale_returns")
public class SaleReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "return_number",
            nullable = false, unique = true, length = 50)
    private String returnNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    @Column(name = "total_amount",
            nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(length = 255)
    private String reason;

    @Column(length = 255)
    private String notes;

    @OneToMany(mappedBy = "saleReturn",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<SaleReturnItem> items = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public SaleReturn() {}

    public Long getId() { return id; }
    public String getReturnNumber() { return returnNumber; }
    public Sale getSale() { return sale; }
    public Customer getCustomer() { return customer; }
    public LocalDate getReturnDate() { return returnDate; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public String getReason() { return reason; }
    public String getNotes() { return notes; }
    public List<SaleReturnItem> getItems() { return items; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setReturnNumber(String returnNumber) { this.returnNumber = returnNumber; }
    public void setSale(Sale sale) { this.sale = sale; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public void setReason(String reason) { this.reason = reason; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setItems(List<SaleReturnItem> items) { this.items = items; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}