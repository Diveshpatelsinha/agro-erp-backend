package com.agroerp.modules.customer.repository;

import com.agroerp.modules.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByActiveTrueOrderByNameAsc();
    Optional<Customer> findByPhone(String phone);
    boolean existsByPhone(String phone);
    List<Customer> findByNameContainingIgnoreCaseAndActiveTrue(String name);
}