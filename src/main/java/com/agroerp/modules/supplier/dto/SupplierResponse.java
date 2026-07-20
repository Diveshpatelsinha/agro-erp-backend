package com.agroerp.modules.supplier.dto;

import com.agroerp.modules.supplier.entity.Supplier;
import java.time.LocalDateTime;

public class SupplierResponse {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String gstNumber;
    private boolean active;
    private LocalDateTime createdAt;

    public SupplierResponse() {}

    public static SupplierResponse fromEntity(Supplier s) {
        SupplierResponse r = new SupplierResponse();
        r.id         = s.getId();
        r.name       = s.getName();
        r.phone      = s.getPhone();
        r.email      = s.getEmail();
        r.address    = s.getAddress();
        r.city       = s.getCity();
        r.gstNumber  = s.getGstNumber();
        r.active     = s.isActive();
        r.createdAt  = s.getCreatedAt();
        return r;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getGstNumber() { return gstNumber; }
    public boolean isActive() { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }
    public void setCity(String city) { this.city = city; }
    public void setGstNumber(String gstNumber) { this.gstNumber = gstNumber; }
    public void setActive(boolean active) { this.active = active; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}