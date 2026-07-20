package com.agroerp.modules.customer.dto;

import com.agroerp.modules.customer.entity.Customer;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CustomerResponse {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String village;
    private String taluka;
    private String district;
    private String aadharNumber;
    private BigDecimal openingBalance;
    private BigDecimal currentBalance;
    private boolean active;
    private LocalDateTime createdAt;

    public CustomerResponse() {}

    public static CustomerResponse fromEntity(Customer c) {
        CustomerResponse r = new CustomerResponse();
        r.id             = c.getId();
        r.name           = c.getName();
        r.phone          = c.getPhone();
        r.email          = c.getEmail();
        r.address        = c.getAddress();
        r.village        = c.getVillage();
        r.taluka         = c.getTaluka();
        r.district       = c.getDistrict();
        r.aadharNumber   = c.getAadharNumber();
        r.openingBalance = c.getOpeningBalance();
        r.currentBalance = c.getCurrentBalance();
        r.active         = c.isActive();
        r.createdAt      = c.getCreatedAt();
        return r;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getVillage() { return village; }
    public String getTaluka() { return taluka; }
    public String getDistrict() { return district; }
    public String getAadharNumber() { return aadharNumber; }
    public BigDecimal getOpeningBalance() { return openingBalance; }
    public BigDecimal getCurrentBalance() { return currentBalance; }
    public boolean isActive() { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }
    public void setVillage(String village) { this.village = village; }
    public void setTaluka(String taluka) { this.taluka = taluka; }
    public void setDistrict(String district) { this.district = district; }
    public void setAadharNumber(String aadharNumber) { this.aadharNumber = aadharNumber; }
    public void setOpeningBalance(BigDecimal openingBalance) { this.openingBalance = openingBalance; }
    public void setCurrentBalance(BigDecimal currentBalance) { this.currentBalance = currentBalance; }
    public void setActive(boolean active) { this.active = active; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}