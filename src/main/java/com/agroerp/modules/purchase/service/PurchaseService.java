package com.agroerp.modules.purchase.service;

import com.agroerp.modules.purchase.dto.PurchaseRequest;
import com.agroerp.modules.purchase.dto.PurchaseResponse;
import java.util.List;

public interface PurchaseService {
    PurchaseResponse create(PurchaseRequest request);
    PurchaseResponse getById(Long id);
    List<PurchaseResponse> getAll();
    List<PurchaseResponse> getBySupplier(Long supplierId);
}