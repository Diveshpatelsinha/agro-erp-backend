package com.agroerp.modules.payment.dto;

import com.agroerp.modules.payment.entity.Payment;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PaymentResponse {

    private Long id;
    private String paymentNumber;
    private Long customerId;
    private String customerName;
    private String customerPhone;
    private Long saleId;
    private String saleInvoiceNumber;
    private LocalDate paymentDate;
    private BigDecimal amount;
    private String paymentMode;
    private String referenceNumber;
    private String notes;
    private LocalDateTime createdAt;

    public PaymentResponse() {}

    public static PaymentResponse fromEntity(Payment p) {
        PaymentResponse r = new PaymentResponse();
        r.id                = p.getId();
        r.paymentNumber     = p.getPaymentNumber();
        r.customerId        = p.getCustomer().getId();
        r.customerName      = p.getCustomer().getName();
        r.customerPhone     = p.getCustomer().getPhone();
        r.saleId            = p.getSale() != null
                ? p.getSale().getId() : null;
        r.saleInvoiceNumber = p.getSale() != null
                ? p.getSale().getInvoiceNumber() : null;
        r.paymentDate       = p.getPaymentDate();
        r.amount            = p.getAmount();
        r.paymentMode       = p.getPaymentMode().name();
        r.referenceNumber   = p.getReferenceNumber();
        r.notes             = p.getNotes();
        r.createdAt         = p.getCreatedAt();
        return r;
    }

    public Long getId() { return id; }
    public String getPaymentNumber() { return paymentNumber; }
    public Long getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getCustomerPhone() { return customerPhone; }
    public Long getSaleId() { return saleId; }
    public String getSaleInvoiceNumber() { return saleInvoiceNumber; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public BigDecimal getAmount() { return amount; }
    public String getPaymentMode() { return paymentMode; }
    public String getReferenceNumber() { return referenceNumber; }
    public String getNotes() { return notes; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setPaymentNumber(String paymentNumber) { this.paymentNumber = paymentNumber; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }
    public void setSaleId(Long saleId) { this.saleId = saleId; }
    public void setSaleInvoiceNumber(String saleInvoiceNumber) { this.saleInvoiceNumber = saleInvoiceNumber; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
    public void setReferenceNumber(String referenceNumber) { this.referenceNumber = referenceNumber; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}