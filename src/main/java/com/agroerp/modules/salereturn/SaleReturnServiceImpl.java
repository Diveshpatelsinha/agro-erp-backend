package com.agroerp.modules.salereturn.service;

import com.agroerp.exception.BusinessException;
import com.agroerp.exception.ResourceNotFoundException;
import com.agroerp.modules.customer.entity.Customer;
import com.agroerp.modules.product.entity.Product;
import com.agroerp.modules.product.repository.ProductRepository;
import com.agroerp.modules.sales.entity.Sale;
import com.agroerp.modules.sales.entity.SaleItem;
import com.agroerp.modules.sales.repository.SaleRepository;
import com.agroerp.modules.salereturn.dto.SaleReturnItemRequest;
import com.agroerp.modules.salereturn.dto.SaleReturnRequest;
import com.agroerp.modules.salereturn.dto.SaleReturnResponse;
import com.agroerp.modules.salereturn.entity.SaleReturn;
import com.agroerp.modules.salereturn.entity.SaleReturnItem;
import com.agroerp.modules.salereturn.repository.SaleReturnRepository;
import com.agroerp.modules.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleReturnServiceImpl implements SaleReturnService {

    private final SaleReturnRepository saleReturnRepository;
    private final SaleRepository       saleRepository;
    private final ProductRepository    productRepository;
    private final CustomerRepository   customerRepository;

    public SaleReturnServiceImpl(
            SaleReturnRepository saleReturnRepository,
            SaleRepository saleRepository,
            ProductRepository productRepository,
            CustomerRepository customerRepository) {
        this.saleReturnRepository = saleReturnRepository;
        this.saleRepository       = saleRepository;
        this.productRepository    = productRepository;
        this.customerRepository   = customerRepository;
    }

    @Override
    @Transactional
    public SaleReturnResponse create(SaleReturnRequest request) {

        if (saleReturnRepository.existsByReturnNumber(
                request.getReturnNumber())) {
            throw new BusinessException(
                    "Return number '" + request.getReturnNumber()
                            + "' already exists."
            );
        }

        Sale sale = saleRepository
                .findById(request.getSaleId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Sale", "id", request.getSaleId()));

        Customer customer = sale.getCustomer();

        SaleReturn saleReturn = new SaleReturn();
        saleReturn.setReturnNumber(request.getReturnNumber());
        saleReturn.setSale(sale);
        saleReturn.setCustomer(customer);
        saleReturn.setReturnDate(request.getReturnDate());
        saleReturn.setReason(request.getReason());
        saleReturn.setNotes(request.getNotes());

        List<SaleReturnItem> items = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (SaleReturnItemRequest itemReq : request.getItems()) {

            Product product = productRepository
                    .findById(itemReq.getProductId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Product", "id",
                                    itemReq.getProductId()));

            BigDecimal soldQty = sale.getItems().stream()
                    .filter(si -> si.getProduct().getId()
                            .equals(itemReq.getProductId()))
                    .map(SaleItem::getQuantity)
                    .findFirst()
                    .orElse(BigDecimal.ZERO);

            if (itemReq.getQuantity().compareTo(soldQty) > 0) {
                throw new BusinessException(
                        "Return quantity for "
                                + product.getName()
                                + " cannot exceed sold quantity of "
                                + soldQty
                );
            }

            BigDecimal totalPrice = itemReq.getUnitPrice()
                    .multiply(itemReq.getQuantity());

            SaleReturnItem item = new SaleReturnItem();
            item.setSaleReturn(saleReturn);
            item.setProduct(product);
            item.setQuantity(itemReq.getQuantity());
            item.setUnitPrice(itemReq.getUnitPrice());
            item.setTotalPrice(totalPrice);
            items.add(item);

            totalAmount = totalAmount.add(totalPrice);

            product.setStockQuantity(
                    product.getStockQuantity()
                            .add(itemReq.getQuantity())
            );
            productRepository.save(product);
        }

        saleReturn.setItems(items);
        saleReturn.setTotalAmount(totalAmount);

        BigDecimal newBalance = customer.getCurrentBalance()
                .subtract(totalAmount);
        customer.setCurrentBalance(
                newBalance.compareTo(BigDecimal.ZERO) < 0
                        ? BigDecimal.ZERO : newBalance
        );
        customerRepository.save(customer);

        return SaleReturnResponse.fromEntity(
                saleReturnRepository.save(saleReturn));
    }

    @Override
    @Transactional(readOnly = true)
    public SaleReturnResponse getById(Long id) {
        return saleReturnRepository.findById(id)
                .map(SaleReturnResponse::fromEntity)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "SaleReturn", "id", id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SaleReturnResponse> getAll() {
        return saleReturnRepository
                .findAllByOrderByCreatedAtDesc()
                .stream()
                .map(SaleReturnResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SaleReturnResponse> getByCustomer(
            Long customerId) {
        return saleReturnRepository
                .findByCustomerIdOrderByCreatedAtDesc(customerId)
                .stream()
                .map(SaleReturnResponse::fromEntity)
                .collect(Collectors.toList());
    }
}