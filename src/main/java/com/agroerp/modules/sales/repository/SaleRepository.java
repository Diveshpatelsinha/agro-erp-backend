package com.agroerp.modules.sales.repository;

import com.agroerp.modules.sales.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findAllByOrderByCreatedAtDesc();

    List<Sale> findByCustomerIdOrderByCreatedAtDesc(Long customerId);

    boolean existsByInvoiceNumber(String invoiceNumber);

    List<Sale> findBySaleDateBetweenOrderBySaleDateDesc(
            LocalDate from, LocalDate to);

    @Query("SELECT COALESCE(SUM(s.netAmount), 0) " +
            "FROM Sale s WHERE s.saleDate = :date")
    BigDecimal getTotalSalesByDate(LocalDate date);

    @Query("SELECT COALESCE(SUM(s.dueAmount), 0) " +
            "FROM Sale s WHERE s.customer.id = :customerId " +
            "AND s.dueAmount > 0")
    BigDecimal getTotalDueByCustomer(Long customerId);
}