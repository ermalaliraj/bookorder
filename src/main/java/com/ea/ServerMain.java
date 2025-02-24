package com.ea;

import com.ea.engine.Order;
import com.ea.engine.OrderBook;
import com.ea.engine.OrderType;
import com.ea.server.Server;

import java.util.ArrayList;
import java.util.List;

public class ServerMain {
    public static void main(String[] args) throws InterruptedException {
        List<Order> buyOrders = new ArrayList<>();
        buyOrders.add(new Order(OrderType.BUY, 10, 1000, "1001"));
        buyOrders.add(new Order(OrderType.BUY, 20, 1003, "1002"));
        buyOrders.add(new Order(OrderType.BUY, 30, 1007, "1003"));
        buyOrders.add(new Order(OrderType.BUY, 40, 1009, "1004"));

        List<Order> sellOrders = new ArrayList<>();
        sellOrders.add(new Order(OrderType.SELL, 50, 1009, "2001"));
        sellOrders.add(new Order(OrderType.SELL, 23, 10123, "2002"));
        
        OrderBook orderBook = new OrderBook(buyOrders, sellOrders);
        
        new Thread(() -> {
            try {
                new Server(orderBook).start(8080);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(2000);
    }
}
