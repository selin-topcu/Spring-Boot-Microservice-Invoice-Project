package com.invoice.service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class InvoiceError {
    private String errorMessage;
    private List<String> errorDetails;
}
