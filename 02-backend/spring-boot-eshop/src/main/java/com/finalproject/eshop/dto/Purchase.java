package com.finalproject.eshop.dto;

import com.finalproject.eshop.entity.Address;
import com.finalproject.eshop.entity.Customer;
import com.finalproject.eshop.entity.Order;
import com.finalproject.eshop.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
