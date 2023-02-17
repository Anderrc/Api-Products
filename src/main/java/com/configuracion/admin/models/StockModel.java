package com.configuracion.admin.models;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StockModel {


    @NotNull(message = "Id is required")
    private Long id;

    @NotNull(message = "Id is required")
    private Integer stock;

    public Long getId() {
        return id;
    }

    public StockModel(Long id, Integer stock) {
        this.id = id;
        this.stock = stock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
