package com.agroerp.modules.salereturn.controller;

import com.agroerp.modules.salereturn.dto.SaleReturnRequest;
import com.agroerp.modules.salereturn.dto.SaleReturnResponse;
import com.agroerp.modules.salereturn.service.SaleReturnService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sale-returns")
@CrossOrigin(origins = "*")
public class SaleReturnController {

    private final SaleReturnService saleReturnService;

    public SaleReturnController(
            SaleReturnService saleReturnService) {
        this.saleReturnService = saleReturnService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<SaleReturnResponse> create(
            @Valid @RequestBody SaleReturnRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saleReturnService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleReturnResponse> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                saleReturnService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<SaleReturnResponse>> getAll() {
        return ResponseEntity.ok(
                saleReturnService.getAll());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<SaleReturnResponse>> getByCustomer(
            @PathVariable Long customerId) {
        return ResponseEntity.ok(
                saleReturnService.getByCustomer(customerId));
    }
}