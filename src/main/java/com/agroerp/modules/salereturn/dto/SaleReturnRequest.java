package com.agroerp.modules.salereturn.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class SaleReturnRequest {

    @NotBlank(message = "Return number is required")
    private String returnNumber;

    @NotNull(message = "Sale is required")
    private Long saleId;

    @NotNull(message = "Return date is required")
    private LocalDate returnDate;

    @NotBlank(message = "Reason is required")
    private String reason;

    private String notes;

    @NotEmpty(message = "At least one item is required")
    @Valid
    private List<SaleReturnItemRequest> items;

    public SaleReturnRequest() {}

    public String getReturnNumber() { return returnNumber; }
    public void setReturnNumber(String returnNumber) { this.returnNumber = returnNumber; }

    public Long getSaleId() { return saleId; }
    public void setSaleId(Long saleId) { this.saleId = saleId; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public List<SaleReturnItemRequest> getItems() { return items; }
    public void setItems(List<SaleReturnItemRequest> items) { this.items = items; }
}