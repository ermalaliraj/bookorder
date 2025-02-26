package com.ea.model;

import com.ea.engine.OrderMatching;
import com.ea.engine.OrderMatchingFactory;

import java.util.List;
import java.util.TreeSet;

public class OrderBook {

    private final TreeSet<Order> buyOrders = new TreeSet<>((Order o1, Order o2) -> { // Highest price first, FIFO on same price
        return (o1.getPrice() > o2.getPrice()) ? -1 : 1;
    });
    private final TreeSet<Order> sellOrders = new TreeSet<>((Order o1, Order o2) -> { // Lowest price first, FIFO on same price
        return (o1.getPrice() < o2.getPrice()) ? -1 : 1;
    });

    public OrderBook(List<Order> buyOrders, List<Order> sellOrders) {
        this.buyOrders.addAll(buyOrders);
        this.sellOrders.addAll(sellOrders);
    }

    public synchronized Report processOrder(Order order) {
        OrderMatching orderMatching = OrderMatchingFactory.getMatcher(order.getType()); // BUY or SELL
        Report report = orderMatching.match(this, order);
        return report;
    }

    public TreeSet<Order> getBuyOrders() {
        return buyOrders;
    }

    public TreeSet<Order> getSellOrders() {
        return sellOrders;
    }

    @Override
    public String toString() {
        return "OrderBook{" +
                "buyOrders=" + buyOrders +
                ", sellOrders=" + sellOrders +
                '}';
    }
}
