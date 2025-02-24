package com.ea.model;

import com.ea.model.Order;
import com.ea.model.OrderType;

public class OrderFactory {
    public static Order createBuyOrder(int quantity, int price, String accountId) {
        return new Order(OrderType.BUY, quantity, price, accountId);
    }

    public static Order createSellOrder(int quantity, int price, String accountId) {
        return new Order(OrderType.SELL, quantity, price, accountId);
    }
}