package com.invoice.service.controller;

import com.invoice.service.dto.InvoiceDto;
import com.invoice.service.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping("/invoices")
    public List<InvoiceDto> getInvoices(@RequestParam("userId") String userId) {
        return invoiceService.getInvoiceList(userId);
    }

}
