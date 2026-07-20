package com.agroerp.modules.supplier.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SupplierRequest {

    @NotBlank(message = "Supplier name is required")
    @Size(min = 2, max = 150)
    private String name;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 15, message = "Enter valid phone number")
    private String phone;

    private String email;
    private String address;
    private String city;
    private String gstNumber;

    public SupplierRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getGstNumber() { return gstNumber; }
    public void setGstNumber(String gstNumber) { this.gstNumber = gstNumber; }
}