package com.agroerp.modules.salereturn.dto;

import com.agroerp.modules.salereturn.entity.SaleReturn;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class SaleReturnResponse {

    private Long id;
    private String returnNumber;
    private Long saleId;
    private String saleInvoiceNumber;
    private Long customerId;
    private String customerName;
    private String customerPhone;
    private LocalDate returnDate;
    private BigDecimal totalAmount;
    private String reason;
    private String notes;
    private List<SaleReturnItemResponse> items;
    private LocalDateTime createdAt;

    public SaleReturnResponse() {}

    public static SaleReturnResponse fromEntity(SaleReturn r) {
        SaleReturnResponse res = new SaleReturnResponse();
        res.id                = r.getId();
        res.returnNumber      = r.getReturnNumber();
        res.saleId            = r.getSale().getId();
        res.saleInvoiceNumber = r.getSale().getInvoiceNumber();
        res.customerId        = r.getCustomer().getId();
        res.customerName      = r.getCustomer().getName();
        res.customerPhone     = r.getCustomer().getPhone();
        res.returnDate        = r.getReturnDate();
        res.totalAmount       = r.getTotalAmount();
        res.reason            = r.getReason();
        res.notes             = r.getNotes();
        res.items             = r.getItems().stream()
                .map(SaleReturnItemResponse::fromEntity)
                .collect(Collectors.toList());
        res.createdAt         = r.getCreatedAt();
        return res;
    }

    public Long getId() { return id; }
    public String getReturnNumber() { return returnNumber; }
    public Long getSaleId() { return saleId; }
    public String getSaleInvoiceNumber() { return saleInvoiceNumber; }
    public Long getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getCustomerPhone() { return customerPhone; }
    public LocalDate getReturnDate() { return returnDate; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public String getReason() { return reason; }
    public String getNotes() { return notes; }
    public List<SaleReturnItemResponse> getItems() { return items; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setReturnNumber(String returnNumber) { this.returnNumber = returnNumber; }
    public void setSaleId(Long saleId) { this.saleId = saleId; }
    public void setSaleInvoiceNumber(String s) { this.saleInvoiceNumber = s; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public void setReason(String reason) { this.reason = reason; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setItems(List<SaleReturnItemResponse> items) { this.items = items; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}