package com.agroerp.modules.ledger.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LedgerEntryResponse {

    private String type;
    private String invoiceNumber;
    private LocalDate date;
    private BigDecimal totalAmount;
    private BigDecimal paidAmount;
    private BigDecimal dueAmount;
    private String paymentStatus;
    private String paymentMode;

    public LedgerEntryResponse() {}

    public String getType() { return type; }
    public String getInvoiceNumber() { return invoiceNumber; }
    public LocalDate getDate() { return date; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public BigDecimal getPaidAmount() { return paidAmount; }
    public BigDecimal getDueAmount() { return dueAmount; }
    public String getPaymentStatus() { return paymentStatus; }
    public String getPaymentMode() { return paymentMode; }

    public void setType(String type) { this.type = type; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public void setPaidAmount(BigDecimal paidAmount) { this.paidAmount = paidAmount; }
    public void setDueAmount(BigDecimal dueAmount) { this.dueAmount = dueAmount; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
}