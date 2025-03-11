package com.invoice.service.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.invoice.service.dto.InvoiceDto;
import com.invoice.service.entities.Invoice;
import com.invoice.service.exception.InvoiceException;
import com.invoice.service.repositories.InvoiceRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ObjectMapper objectMapper;

    public List<InvoiceDto> getInvoiceList(String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new InvoiceException(HttpStatus.BAD_REQUEST, "Bad Request", List.of("userId not found"));
        }

        List<Invoice> invoices = invoiceRepository.findAllByUserId(userId);
        List<InvoiceDto> invoiceDtoList = objectMapper.convertValue(invoices, new TypeReference<>() {
        });

        return invoiceDtoList;
    }
}
