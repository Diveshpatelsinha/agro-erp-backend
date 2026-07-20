package com.agroerp.modules.ledger.controller;

import com.agroerp.modules.ledger.dto.CustomerLedgerResponse;
import com.agroerp.modules.ledger.service.LedgerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ledger")
@CrossOrigin(origins = "http://localhost:4200")
public class LedgerController {

    private final LedgerService ledgerService;

    public LedgerController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<CustomerLedgerResponse> getCustomerLedger(
            @PathVariable Long customerId) {
        return ResponseEntity.ok(
                ledgerService.getCustomerLedger(customerId));
    }

    @GetMapping("/balances")
    public ResponseEntity<List<CustomerLedgerResponse>> getAllBalances() {
        return ResponseEntity.ok(
                ledgerService.getAllCustomerBalances());
    }
}