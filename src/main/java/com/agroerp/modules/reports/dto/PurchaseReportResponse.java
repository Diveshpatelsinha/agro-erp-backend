package com.agroerp.modules.reports.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PurchaseReportResponse {

    private String invoiceNumber;
    private String supplierName;
    private LocalDate purchaseDate;
    private BigDecimal totalAmount;
    private BigDecimal paidAmount;
    private BigDecimal dueAmount;
    private String paymentStatus;
    private int itemCount;

    public PurchaseReportResponse() {}

    public String getInvoiceNumber() { return invoiceNumber; }
    public String getSupplierName() { return supplierName; }
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public BigDecimal getPaidAmount() { return paidAmount; }
    public BigDecimal getDueAmount() { return dueAmount; }
    public String getPaymentStatus() { return paymentStatus; }
    public int getItemCount() { return itemCount; }

    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public void setPaidAmount(BigDecimal paidAmount) { this.paidAmount = paidAmount; }
    public void setDueAmount(BigDecimal dueAmount) { this.dueAmount = dueAmount; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public void setItemCount(int itemCount) { this.itemCount = itemCount; }
}