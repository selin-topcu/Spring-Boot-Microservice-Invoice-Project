package com.invoice.service.dto;

import lombok.Data;

@Data
public class InvoiceDto {
    private String invoiceId;
    private String invoiceNumber;
    private String productIds;
    private String updatedTime;
}
