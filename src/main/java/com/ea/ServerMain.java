package com.ea;

import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.OrderFactory;
import com.ea.server.Server;

import java.util.ArrayList;
import java.util.List;

public class ServerMain {
    public static void main(String[] args) throws InterruptedException {
        List<Order> buyOrders = new ArrayList<>();
        buyOrders.add(OrderFactory.createBuyOrder(10, 1000, "1001"));
        buyOrders.add(OrderFactory.createBuyOrder(20, 1003, "1002"));
        buyOrders.add(OrderFactory.createBuyOrder(30, 1007, "1003"));
        buyOrders.add(OrderFactory.createBuyOrder(40, 1009, "1004"));

        List<Order> sellOrders = new ArrayList<>();
        sellOrders.add(OrderFactory.createSellOrder(50, 1009, "2001"));
        sellOrders.add(OrderFactory.createSellOrder(23, 10123, "2002"));

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
