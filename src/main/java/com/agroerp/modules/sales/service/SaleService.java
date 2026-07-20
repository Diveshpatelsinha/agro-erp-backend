package com.agroerp.modules.sales.service;

import com.agroerp.modules.sales.dto.SaleRequest;
import com.agroerp.modules.sales.dto.SaleResponse;
import java.util.List;

public interface SaleService {
    SaleResponse create(SaleRequest request);
    SaleResponse getById(Long id);
    List<SaleResponse> getAll();
    List<SaleResponse> getByCustomer(Long customerId);
}