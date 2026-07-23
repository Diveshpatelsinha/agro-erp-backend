package com.agroerp.modules.salereturn.service;

import com.agroerp.modules.salereturn.dto.SaleReturnRequest;
import com.agroerp.modules.salereturn.dto.SaleReturnResponse;
import java.util.List;

public interface SaleReturnService {
    SaleReturnResponse create(SaleReturnRequest request);
    SaleReturnResponse getById(Long id);
    List<SaleReturnResponse> getAll();
    List<SaleReturnResponse> getByCustomer(Long customerId);
}