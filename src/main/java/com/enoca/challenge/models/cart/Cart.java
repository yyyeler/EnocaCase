package com.enoca.challenge.models.cart;

import com.enoca.challenge.models.customer.Customer;
import com.enoca.challenge.models.product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Cart")
public class Cart {
    @Id
    @GeneratedValue
    long cartId;

    @ManyToOne
    @JoinColumn(name = "customerId" , referencedColumnName = "customerId")
    Customer customer;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    Product product;

    int productCount;

    public Cart(){}

    public Cart(Customer customer,Product product,int productCount)
    {
        this.customer = customer;
        this.product = product;
        this.productCount = productCount;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public CartDTO toCardDTO() 
    {
        return new CartDTO(
            this.getProduct().getProductId(),
            this.getProductCount(),
            (this.getProductCount()*this.getProduct().getProductPrice())
        );
    }
}

