package com.agroerp.modules.purchase.repository;

import com.agroerp.modules.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findAllByOrderByCreatedAtDesc();

    List<Purchase> findBySupplierIdOrderByCreatedAtDesc(Long supplierId);

    Optional<Purchase> findByInvoiceNumber(String invoiceNumber);

    boolean existsByInvoiceNumber(String invoiceNumber);

    List<Purchase> findByPurchaseDateBetweenOrderByPurchaseDateDesc(
            LocalDate from, LocalDate to);

    @Query("SELECT COALESCE(SUM(p.totalAmount), 0) FROM Purchase p " +
            "WHERE p.purchaseDate = :date")
    java.math.BigDecimal getTotalPurchaseByDate(LocalDate date);
}