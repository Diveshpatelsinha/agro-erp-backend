package com.agroerp.modules.customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class CustomerRequest {

    @NotBlank(message = "Customer name is required")
    @Size(min = 2, max = 150)
    private String name;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 15, message = "Enter valid phone number")
    private String phone;

    private String email;
    private String address;
    private String village;
    private String taluka;
    private String district;
    private String aadharNumber;
    private BigDecimal openingBalance = BigDecimal.ZERO;

    public CustomerRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getVillage() { return village; }
    public void setVillage(String village) { this.village = village; }

    public String getTaluka() { return taluka; }
    public void setTaluka(String taluka) { this.taluka = taluka; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getAadharNumber() { return aadharNumber; }
    public void setAadharNumber(String aadharNumber) { this.aadharNumber = aadharNumber; }

    public BigDecimal getOpeningBalance() { return openingBalance; }
    public void setOpeningBalance(BigDecimal openingBalance) { this.openingBalance = openingBalance; }
}