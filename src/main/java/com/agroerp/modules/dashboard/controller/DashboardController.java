package com.agroerp.modules.dashboard.controller;

import com.agroerp.modules.customer.repository.CustomerRepository;
import com.agroerp.modules.dashboard.dto.DashboardResponse;
import com.agroerp.modules.product.repository.ProductRepository;
import com.agroerp.modules.purchase.repository.PurchaseRepository;
import com.agroerp.modules.sales.repository.SaleRepository;
import com.agroerp.modules.supplier.repository.SupplierRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final ProductRepository  productRepository;
    private final CustomerRepository customerRepository;
    private final SupplierRepository supplierRepository;
    private final SaleRepository     saleRepository;
    private final PurchaseRepository purchaseRepository;

    public DashboardController(
            ProductRepository productRepository,
            CustomerRepository customerRepository,
            SupplierRepository supplierRepository,
            SaleRepository saleRepository,
            PurchaseRepository purchaseRepository) {
        this.productRepository  = productRepository;
        this.customerRepository = customerRepository;
        this.supplierRepository = supplierRepository;
        this.saleRepository     = saleRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @GetMapping
    public ResponseEntity<DashboardResponse> getDashboard() {
        LocalDate today = LocalDate.now();

        DashboardResponse response = new DashboardResponse();

        response.setTotalProducts(
                productRepository
                        .findByActiveTrueOrderByNameAsc().size()
        );
        response.setTotalCustomers(
                customerRepository
                        .findByActiveTrueOrderByNameAsc().size()
        );
        response.setTotalSuppliers(
                supplierRepository
                        .findByActiveTrueOrderByNameAsc().size()
        );
        response.setLowStockCount(
                productRepository.findLowStockProducts().size()
        );
        response.setTotalSalesCount(saleRepository.count());
        response.setTotalPurchasesCount(
                purchaseRepository.count());

        BigDecimal todaySales =
                saleRepository.getTotalSalesByDate(today);
        response.setTodaySales(
                todaySales != null ? todaySales : BigDecimal.ZERO
        );

        BigDecimal todayPurchases =
                purchaseRepository.getTotalPurchaseByDate(today);
        response.setTodayPurchases(
                todayPurchases != null
                        ? todayPurchases : BigDecimal.ZERO
        );

        BigDecimal totalDue = customerRepository
                .findByActiveTrueOrderByNameAsc()
                .stream()
                .map(c -> c.getCurrentBalance())
                .filter(b -> b.compareTo(BigDecimal.ZERO) > 0)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        response.setTotalDueFromCustomers(totalDue);

        return ResponseEntity.ok(response);
    }
}