package com.agroerp.modules.reports.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SalesReportResponse {

    private String invoiceNumber;
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
    private int itemCount;

    public SalesReportResponse() {}

    public String getInvoiceNumber() { return invoiceNumber; }
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
    public int getItemCount() { return itemCount; }

    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
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
    public void setItemCount(int itemCount) { this.itemCount = itemCount; }
}