package com.configuracion.admin.services;

import com.configuracion.admin.exceptions.ProductException;
import com.configuracion.admin.models.InvoiceAndDetails;
import com.configuracion.admin.models.InvoiceDetails;
import com.configuracion.admin.models.InvoiceModel;
import com.configuracion.admin.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class InvoiceService {


    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    InvoicesDetailsServices invoicesDetailsServices;

    @Autowired
    ProductServices productServices;

    public InvoiceModel createInvoices(InvoiceAndDetails invoiceAndDetails) throws ProductException {
        Float total = calculateTotal(invoiceAndDetails);
        invoiceAndDetails.getInvoiceModel().setTotal(total);
        invoiceAndDetails.setInvoiceModel(invoiceAndDetails.getInvoiceModel());
        InvoiceModel invoiceModel = invoiceRepository.save(invoiceAndDetails.getInvoiceModel());
        addDetails(invoiceModel, invoiceAndDetails);
        return invoiceModel;
    }

    public void addDetails(InvoiceModel invoiceModel, InvoiceAndDetails invoiceAndDetails) throws ProductException {
        ArrayList<InvoiceDetails> invoiceDetails = new ArrayList<>();
        for(InvoiceDetails invoiceDetails1 :invoiceAndDetails.getProducts()) {
            invoiceDetails1.setIdInvoices(invoiceModel.getId());
            invoiceDetails.add(invoiceDetails1);
        }
        invoicesDetailsServices.createInvoices(invoiceDetails);
        for(InvoiceDetails invoiceDetails1 :invoiceAndDetails.getProducts()) {
            productServices.reduceStock(invoiceDetails1.getIdProduct(), invoiceDetails1.getCant());
        }
    }

    public Float calculateTotal(InvoiceAndDetails invoiceAndDetails) throws ProductException {
        Float total = (float) 0;

        for (InvoiceDetails invoiceDetails : invoiceAndDetails.getProducts() ) {
            Float neto = (float) invoiceDetails.getCant() * invoiceDetails.getValue();
            total = neto + total;
            productServices.validStock(invoiceDetails.getIdProduct(), invoiceDetails.getCant());
        }

        return total;
    }

    public ArrayList<InvoiceModel> listInvoices(){
        return (ArrayList<InvoiceModel>) invoiceRepository.findAll();
    }

    public InvoiceModel invoiceByID (Long id) throws ProductException {
        Optional<InvoiceModel> invoiceModelResult =  invoiceRepository.findById(id);
        if(invoiceModelResult.isEmpty()){
            throw new ProductException("Invoices Not Exist");
        }
        return invoiceModelResult.get();
    }


    public InvoiceAndDetails invoiceDetails(Long id) throws ProductException {

        InvoiceAndDetails invoiceAndDetails = new InvoiceAndDetails();
        invoiceAndDetails.setInvoiceModel(invoiceByID(id));
        invoiceAndDetails.setProducts(invoicesDetailsServices.listInvoicesDetails(id));
        return invoiceAndDetails;
    }
}
