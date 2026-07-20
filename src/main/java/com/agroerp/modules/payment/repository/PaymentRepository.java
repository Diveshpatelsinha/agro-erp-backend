package com.agroerp.modules.payment.repository;

import com.agroerp.modules.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByCustomerIdOrderByCreatedAtDesc(Long customerId);

    List<Payment> findAllByOrderByCreatedAtDesc();

    boolean existsByPaymentNumber(String paymentNumber);

    List<Payment> findByPaymentDateBetweenOrderByPaymentDateDesc(
            LocalDate from, LocalDate to);

    @Query("SELECT COALESCE(SUM(p.amount), 0) " +
            "FROM Payment p WHERE p.customer.id = :customerId")
    BigDecimal getTotalPaidByCustomer(Long customerId);
}