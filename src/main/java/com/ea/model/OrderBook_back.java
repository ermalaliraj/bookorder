package com.ea.model;

import java.util.Comparator;
import java.util.PriorityQueue;

public class OrderBook_back {
    private final PriorityQueue<Order> buyOrders = new PriorityQueue<>(Comparator.comparingInt(o -> -o.price)); // Highest price first, FIFO on same price
    private final PriorityQueue<Order> sellOrders = new PriorityQueue<>(Comparator.comparingInt(o -> o.price)); // Lowest price first, FIFO on same price

    public synchronized Report processMarketOrder(Order order) {
        Report report;
        if (order.type == Order.Type.BUY) {
            if (!sellOrders.isEmpty()) {
                Order match = sellOrders.poll();
                report = new Report(ReportType.exe_report, sellOrders.size(), match.price, match.quantity, match.accountId, ReportStatus.FILLED);
            } else {
                report = new Report(ReportType.exe_report, sellOrders.size(), null, null, order.accountId, ReportStatus.REJECTED);
            }
        } else if (order.type == Order.Type.SELL) {
            if (!buyOrders.isEmpty()) {
                Order match = buyOrders.poll();
                report = new Report(ReportType.exe_report, buyOrders.size(), match.quantity, match.price, match.accountId, ReportStatus.FILLED);
            } else {
                report = new Report(ReportType.exe_report, buyOrders.size(), null, null, order.accountId, ReportStatus.REJECTED);
            }
        } else {
            throw new IllegalArgumentException("Not known order type! Order: " + order);
        }
        return report;
    }

    public synchronized void addInitialOrders() {
        buyOrders.add(new Order(Order.Type.BUY, 10, 1000, "1001"));
        buyOrders.add(new Order(Order.Type.BUY, 20, 1003, "1002"));
        buyOrders.add(new Order(Order.Type.BUY, 30, 1007, "1003"));
        buyOrders.add(new Order(Order.Type.BUY, 40, 1009, "1004"));
        sellOrders.add(new Order(Order.Type.SELL, 50, 1009, "2001"));
        sellOrders.add(new Order(Order.Type.SELL, 23, 10123, "2002"));
    }
}
