package com.ea.model;

import com.ea.engine.OrderMatchingStrategyBuy;
import com.ea.engine.OrderMatchingStrategy;
import com.ea.engine.OrderMatchingStrategySell;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class OrderBook {
    private final PriorityQueue<Order> buyOrders = new PriorityQueue<>(Comparator.comparingInt(o -> -o.getPrice())); // Highest price first, FIFO on same price
    private final PriorityQueue<Order> sellOrders = new PriorityQueue<>(Comparator.comparingInt(o -> o.getPrice())); // Lowest price first, FIFO on same price

    public OrderBook(List<Order> buyOrders, List<Order> sellOrders) {
        this.buyOrders.addAll(buyOrders);
        this.sellOrders.addAll(sellOrders);
    }

    public synchronized Report processMarketOrder(Order order) {
        OrderMatchingStrategy orderMatching = order.getType() == OrderType.BUY ?
                new OrderMatchingStrategyBuy() :
                new OrderMatchingStrategySell();
        return orderMatching.match(this, order);
    }

    public PriorityQueue<Order> getBuyOrders() {
        return buyOrders;
    }

    public PriorityQueue<Order> getSellOrders() {
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
