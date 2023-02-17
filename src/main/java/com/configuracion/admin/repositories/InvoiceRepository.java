package com.configuracion.admin.repositories;

import com.configuracion.admin.models.InvoiceModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends CrudRepository<InvoiceModel, Long> {
}
