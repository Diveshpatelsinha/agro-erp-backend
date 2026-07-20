package com.agroerp.modules.ledger.service;

import com.agroerp.modules.ledger.dto.CustomerLedgerResponse;
import java.util.List;

public interface LedgerService {
    CustomerLedgerResponse getCustomerLedger(Long customerId);
    List<CustomerLedgerResponse> getAllCustomerBalances();
}