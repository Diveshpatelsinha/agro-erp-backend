package com.agroerp.modules.customer.service;

import com.agroerp.exception.BusinessException;
import com.agroerp.exception.ResourceNotFoundException;
import com.agroerp.modules.customer.dto.CustomerRequest;
import com.agroerp.modules.customer.dto.CustomerResponse;
import com.agroerp.modules.customer.entity.Customer;
import com.agroerp.modules.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public CustomerResponse create(CustomerRequest request) {
        if (customerRepository.existsByPhone(request.getPhone())) {
            throw new BusinessException(
                    "Customer with phone '" + request.getPhone() + "' already exists."
            );
        }
        Customer customer = new Customer();
        mapToEntity(request, customer);
        customer.setCurrentBalance(request.getOpeningBalance());
        return CustomerResponse.fromEntity(customerRepository.save(customer));
    }

    @Override
    @Transactional
    public CustomerResponse update(Long id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer", "id", id));

        customerRepository.findByPhone(request.getPhone())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new BusinessException(
                                "Phone '" + request.getPhone() + "' already registered."
                        );
                    }
                });

        mapToEntity(request, customer);
        return CustomerResponse.fromEntity(customerRepository.save(customer));
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse getById(Long id) {
        return customerRepository.findById(id)
                .map(CustomerResponse::fromEntity)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer", "id", id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> getAll() {
        return customerRepository.findByActiveTrueOrderByNameAsc()
                .stream()
                .map(CustomerResponse::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> getAllActive() {
        return customerRepository.findByActiveTrueOrderByNameAsc()
                .stream()
                .map(CustomerResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> searchByName(String name) {
        return customerRepository
                .findByNameContainingIgnoreCaseAndActiveTrue(name)
                .stream()
                .map(CustomerResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer", "id", id));
        customer.setActive(false);
        customerRepository.save(customer);
    }

    private void mapToEntity(CustomerRequest r, Customer c) {
        c.setName(r.getName());
        c.setPhone(r.getPhone());
        c.setEmail(r.getEmail());
        c.setAddress(r.getAddress());
        c.setVillage(r.getVillage());
        c.setTaluka(r.getTaluka());
        c.setDistrict(r.getDistrict());
        c.setAadharNumber(r.getAadharNumber());
        c.setOpeningBalance(r.getOpeningBalance());
        c.setActive(true);
    }
}