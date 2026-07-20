package com.agroerp.modules.supplier.service;

import com.agroerp.exception.ResourceNotFoundException;
import com.agroerp.modules.supplier.dto.SupplierRequest;
import com.agroerp.modules.supplier.dto.SupplierResponse;
import com.agroerp.modules.supplier.entity.Supplier;
import com.agroerp.modules.supplier.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    @Transactional
    public SupplierResponse create(SupplierRequest request) {
        Supplier supplier = new Supplier();
        mapToEntity(request, supplier);
        return SupplierResponse.fromEntity(supplierRepository.save(supplier));
    }

    @Override
    @Transactional
    public SupplierResponse update(Long id, SupplierRequest request) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Supplier", "id", id));
        mapToEntity(request, supplier);
        return SupplierResponse.fromEntity(supplierRepository.save(supplier));
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierResponse getById(Long id) {
        return supplierRepository.findById(id)
                .map(SupplierResponse::fromEntity)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Supplier", "id", id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierResponse> getAll() {
        return supplierRepository.findByActiveTrueOrderByNameAsc()
                .stream()
                .map(SupplierResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierResponse> getAllActive() {
        return supplierRepository.findByActiveTrueOrderByNameAsc()
                .stream()
                .map(SupplierResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Supplier", "id", id));
        supplier.setActive(false);
        supplierRepository.save(supplier);
    }

    private void mapToEntity(SupplierRequest r, Supplier s) {
        s.setName(r.getName());
        s.setPhone(r.getPhone());
        s.setEmail(r.getEmail());
        s.setAddress(r.getAddress());
        s.setCity(r.getCity());
        s.setGstNumber(r.getGstNumber());
        s.setActive(true);
    }
}