package com.agroerp.modules.ledger.service;

import com.agroerp.exception.ResourceNotFoundException;
import com.agroerp.modules.customer.entity.Customer;
import com.agroerp.modules.customer.repository.CustomerRepository;
import com.agroerp.modules.ledger.dto.CustomerLedgerResponse;
import com.agroerp.modules.ledger.dto.LedgerEntryResponse;
import com.agroerp.modules.sales.entity.Sale;
import com.agroerp.modules.sales.repository.SaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LedgerServiceImpl implements LedgerService {

    private final CustomerRepository customerRepository;
    private final SaleRepository     saleRepository;

    public LedgerServiceImpl(CustomerRepository customerRepository,
                             SaleRepository saleRepository) {
        this.customerRepository = customerRepository;
        this.saleRepository     = saleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerLedgerResponse getCustomerLedger(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer", "id", customerId));

        List<Sale> sales = saleRepository
                .findByCustomerIdOrderByCreatedAtDesc(customerId);

        BigDecimal totalSales = sales.stream()
                .map(Sale::getNetAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPaid = sales.stream()
                .map(Sale::getPaidAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<LedgerEntryResponse> entries = sales.stream()
                .map(s -> {
                    LedgerEntryResponse e = new LedgerEntryResponse();
                    e.setType("SALE");
                    e.setInvoiceNumber(s.getInvoiceNumber());
                    e.setDate(s.getSaleDate());
                    e.setTotalAmount(s.getNetAmount());
                    e.setPaidAmount(s.getPaidAmount());
                    e.setDueAmount(s.getDueAmount());
                    e.setPaymentStatus(s.getPaymentStatus().name());
                    e.setPaymentMode(s.getPaymentMode().name());
                    return e;
                })
                .collect(Collectors.toList());

        CustomerLedgerResponse response = new CustomerLedgerResponse();
        response.setCustomerId(customer.getId());
        response.setCustomerName(customer.getName());
        response.setCustomerPhone(customer.getPhone());
        response.setVillage(customer.getVillage());
        response.setDistrict(customer.getDistrict());
        response.setOpeningBalance(customer.getOpeningBalance());
        response.setTotalSales(totalSales);
        response.setTotalPaid(totalPaid);
        response.setCurrentBalance(customer.getCurrentBalance());
        response.setEntries(entries);
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerLedgerResponse> getAllCustomerBalances() {
        return customerRepository.findByActiveTrueOrderByNameAsc()
                .stream()
                .map(c -> {
                    CustomerLedgerResponse r =
                            new CustomerLedgerResponse();
                    r.setCustomerId(c.getId());
                    r.setCustomerName(c.getName());
                    r.setCustomerPhone(c.getPhone());
                    r.setVillage(c.getVillage());
                    r.setDistrict(c.getDistrict());
                    r.setOpeningBalance(c.getOpeningBalance());
                    r.setCurrentBalance(c.getCurrentBalance());
                    return r;
                })
                .collect(Collectors.toList());
    }
}