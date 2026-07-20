package com.agroerp.modules.ledger.dto;

import java.math.BigDecimal;
import java.util.List;

public class CustomerLedgerResponse {

    private Long customerId;
    private String customerName;
    private String customerPhone;
    private String village;
    private String district;
    private BigDecimal openingBalance;
    private BigDecimal totalSales;
    private BigDecimal totalPaid;
    private BigDecimal currentBalance;
    private List<LedgerEntryResponse> entries;

    public CustomerLedgerResponse() {}

    public Long getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getCustomerPhone() { return customerPhone; }
    public String getVillage() { return village; }
    public String getDistrict() { return district; }
    public BigDecimal getOpeningBalance() { return openingBalance; }
    public BigDecimal getTotalSales() { return totalSales; }
    public BigDecimal getTotalPaid() { return totalPaid; }
    public BigDecimal getCurrentBalance() { return currentBalance; }
    public List<LedgerEntryResponse> getEntries() { return entries; }

    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }
    public void setVillage(String village) { this.village = village; }
    public void setDistrict(String district) { this.district = district; }
    public void setOpeningBalance(BigDecimal openingBalance) { this.openingBalance = openingBalance; }
    public void setTotalSales(BigDecimal totalSales) { this.totalSales = totalSales; }
    public void setTotalPaid(BigDecimal totalPaid) { this.totalPaid = totalPaid; }
    public void setCurrentBalance(BigDecimal currentBalance) { this.currentBalance = currentBalance; }
    public void setEntries(List<LedgerEntryResponse> entries) { this.entries = entries; }
}