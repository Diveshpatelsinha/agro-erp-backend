package com.agroerp.modules.payment.service;

import com.agroerp.exception.BusinessException;
import com.agroerp.exception.ResourceNotFoundException;
import com.agroerp.modules.customer.entity.Customer;
import com.agroerp.modules.customer.repository.CustomerRepository;
import com.agroerp.modules.payment.dto.PaymentRequest;
import com.agroerp.modules.payment.dto.PaymentResponse;
import com.agroerp.modules.payment.entity.Payment;
import com.agroerp.modules.payment.entity.PaymentMode;
import com.agroerp.modules.payment.repository.PaymentRepository;
import com.agroerp.modules.purchase.entity.PaymentStatus;
import com.agroerp.modules.sales.entity.Sale;
import com.agroerp.modules.sales.repository.SaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository  paymentRepository;
    private final CustomerRepository customerRepository;
    private final SaleRepository     saleRepository;

    public PaymentServiceImpl(
            PaymentRepository paymentRepository,
            CustomerRepository customerRepository,
            SaleRepository saleRepository) {
        this.paymentRepository  = paymentRepository;
        this.customerRepository = customerRepository;
        this.saleRepository     = saleRepository;
    }

    @Override
    @Transactional
    public PaymentResponse collect(PaymentRequest request) {

        if (paymentRepository.existsByPaymentNumber(
                request.getPaymentNumber())) {
            throw new BusinessException(
                    "Payment number '" + request.getPaymentNumber()
                            + "' already exists."
            );
        }

        Customer customer = customerRepository
                .findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer", "id", request.getCustomerId()));

        Payment payment = new Payment();
        payment.setPaymentNumber(request.getPaymentNumber());
        payment.setCustomer(customer);
        payment.setPaymentDate(request.getPaymentDate());
        payment.setAmount(request.getAmount());
        payment.setPaymentMode(
                PaymentMode.valueOf(request.getPaymentMode()));
        payment.setReferenceNumber(request.getReferenceNumber());
        payment.setNotes(request.getNotes());

        if (request.getSaleId() != null) {
            Sale sale = saleRepository
                    .findById(request.getSaleId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Sale", "id", request.getSaleId()));

            if (request.getAmount()
                    .compareTo(sale.getDueAmount()) > 0) {
                throw new BusinessException(
                        "Payment amount exceeds due amount of ₹"
                                + sale.getDueAmount()
                );
            }

            BigDecimal newDue = sale.getDueAmount()
                    .subtract(request.getAmount());
            BigDecimal newPaid = sale.getPaidAmount()
                    .add(request.getAmount());

            sale.setDueAmount(newDue);
            sale.setPaidAmount(newPaid);

            if (newDue.compareTo(BigDecimal.ZERO) <= 0) {
                sale.setPaymentStatus(PaymentStatus.PAID);
            } else {
                sale.setPaymentStatus(PaymentStatus.PARTIAL);
            }

            saleRepository.save(sale);
            payment.setSale(sale);
        }

        BigDecimal newBalance = customer.getCurrentBalance()
                .subtract(request.getAmount());
        customer.setCurrentBalance(
                newBalance.compareTo(BigDecimal.ZERO) < 0
                        ? BigDecimal.ZERO : newBalance
        );
        customerRepository.save(customer);

        return PaymentResponse.fromEntity(
                paymentRepository.save(payment));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponse> getAll() {
        return paymentRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(PaymentResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponse> getByCustomer(Long customerId) {
        return paymentRepository
                .findByCustomerIdOrderByCreatedAtDesc(customerId)
                .stream()
                .map(PaymentResponse::fromEntity)
                .collect(Collectors.toList());
    }
}