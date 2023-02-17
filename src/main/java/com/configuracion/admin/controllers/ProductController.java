package com.configuracion.admin.controllers;


import com.configuracion.admin.exceptions.ProductException;
import com.configuracion.admin.models.ApiResponse;
import com.configuracion.admin.models.ProductsModel;
import com.configuracion.admin.models.StockModel;
import com.configuracion.admin.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/products",  produces = "application/json; charset=UTF-8")
@Validated
public class ProductController {

    @Autowired
    ProductServices productServices;

    @GetMapping
    public @ResponseBody ApiResponse<ArrayList<ProductsModel> >getProductList(){
        return ApiResponse.success().message("Listed").object(productServices.listAllProducts());
    }

    @PostMapping
    public  @ResponseBody ApiResponse<ProductsModel> createProducts(@RequestBody ProductsModel productsModel){
        return ApiResponse.success().message("success create").object(productServices.createProduct(productsModel));
    }

    @PatchMapping
    public  @ResponseBody ApiResponse<String> addStock(@Valid @RequestBody StockModel stockModel) throws ProductException {
           return ApiResponse.success().message(productServices.updateStock(stockModel)).build();
    }

    @DeleteMapping
    public @ResponseBody ApiResponse<String> deleteProduct(@Valid @RequestParam Long id) throws ProductException {
        return ApiResponse.success().message(productServices.deleteProduct(id)).build();
    }

    @GetMapping(value = "/best-seller")
    public @ResponseBody ApiResponse<ProductsModel> getBestSellingProduct(){
        return ApiResponse.success().message("Best selling product").object(productServices.bestSellingProduct());
    }

    @GetMapping(value = "/more-stock")
    public @ResponseBody ApiResponse<ProductsModel> getProductMoreStock(){
        return ApiResponse.success().message("Best selling product").object(productServices.productMoreStock());
    }
}
