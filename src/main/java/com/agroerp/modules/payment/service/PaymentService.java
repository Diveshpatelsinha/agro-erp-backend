package com.agroerp.modules.payment.service;

import com.agroerp.modules.payment.dto.PaymentRequest;
import com.agroerp.modules.payment.dto.PaymentResponse;
import java.util.List;

public interface PaymentService {
    PaymentResponse collect(PaymentRequest request);
    List<PaymentResponse> getAll();
    List<PaymentResponse> getByCustomer(Long customerId);
}