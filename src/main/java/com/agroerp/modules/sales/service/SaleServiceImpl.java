package com.agroerp.modules.sales.service;

import com.agroerp.exception.BusinessException;
import com.agroerp.exception.ResourceNotFoundException;
import com.agroerp.modules.customer.entity.Customer;
import com.agroerp.modules.customer.repository.CustomerRepository;
import com.agroerp.modules.product.entity.Product;
import com.agroerp.modules.product.repository.ProductRepository;
import com.agroerp.modules.purchase.entity.PaymentStatus;
import com.agroerp.modules.sales.dto.SaleItemRequest;
import com.agroerp.modules.sales.dto.SaleRequest;
import com.agroerp.modules.sales.dto.SaleResponse;
import com.agroerp.modules.sales.entity.PaymentMode;
import com.agroerp.modules.sales.entity.Sale;
import com.agroerp.modules.sales.entity.SaleItem;
import com.agroerp.modules.sales.repository.SaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository     saleRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository  productRepository;

    public SaleServiceImpl(SaleRepository saleRepository,
                           CustomerRepository customerRepository,
                           ProductRepository productRepository) {
        this.saleRepository     = saleRepository;
        this.customerRepository = customerRepository;
        this.productRepository  = productRepository;
    }

    @Override
    @Transactional
    public SaleResponse create(SaleRequest request) {

        if (saleRepository.existsByInvoiceNumber(
                request.getInvoiceNumber())) {
            throw new BusinessException(
                    "Invoice number '" + request.getInvoiceNumber()
                            + "' already exists."
            );
        }

        Customer customer = customerRepository
                .findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer", "id", request.getCustomerId()));

        Sale sale = new Sale();
        sale.setInvoiceNumber(request.getInvoiceNumber());
        sale.setCustomer(customer);
        sale.setSaleDate(request.getSaleDate());
        sale.setDiscountAmount(request.getDiscountAmount()
                != null ? request.getDiscountAmount() : BigDecimal.ZERO);
        sale.setPaymentMode(PaymentMode.valueOf(request.getPaymentMode()));
        sale.setNotes(request.getNotes());

        List<SaleItem> items  = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (SaleItemRequest itemReq : request.getItems()) {
            Product product = productRepository
                    .findById(itemReq.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Product", "id", itemReq.getProductId()));

            if (product.getStockQuantity()
                    .compareTo(itemReq.getQuantity()) < 0) {
                throw new BusinessException(
                        "Insufficient stock for: " + product.getName()
                                + ". Available: " + product.getStockQuantity()
                                + " " + product.getUnit()
                );
            }

            BigDecimal totalPrice = itemReq.getUnitPrice()
                    .multiply(itemReq.getQuantity());

            SaleItem item = new SaleItem();
            item.setSale(sale);
            item.setProduct(product);
            item.setQuantity(itemReq.getQuantity());
            item.setUnitPrice(itemReq.getUnitPrice());
            item.setTotalPrice(totalPrice);
            items.add(item);

            totalAmount = totalAmount.add(totalPrice);

            product.setStockQuantity(
                    product.getStockQuantity()
                            .subtract(itemReq.getQuantity())
            );
            productRepository.save(product);
        }

        BigDecimal discount = sale.getDiscountAmount();
        BigDecimal netAmount = totalAmount.subtract(discount);
        BigDecimal paidAmount = request.getPaidAmount() != null
                ? request.getPaidAmount() : BigDecimal.ZERO;
        BigDecimal dueAmount = netAmount.subtract(paidAmount);

        sale.setItems(items);
        sale.setTotalAmount(totalAmount);
        sale.setNetAmount(netAmount);
        sale.setPaidAmount(paidAmount);
        sale.setDueAmount(dueAmount);

        if (dueAmount.compareTo(BigDecimal.ZERO) <= 0) {
            sale.setPaymentStatus(PaymentStatus.PAID);
        } else if (paidAmount.compareTo(BigDecimal.ZERO) > 0) {
            sale.setPaymentStatus(PaymentStatus.PARTIAL);
        } else {
            sale.setPaymentStatus(PaymentStatus.PENDING);
        }

        customer.setCurrentBalance(
                customer.getCurrentBalance().add(dueAmount)
        );
        customerRepository.save(customer);

        return SaleResponse.fromEntity(saleRepository.save(sale));
    }

    @Override
    @Transactional(readOnly = true)
    public SaleResponse getById(Long id) {
        return saleRepository.findById(id)
                .map(SaleResponse::fromEntity)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sale", "id", id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SaleResponse> getAll() {
        return saleRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(SaleResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SaleResponse> getByCustomer(Long customerId) {
        return saleRepository
                .findByCustomerIdOrderByCreatedAtDesc(customerId)
                .stream()
                .map(SaleResponse::fromEntity)
                .collect(Collectors.toList());
    }
}