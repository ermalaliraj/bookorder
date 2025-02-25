package com.ea.model;

import com.ea.engine.OrderMatching;
import com.ea.engine.OrderMatchingFactory;

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

    public synchronized Report processOrder(Order order) {
        OrderMatching orderMatching = OrderMatchingFactory.getMatcher(order.getType()); // BUY or SELL
        Report report = orderMatching.match(this, order);
        //TODO do we need to pass the order from one list to the other?
//        if (report.getStatus() == ReportStatus.REJECTED) {
//            if (order.getType() == OrderType.BUY) {
//                buyOrders.add(order);
//            } else {
//                sellOrders.add(order);
//            }
//        }
        return report;
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
