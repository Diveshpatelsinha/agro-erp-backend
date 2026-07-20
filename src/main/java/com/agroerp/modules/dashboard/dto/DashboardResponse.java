package com.agroerp.modules.dashboard.dto;

import java.math.BigDecimal;

public class DashboardResponse {

    private long totalProducts;
    private long totalCustomers;
    private long totalSuppliers;
    private long lowStockCount;
    private BigDecimal todaySales;
    private BigDecimal todayPurchases;
    private BigDecimal totalDueFromCustomers;
    private long totalSalesCount;
    private long totalPurchasesCount;

    public DashboardResponse() {}

    public long getTotalProducts() { return totalProducts; }
    public long getTotalCustomers() { return totalCustomers; }
    public long getTotalSuppliers() { return totalSuppliers; }
    public long getLowStockCount() { return lowStockCount; }
    public BigDecimal getTodaySales() { return todaySales; }
    public BigDecimal getTodayPurchases() { return todayPurchases; }
    public BigDecimal getTotalDueFromCustomers() { return totalDueFromCustomers; }
    public long getTotalSalesCount() { return totalSalesCount; }
    public long getTotalPurchasesCount() { return totalPurchasesCount; }

    public void setTotalProducts(long totalProducts) { this.totalProducts = totalProducts; }
    public void setTotalCustomers(long totalCustomers) { this.totalCustomers = totalCustomers; }
    public void setTotalSuppliers(long totalSuppliers) { this.totalSuppliers = totalSuppliers; }
    public void setLowStockCount(long lowStockCount) { this.lowStockCount = lowStockCount; }
    public void setTodaySales(BigDecimal todaySales) { this.todaySales = todaySales; }
    public void setTodayPurchases(BigDecimal todayPurchases) { this.todayPurchases = todayPurchases; }
    public void setTotalDueFromCustomers(BigDecimal totalDueFromCustomers) { this.totalDueFromCustomers = totalDueFromCustomers; }
    public void setTotalSalesCount(long totalSalesCount) { this.totalSalesCount = totalSalesCount; }
    public void setTotalPurchasesCount(long totalPurchasesCount) { this.totalPurchasesCount = totalPurchasesCount; }
}