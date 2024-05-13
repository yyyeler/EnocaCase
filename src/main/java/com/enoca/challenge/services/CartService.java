package com.enoca.challenge.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enoca.challenge.models.cart.Cart;
import com.enoca.challenge.models.cart.CartDTO;
import com.enoca.challenge.models.cart.CartDetailedDTO;
import com.enoca.challenge.models.cart.CartRepository;
import com.enoca.challenge.models.customer.Customer;
import com.enoca.challenge.models.product.Product;
import com.enoca.challenge.models.product.ProductDTO;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    public CartDetailedDTO getCart(long customerId) 
    {
        double cartTotalPrice = 0.0;
        List<Cart> list = cartRepository.findCartsByCustomerId(customerId);
        List<ProductDTO> newList = list.stream().map((cart) -> 
                                        this.toProductDTO(cart.getProduct(),cart.getProductCount())
                                    ).collect(Collectors.toList());
        for (ProductDTO productDTO : newList) 
        {
            cartTotalPrice += productDTO.getProductTotalPrice();
        }
        return new CartDetailedDTO(newList, cartTotalPrice);
    }

    public boolean addCart(CartDTO tempCart, long customerId) throws Exception
    {
        Customer customer = customerService.getCustomer(customerId);
        Product product = productService.getProduct(tempCart.getProductId());

        Cart cart = new Cart(customer,product,tempCart.getProductCount());

        Cart cartInDB = cartRepository.findCartByCustomerIdAndProductId(cart.getCustomer().getCustomerId(),cart.getProduct().getProductId());
        if(cartInDB != null)
        {
            int newProductCount = cartInDB.getProductCount()+cart.getProductCount();
            if(cartInDB.getProduct().getProductStock() < newProductCount)
                return false;
            cartInDB.setProductCount(newProductCount);
            cartRepository.saveAndFlush(cartInDB);
        }
        else
        {
            if(cart.getProduct().getProductStock() < cart.getProductCount())
                return false;
            cartRepository.saveAndFlush(cart);
        }

        return true;
    }

    public void removeProductFromCart(long customerId, long productId)
    {
        Cart cart = cartRepository.findCartByCustomerIdAndProductId(customerId, productId);
        cartRepository.delete(cart);
    }

    public void removeCart(long customerId)
    {
        List<Cart> cartList = cartRepository.findCartsByCustomerId(customerId);
        cartRepository.deleteAll(cartList);
    }
     
    public boolean updateCart(long customerId, List<CartDTO> list) throws Exception
    {
        List<Cart> newList = new ArrayList<Cart>();
        this.removeCart(customerId);
        
        for (CartDTO cartDTO : list) 
        {
            Product product = productService.getProduct(cartDTO.getProductId());

            if(product.getProductStock() < cartDTO.getProductCount())
                return false;

            newList.add(
                new Cart(
                    customerService.getCustomer(customerId), 
                    product, 
                    cartDTO.getProductCount()
                    )
                    );
        }

        cartRepository.saveAllAndFlush(newList);
        return true;
    }
    
    public ProductDTO toProductDTO(Product product, int productCount){
        return new ProductDTO(
            product.getProductId(),
            product.getProductName(),
            product.getProductPrice(),
            productCount,
            (product.getProductPrice()*productCount)
        );
    }

}
