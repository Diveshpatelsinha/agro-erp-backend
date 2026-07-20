package com.agroerp.modules.supplier.service;

import com.agroerp.modules.supplier.dto.SupplierRequest;
import com.agroerp.modules.supplier.dto.SupplierResponse;
import java.util.List;

public interface SupplierService {
    SupplierResponse create(SupplierRequest request);
    SupplierResponse update(Long id, SupplierRequest request);
    SupplierResponse getById(Long id);
    List<SupplierResponse> getAll();
    List<SupplierResponse> getAllActive();
    void delete(Long id);
}