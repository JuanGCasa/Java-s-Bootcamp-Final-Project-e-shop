package com.finalproject.eshop.service;

import com.finalproject.eshop.dto.Purchase;
import com.finalproject.eshop.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

}
