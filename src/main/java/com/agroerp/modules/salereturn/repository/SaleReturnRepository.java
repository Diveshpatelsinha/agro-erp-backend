package com.agroerp.modules.salereturn.repository;

import com.agroerp.modules.salereturn.entity.SaleReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleReturnRepository
        extends JpaRepository<SaleReturn, Long> {

    List<SaleReturn> findAllByOrderByCreatedAtDesc();

    List<SaleReturn> findByCustomerIdOrderByCreatedAtDesc(
            Long customerId);

    List<SaleReturn> findBySaleIdOrderByCreatedAtDesc(
            Long saleId);

    boolean existsByReturnNumber(String returnNumber);
}