package com.agroerp.modules.sales.dto;

import com.agroerp.modules.sales.entity.Sale;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class SaleResponse {

    private Long id;
    private String invoiceNumber;
    private Long customerId;
    private String customerName;
    private String customerPhone;
    private LocalDate saleDate;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal netAmount;
    private BigDecimal paidAmount;
    private BigDecimal dueAmount;
    private String paymentStatus;
    private String paymentMode;
    private String notes;
    private List<SaleItemResponse> items;
    private LocalDateTime createdAt;

    public SaleResponse() {}

    public static SaleResponse fromEntity(Sale s) {
        SaleResponse r = new SaleResponse();
        r.id             = s.getId();
        r.invoiceNumber  = s.getInvoiceNumber();
        r.customerId     = s.getCustomer().getId();
        r.customerName   = s.getCustomer().getName();
        r.customerPhone  = s.getCustomer().getPhone();
        r.saleDate       = s.getSaleDate();
        r.totalAmount    = s.getTotalAmount();
        r.discountAmount = s.getDiscountAmount();
        r.netAmount      = s.getNetAmount();
        r.paidAmount     = s.getPaidAmount();
        r.dueAmount      = s.getDueAmount();
        r.paymentStatus  = s.getPaymentStatus().name();
        r.paymentMode    = s.getPaymentMode().name();
        r.notes          = s.getNotes();
        r.items          = s.getItems().stream()
                .map(SaleItemResponse::fromEntity)
                .collect(Collectors.toList());
        r.createdAt      = s.getCreatedAt();
        return r;
    }

    public Long getId() { return id; }
    public String getInvoiceNumber() { return invoiceNumber; }
    public Long getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getCustomerPhone() { return customerPhone; }
    public LocalDate getSaleDate() { return saleDate; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public BigDecimal getNetAmount() { return netAmount; }
    public BigDecimal getPaidAmount() { return paidAmount; }
    public BigDecimal getDueAmount() { return dueAmount; }
    public String getPaymentStatus() { return paymentStatus; }
    public String getPaymentMode() { return paymentMode; }
    public String getNotes() { return notes; }
    public List<SaleItemResponse> getItems() { return items; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setInvoiceNumber(String s) { this.invoiceNumber = s; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }
    public void setSaleDate(LocalDate saleDate) { this.saleDate = saleDate; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }
    public void setNetAmount(BigDecimal netAmount) { this.netAmount = netAmount; }
    public void setPaidAmount(BigDecimal paidAmount) { this.paidAmount = paidAmount; }
    public void setDueAmount(BigDecimal dueAmount) { this.dueAmount = dueAmount; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setItems(List<SaleItemResponse> items) { this.items = items; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}