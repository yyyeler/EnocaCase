package com.enoca.challenge.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enoca.challenge.models.product.Product;
import com.enoca.challenge.models.product.ProductRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    public Product getProduct(long id) throws Exception
    {
        return productRepository.findById(id).orElseThrow(() -> new Exception());
    }

    public void createProduct(Product product)
    {
        try 
        {
            productRepository.saveAndFlush(product);
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }

    public void deleteProduct(long id)
    {
        productRepository.deleteById(id);
    }

    public void updateProduct(long id, Product product) throws Exception
    {
        Product productInDB = productRepository.findById(id).orElseThrow(() -> new Exception());

        if(productInDB != null)
        {
            productInDB.setProductName(product.getProductName());
            productInDB.setProductStock(product.getProductStock());
            productInDB.setProductPrice(product.getProductPrice());

            productRepository.saveAndFlush(productInDB);
        }
    }
}
