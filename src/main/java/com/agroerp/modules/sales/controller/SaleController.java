package com.agroerp.modules.sales.controller;

import com.agroerp.modules.sales.dto.SaleRequest;
import com.agroerp.modules.sales.dto.SaleResponse;
import com.agroerp.modules.sales.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "http://localhost:4200")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','CASHIER')")
    public ResponseEntity<SaleResponse> create(
            @Valid @RequestBody SaleRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saleService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponse> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(saleService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<SaleResponse>> getAll() {
        return ResponseEntity.ok(saleService.getAll());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<SaleResponse>> getByCustomer(
            @PathVariable Long customerId) {
        return ResponseEntity.ok(
                saleService.getByCustomer(customerId));
    }
}