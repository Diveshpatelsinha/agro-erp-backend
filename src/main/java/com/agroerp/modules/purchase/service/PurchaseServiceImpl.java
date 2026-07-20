package com.agroerp.modules.purchase.service;

import com.agroerp.exception.BusinessException;
import com.agroerp.exception.ResourceNotFoundException;
import com.agroerp.modules.product.entity.Product;
import com.agroerp.modules.product.repository.ProductRepository;
import com.agroerp.modules.purchase.dto.PurchaseItemRequest;
import com.agroerp.modules.purchase.dto.PurchaseRequest;
import com.agroerp.modules.purchase.dto.PurchaseResponse;
import com.agroerp.modules.purchase.entity.PaymentStatus;
import com.agroerp.modules.purchase.entity.Purchase;
import com.agroerp.modules.purchase.entity.PurchaseItem;
import com.agroerp.modules.purchase.repository.PurchaseRepository;
import com.agroerp.modules.supplier.entity.Supplier;
import com.agroerp.modules.supplier.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository  purchaseRepository;
    private final SupplierRepository  supplierRepository;
    private final ProductRepository   productRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository,
                               SupplierRepository supplierRepository,
                               ProductRepository productRepository) {
        this.purchaseRepository = purchaseRepository;
        this.supplierRepository = supplierRepository;
        this.productRepository  = productRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse create(PurchaseRequest request) {

        if (purchaseRepository.existsByInvoiceNumber(
                request.getInvoiceNumber())) {
            throw new BusinessException(
                    "Invoice number '" + request.getInvoiceNumber()
                            + "' already exists."
            );
        }

        Supplier supplier = supplierRepository
                .findById(request.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Supplier", "id", request.getSupplierId()));

        Purchase purchase = new Purchase();
        purchase.setInvoiceNumber(request.getInvoiceNumber());
        purchase.setSupplier(supplier);
        purchase.setPurchaseDate(request.getPurchaseDate());
        purchase.setNotes(request.getNotes());

        List<PurchaseItem> items = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (PurchaseItemRequest itemReq : request.getItems()) {
            Product product = productRepository
                    .findById(itemReq.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Product", "id", itemReq.getProductId()));

            BigDecimal totalPrice = itemReq.getUnitPrice()
                    .multiply(itemReq.getQuantity());

            PurchaseItem item = new PurchaseItem();
            item.setPurchase(purchase);
            item.setProduct(product);
            item.setQuantity(itemReq.getQuantity());
            item.setUnitPrice(itemReq.getUnitPrice());
            item.setTotalPrice(totalPrice);
            items.add(item);

            totalAmount = totalAmount.add(totalPrice);

            product.setStockQuantity(
                    product.getStockQuantity().add(itemReq.getQuantity())
            );
            product.setPurchasePrice(itemReq.getUnitPrice());
            productRepository.save(product);
        }

        purchase.setItems(items);
        purchase.setTotalAmount(totalAmount);
        purchase.setPaidAmount(request.getPaidAmount());
        purchase.setDueAmount(
                totalAmount.subtract(request.getPaidAmount())
        );

        BigDecimal due = totalAmount.subtract(request.getPaidAmount());
        if (due.compareTo(BigDecimal.ZERO) <= 0) {
            purchase.setPaymentStatus(PaymentStatus.PAID);
        } else if (request.getPaidAmount()
                .compareTo(BigDecimal.ZERO) > 0) {
            purchase.setPaymentStatus(PaymentStatus.PARTIAL);
        } else {
            purchase.setPaymentStatus(PaymentStatus.PENDING);
        }

        return PurchaseResponse.fromEntity(
                purchaseRepository.save(purchase)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseResponse getById(Long id) {
        return purchaseRepository.findById(id)
                .map(PurchaseResponse::fromEntity)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Purchase", "id", id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseResponse> getAll() {
        return purchaseRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(PurchaseResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseResponse> getBySupplier(Long supplierId) {
        return purchaseRepository
                .findBySupplierIdOrderByCreatedAtDesc(supplierId)
                .stream()
                .map(PurchaseResponse::fromEntity)
                .collect(Collectors.toList());
    }
}