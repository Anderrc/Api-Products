package com.configuracion.admin.repositories;

import com.configuracion.admin.models.InvoiceDetails;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface InvoicesDetailsRepository  extends CrudRepository<InvoiceDetails, Long> {

    @Modifying(clearAutomatically=true, flushAutomatically=true)
    @Query(value = "Select * from tb_invoice_details where id_invoices = :idInvoices", nativeQuery = true)
    Iterable<InvoiceDetails> findAllByIdInvoices(@Param("idInvoices") Long idInvoices);

}
