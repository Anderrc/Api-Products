package com.configuracion.admin.services;

import com.configuracion.admin.models.InvoiceAndDetails;
import com.configuracion.admin.models.InvoiceDetails;
import com.configuracion.admin.models.InvoiceModel;
import com.configuracion.admin.repositories.InvoicesDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InvoicesDetailsServices {

    @Autowired
    InvoicesDetailsRepository invoicesDetailsRepository;

    public ArrayList<InvoiceDetails> createInvoices(ArrayList<InvoiceDetails>  invoiceDetails){
        return (ArrayList<InvoiceDetails>) invoicesDetailsRepository.saveAll(invoiceDetails);
    }

    public ArrayList<InvoiceDetails> listInvoicesDetails(Long idInvoice){
        return (ArrayList<InvoiceDetails>) invoicesDetailsRepository.findAllByIdInvoices(idInvoice);
    }
}
