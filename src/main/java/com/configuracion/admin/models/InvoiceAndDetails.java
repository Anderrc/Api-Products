package com.configuracion.admin.models;

import java.util.ArrayList;

public class InvoiceAndDetails{

    private ArrayList<InvoiceDetails> products;
    private InvoiceModel invoiceModel;

    public InvoiceModel getInvoiceModel() {
        return invoiceModel;
    }

    public void setInvoiceModel(InvoiceModel invoiceModel) {
        this.invoiceModel = invoiceModel;
    }

    public ArrayList<InvoiceDetails> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<InvoiceDetails> products) {
        this.products = products;
    }
}
