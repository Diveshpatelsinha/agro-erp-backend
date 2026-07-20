package com.agroerp.modules.purchase.controller;

import com.agroerp.modules.purchase.dto.PurchaseRequest;
import com.agroerp.modules.purchase.dto.PurchaseResponse;
import com.agroerp.modules.purchase.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@CrossOrigin(origins = "http://localhost:4200")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<PurchaseResponse> create(
            @Valid @RequestBody PurchaseRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(purchaseService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseResponse> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(purchaseService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<PurchaseResponse>> getAll() {
        return ResponseEntity.ok(purchaseService.getAll());
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<PurchaseResponse>> getBySupplier(
            @PathVariable Long supplierId) {
        return ResponseEntity.ok(
                purchaseService.getBySupplier(supplierId));
    }
}