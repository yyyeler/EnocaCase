package com.enoca.challenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.enoca.challenge.error.ErrorMessage;
import com.enoca.challenge.models.product.Product;
import com.enoca.challenge.services.ProductService;

import jakarta.validation.Valid;

@RestController
public class ProductController 
{
    @Autowired
    ProductService productService;

    @Autowired
    ErrorMessage errorMessage;

    @GetMapping("/api/v1/GetProducts")
    public List<Product> getProducts()
    {
        return productService.getAllProducts();
    }

    @GetMapping("/api/v1/GetProduct/{id}")
    public Product getProduct(@PathVariable long id) throws Exception
    {
        return productService.getProduct(id);
    }

    @PostMapping("/api/v1/CreateProduct")
    public ErrorMessage createProduct(@Valid @RequestBody Product product)
    {
        productService.createProduct(product);
        errorMessage.setAll("200", "Product is created");    
    
        return errorMessage;
    }

    @DeleteMapping("/api/v1/DeleteProduct/{id}")
    public ErrorMessage deleteProduct(@PathVariable long id)
    {
        productService.deleteProduct(id);
        errorMessage.setAll("200", "Product is deleted"); 

        return errorMessage;
    }

    @PutMapping("/api/v1/UpdateProduct/{id}")
    public ErrorMessage updateProduct(@PathVariable long id, @Valid @RequestBody Product product) throws Exception
    {
        productService.updateProduct(id, product);
        errorMessage.setAll("200", "Product is updated");
        
        return errorMessage;
    }
}

