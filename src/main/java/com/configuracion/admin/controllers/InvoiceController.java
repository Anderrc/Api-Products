package com.configuracion.admin.controllers;

import com.configuracion.admin.exceptions.ProductException;
import com.configuracion.admin.models.ApiResponse;
import com.configuracion.admin.models.InvoiceAndDetails;
import com.configuracion.admin.models.InvoiceModel;
import com.configuracion.admin.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @PostMapping
    public @ResponseBody ApiResponse<InvoiceModel> createInvoices(@RequestBody InvoiceAndDetails invoiceAndDetails) throws ProductException {
        return ApiResponse.success().message("Success create").object(invoiceService.createInvoices(invoiceAndDetails));
    }

    @GetMapping
    public @ResponseBody ApiResponse<ArrayList<InvoiceModel>> getAllInvoices(){
        return ApiResponse.success().message("Listed").object(invoiceService.listInvoices());
    }

    @GetMapping(value = "/detail")
    public @ResponseBody ApiResponse<InvoiceAndDetails> getDetails(@RequestParam Long id) throws ProductException {
        return ApiResponse.success().message("invoice").object(invoiceService.invoiceDetails(id));
    }

}
