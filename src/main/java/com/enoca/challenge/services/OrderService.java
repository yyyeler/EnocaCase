package com.enoca.challenge.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enoca.challenge.models.cart.CartDetailedDTO;
import com.enoca.challenge.models.customer.Customer;
import com.enoca.challenge.models.order.Order;
import com.enoca.challenge.models.order.OrderDTO;
import com.enoca.challenge.models.order.OrderRepository;
import com.enoca.challenge.models.product.Product;
import com.enoca.challenge.models.product.ProductDTO;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartService cartService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    public boolean placeOrder(long customerId) throws Exception
    {
        List<Order> orderList = new ArrayList<Order>();

        Customer customer = customerService.getCustomer(customerId);
        CartDetailedDTO cart = cartService.getCart(customerId);
        String orderCode = this.generateOrderCode();

        for (ProductDTO productDto : cart.getProductList()) {
            Product product = productService.getProduct(productDto.getProductId());
            if(productDto.getProductCount() > product.getProductStock())
                return false;

            orderList.add(
                new Order(
                    customer,
                    product,
                    orderCode,
                    productDto.getProductPrice(),
                    productDto.getProductCount()
                ));
        }

        cartService.removeCart(customerId);

        orderRepository.saveAllAndFlush(orderList);
        return true;
    }

    public OrderDTO getOrder(long customerId, String orderCode) throws Exception
    {
        double orderTotalPrice = 0.0;

        List<Order> orderList = orderRepository.findAllByOrderCodeAndCustomerId(customerId,orderCode);

        if(orderList == null || orderList.isEmpty()) 
            throw new Exception("There is no order with this order code");  

        List<ProductDTO> newList = orderList.stream().map((order) -> 
            new ProductDTO(
                order.getProduct().getProductId(),
                order.getProduct().getProductName(),
                order.getProductPrice(),
                order.getProductCount(),
                (order.getProductPrice()*order.getProductCount())
            )
        ).collect(Collectors.toList());

        for (ProductDTO productDTO : newList) 
        {
            orderTotalPrice += productDTO.getProductTotalPrice();
        }

        return new OrderDTO(orderCode, newList, orderTotalPrice);
    }

    public List<OrderDTO> getAllOrdersForCustomer(long customerId) throws Exception
    {
        List<String> orderCodeList= orderRepository.findOrderCodeByCustomerId(customerId);
        List<OrderDTO> orderList = new ArrayList<OrderDTO>();

        for (String orderCode : orderCodeList) 
        {
            orderList.add(this.getOrder(customerId, orderCode));
        }

        return orderList;
    }

    public String generateOrderCode()
    {
        List<String> orderList = orderRepository.findAllOrderCode();
        String tempOrderCode = "", contentText = "ABCDEFGHIJKLMNOPRSQTUVWXYZ0123456789";
        char[] charArray = new char[8];
        Random random = new Random();
        
        while(true)
        {
            for(int i=0;i<8;i++)
            {
                charArray[i] = contentText.charAt(random.nextInt(contentText.length()));
                tempOrderCode = new String(charArray);
            }

            if(!orderList.contains(tempOrderCode))
                break;
            else
                tempOrderCode = "";
        }

        return tempOrderCode;
    }
}
