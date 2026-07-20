package com.agroerp.modules.reports.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReportSummaryResponse {

    private LocalDate fromDate;
    private LocalDate toDate;
    private BigDecimal totalSalesAmount;
    private BigDecimal totalSalesPaid;
    private BigDecimal totalSalesDue;
    private int totalSalesCount;
    private BigDecimal totalPurchaseAmount;
    private BigDecimal totalPurchasePaid;
    private BigDecimal totalPurchaseDue;
    private int totalPurchaseCount;
    private BigDecimal grossProfit;
    private List<SalesReportResponse> sales;
    private List<PurchaseReportResponse> purchases;

    public ReportSummaryResponse() {}

    public LocalDate getFromDate() { return fromDate; }
    public LocalDate getToDate() { return toDate; }
    public BigDecimal getTotalSalesAmount() { return totalSalesAmount; }
    public BigDecimal getTotalSalesPaid() { return totalSalesPaid; }
    public BigDecimal getTotalSalesDue() { return totalSalesDue; }
    public int getTotalSalesCount() { return totalSalesCount; }
    public BigDecimal getTotalPurchaseAmount() { return totalPurchaseAmount; }
    public BigDecimal getTotalPurchasePaid() { return totalPurchasePaid; }
    public BigDecimal getTotalPurchaseDue() { return totalPurchaseDue; }
    public int getTotalPurchaseCount() { return totalPurchaseCount; }
    public BigDecimal getGrossProfit() { return grossProfit; }
    public List<SalesReportResponse> getSales() { return sales; }
    public List<PurchaseReportResponse> getPurchases() { return purchases; }

    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; }
    public void setToDate(LocalDate toDate) { this.toDate = toDate; }
    public void setTotalSalesAmount(BigDecimal totalSalesAmount) { this.totalSalesAmount = totalSalesAmount; }
    public void setTotalSalesPaid(BigDecimal totalSalesPaid) { this.totalSalesPaid = totalSalesPaid; }
    public void setTotalSalesDue(BigDecimal totalSalesDue) { this.totalSalesDue = totalSalesDue; }
    public void setTotalSalesCount(int totalSalesCount) { this.totalSalesCount = totalSalesCount; }
    public void setTotalPurchaseAmount(BigDecimal totalPurchaseAmount) { this.totalPurchaseAmount = totalPurchaseAmount; }
    public void setTotalPurchasePaid(BigDecimal totalPurchasePaid) { this.totalPurchasePaid = totalPurchasePaid; }
    public void setTotalPurchaseDue(BigDecimal totalPurchaseDue) { this.totalPurchaseDue = totalPurchaseDue; }
    public void setTotalPurchaseCount(int totalPurchaseCount) { this.totalPurchaseCount = totalPurchaseCount; }
    public void setGrossProfit(BigDecimal grossProfit) { this.grossProfit = grossProfit; }
    public void setSales(List<SalesReportResponse> sales) { this.sales = sales; }
    public void setPurchases(List<PurchaseReportResponse> purchases) { this.purchases = purchases; }
}