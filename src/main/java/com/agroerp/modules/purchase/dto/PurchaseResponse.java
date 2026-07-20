package com.agroerp.modules.purchase.dto;

import com.agroerp.modules.purchase.entity.Purchase;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseResponse {

    private Long id;
    private String invoiceNumber;
    private Long supplierId;
    private String supplierName;
    private LocalDate purchaseDate;
    private BigDecimal totalAmount;
    private BigDecimal paidAmount;
    private BigDecimal dueAmount;
    private String paymentStatus;
    private String notes;
    private List<PurchaseItemResponse> items;
    private LocalDateTime createdAt;

    public PurchaseResponse() {}

    public static PurchaseResponse fromEntity(Purchase p) {
        PurchaseResponse r = new PurchaseResponse();
        r.id            = p.getId();
        r.invoiceNumber = p.getInvoiceNumber();
        r.supplierId    = p.getSupplier().getId();
        r.supplierName  = p.getSupplier().getName();
        r.purchaseDate  = p.getPurchaseDate();
        r.totalAmount   = p.getTotalAmount();
        r.paidAmount    = p.getPaidAmount();
        r.dueAmount     = p.getDueAmount();
        r.paymentStatus = p.getPaymentStatus().name();
        r.notes         = p.getNotes();
        r.items         = p.getItems().stream()
                .map(PurchaseItemResponse::fromEntity)
                .collect(Collectors.toList());
        r.createdAt     = p.getCreatedAt();
        return r;
    }

    public Long getId() { return id; }
    public String getInvoiceNumber() { return invoiceNumber; }
    public Long getSupplierId() { return supplierId; }
    public String getSupplierName() { return supplierName; }
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public BigDecimal getPaidAmount() { return paidAmount; }
    public BigDecimal getDueAmount() { return dueAmount; }
    public String getPaymentStatus() { return paymentStatus; }
    public String getNotes() { return notes; }
    public List<PurchaseItemResponse> getItems() { return items; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public void setPaidAmount(BigDecimal paidAmount) { this.paidAmount = paidAmount; }
    public void setDueAmount(BigDecimal dueAmount) { this.dueAmount = dueAmount; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setItems(List<PurchaseItemResponse> items) { this.items = items; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}