package com.configuracion.admin.services;

import com.configuracion.admin.exceptions.ProductException;
import com.configuracion.admin.models.ProductsModel;
import com.configuracion.admin.models.StockModel;
import com.configuracion.admin.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductServices {

    @Autowired
    ProductRepository productRepository;

    public ArrayList<ProductsModel> listAllProducts() {
        return (ArrayList<ProductsModel>) productRepository.findAll();
    }

    public ProductsModel productById (Long id) throws ProductException {
        Optional<ProductsModel> productsModel = productRepository.findById(id);
        if (productsModel.isPresent()){
            return productsModel.get();
        }else{
            throw new ProductException("This product does not exist");
        }
    }

    public ProductsModel createProduct(ProductsModel productsModel) {
        productsModel.setState(true);
        return productRepository.save(productsModel);
    }

    public String updateStock(StockModel stockModel) throws ProductException {
        validProduct(stockModel.getId());
        try {
            productRepository.updateStock(stockModel.getId(), stockModel.getStock());
            return "Update Success";
        } catch (Exception e) {
            throw new ProductException("Error to update", e);
        }
    }

    public String deleteProduct(Long id) throws ProductException {
        validProduct(id);
        try {
            productRepository.changeState(id);
            return "Delete Success";
        } catch (Exception e) {
            throw new ProductException("Error to delete", e);
        }
    }
    public void validStock(Long id, Integer cant) throws ProductException {
        validProduct(id);
        ProductsModel productsModelResult = productRepository.findById(id).get();
        if(cant > productsModelResult.getStock()){
            throw  new ProductException("Insufficient stock");
        }
    }

    public ProductsModel bestSellingProduct(){
        return  productRepository.findBestSellingProduct().iterator().next();

    }

    public ProductsModel productMoreStock (){
        return productRepository.findProductMoreStock().iterator().next();
    }
    public void reduceStock(Long id, Integer cant) throws ProductException {
        ProductsModel productsModel = productById(id);
        StockModel stockModel = new StockModel(id, productsModel.getStock() - cant);
        updateStock(stockModel);
    }

    public void validProduct(Long id) throws ProductException {
        Optional<ProductsModel> result = productRepository.findById(id);
        if (result.isEmpty()) {
            throw new ProductException("This product does not exist");
        }else if(Boolean.FALSE.equals(result.get().getState())){
            throw new ProductException("This product does delete");
        }
    }



}
