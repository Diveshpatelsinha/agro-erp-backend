package com.agroerp.modules.customer.service;

import com.agroerp.modules.customer.dto.CustomerRequest;
import com.agroerp.modules.customer.dto.CustomerResponse;
import java.util.List;

public interface CustomerService {
    CustomerResponse create(CustomerRequest request);
    CustomerResponse update(Long id, CustomerRequest request);
    CustomerResponse getById(Long id);
    List<CustomerResponse> getAll();
    List<CustomerResponse> getAllActive();
    List<CustomerResponse> searchByName(String name);
    void delete(Long id);
}