package com.enoca.challenge.models.order;

import com.enoca.challenge.models.customer.Customer;
import com.enoca.challenge.models.product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue
    long orderId;

    @ManyToOne
    @JoinColumn(name="productId", referencedColumnName = "productId")
    Product product;

    @ManyToOne
    @JoinColumn(name="customerId", referencedColumnName = "customerId")
    Customer customer;

    @NotBlank
    String orderCode;

    double productPrice;

    int productCount;

    public Order(){}

    public Order(
        Customer customer, 
        Product product,
        String orderCode, 
        double productPrice, 
        int productCount )
    {
        this.customer = customer;
        this.product = product;
        this.orderCode = orderCode;
        this.productCount = productCount;
        this.productPrice = productPrice;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }
}
