package com.agroerp.modules.reports.service;

import com.agroerp.modules.purchase.entity.Purchase;
import com.agroerp.modules.purchase.repository.PurchaseRepository;
import com.agroerp.modules.reports.dto.PurchaseReportResponse;
import com.agroerp.modules.reports.dto.ReportSummaryResponse;
import com.agroerp.modules.reports.dto.SalesReportResponse;
import com.agroerp.modules.sales.entity.Sale;
import com.agroerp.modules.sales.repository.SaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final SaleRepository     saleRepository;
    private final PurchaseRepository purchaseRepository;

    public ReportServiceImpl(SaleRepository saleRepository,
                             PurchaseRepository purchaseRepository) {
        this.saleRepository     = saleRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ReportSummaryResponse getDateRangeReport(
            LocalDate fromDate, LocalDate toDate) {

        List<Sale> sales = saleRepository
                .findBySaleDateBetweenOrderBySaleDateDesc(
                        fromDate, toDate);

        List<Purchase> purchases = purchaseRepository
                .findByPurchaseDateBetweenOrderByPurchaseDateDesc(
                        fromDate, toDate);

        BigDecimal totalSalesAmount = sales.stream()
                .map(Sale::getNetAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalSalesPaid = sales.stream()
                .map(Sale::getPaidAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalSalesDue = sales.stream()
                .map(Sale::getDueAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPurchaseAmount = purchases.stream()
                .map(Purchase::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPurchasePaid = purchases.stream()
                .map(Purchase::getPaidAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPurchaseDue = purchases.stream()
                .map(Purchase::getDueAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal grossProfit = totalSalesAmount
                .subtract(totalPurchaseAmount);

        List<SalesReportResponse> salesReport = sales.stream()
                .map(s -> {
                    SalesReportResponse r = new SalesReportResponse();
                    r.setInvoiceNumber(s.getInvoiceNumber());
                    r.setCustomerName(s.getCustomer().getName());
                    r.setCustomerPhone(s.getCustomer().getPhone());
                    r.setSaleDate(s.getSaleDate());
                    r.setTotalAmount(s.getTotalAmount());
                    r.setDiscountAmount(s.getDiscountAmount());
                    r.setNetAmount(s.getNetAmount());
                    r.setPaidAmount(s.getPaidAmount());
                    r.setDueAmount(s.getDueAmount());
                    r.setPaymentStatus(s.getPaymentStatus().name());
                    r.setPaymentMode(s.getPaymentMode().name());
                    r.setItemCount(s.getItems().size());
                    return r;
                })
                .collect(Collectors.toList());

        List<PurchaseReportResponse> purchasesReport = purchases.stream()
                .map(p -> {
                    PurchaseReportResponse r =
                            new PurchaseReportResponse();
                    r.setInvoiceNumber(p.getInvoiceNumber());
                    r.setSupplierName(p.getSupplier().getName());
                    r.setPurchaseDate(p.getPurchaseDate());
                    r.setTotalAmount(p.getTotalAmount());
                    r.setPaidAmount(p.getPaidAmount());
                    r.setDueAmount(p.getDueAmount());
                    r.setPaymentStatus(p.getPaymentStatus().name());
                    r.setItemCount(p.getItems().size());
                    return r;
                })
                .collect(Collectors.toList());

        ReportSummaryResponse response = new ReportSummaryResponse();
        response.setFromDate(fromDate);
        response.setToDate(toDate);
        response.setTotalSalesAmount(totalSalesAmount);
        response.setTotalSalesPaid(totalSalesPaid);
        response.setTotalSalesDue(totalSalesDue);
        response.setTotalSalesCount(sales.size());
        response.setTotalPurchaseAmount(totalPurchaseAmount);
        response.setTotalPurchasePaid(totalPurchasePaid);
        response.setTotalPurchaseDue(totalPurchaseDue);
        response.setTotalPurchaseCount(purchases.size());
        response.setGrossProfit(grossProfit);
        response.setSales(salesReport);
        response.setPurchases(purchasesReport);
        return response;
    }
}