package com.user.service.service;

import com.user.service.client.InvoiceServiceFeignClient;
import com.user.service.dto.InvoiceDTO;
import com.user.service.exception.InvoiceException;
import com.user.service.exception.UserException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceServiceFeignClient invoiceServiceFeignClient;
    private final RetryTemplate retryTemplate;

    public List<InvoiceDTO> callInvoiceService(String userId) {

        AtomicReference<List<InvoiceDTO>> invoiceResponse = new AtomicReference<>(new ArrayList<>());

        try {

            retryTemplate.execute(arg ->{
               invoiceResponse.set(invoiceServiceFeignClient.getInvoices(userId));
               return invoiceResponse;
            });

        } catch (InvoiceException invoiceException) {
            throw invoiceException;
        } catch (FeignException.ServiceUnavailable ex) {
            throw new UserException("Downstream service unavailable", List.of("invoice-service is down"), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            throw new UserException("Internal server error", List.of("invoice-service something is wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return invoiceResponse.get();
    }
}
