package com.ea.model;

import java.util.Comparator;
import java.util.PriorityQueue;

public class OrderBook {
    private final PriorityQueue<Order> buyOrders = new PriorityQueue<>(Comparator.comparingInt(o -> -o.price)); // Highest price first, FIFO on same price
    private final PriorityQueue<Order> sellOrders = new PriorityQueue<>(Comparator.comparingInt(o -> o.price)); // Lowest price first, FIFO on same price

    public synchronized Report processMarketOrder(Order order) {
        if (order.type == Order.Type.BUY) {
            return matchSellOrder(order);
        } else if (order.type == Order.Type.SELL) {
            return matchBuyPrice(order);
        } else {
            throw new IllegalArgumentException("Not known order type! Order: " + order);
        }
    }
    
    private Report matchSellOrder(Order order) {
        while (!sellOrders.isEmpty()) {
            Order bestSell = sellOrders.peek(); // Get the lowest ASK price
            if (bestSell.price > order.price) {
                break; // Cannot match, best ASK is too high
            }
            if (bestSell.quantity == order.quantity) { // Exact match
                sellOrders.poll(); // Remove matched sell order
                System.out.println("BUY FILLED at $" + bestSell.price + " for " + order.quantity + " units.");
                return new Report(ReportType.exe_report, sellOrders.size(), order.price, order.quantity, order.accountId, ReportStatus.FILLED);
            }
            break; // No full match found
        }
        return new Report(ReportType.exe_report, sellOrders.size(), null, null, order.accountId, ReportStatus.REJECTED);
    }

    private Report matchBuyPrice(Order order) {
        while (!buyOrders.isEmpty()) {
            Order bestBuy = buyOrders.peek(); // Get the highest BID price
            if (bestBuy.price < order.price) {
                break; // Cannot match, best BID is too low
            }
            if (bestBuy.quantity == order.quantity) { // Exact match
                buyOrders.poll(); // Remove matched buy order
                System.out.println("SELL FILLED at $" + bestBuy.price + " for " + order.quantity + " units.");
                return new Report(ReportType.exe_report, buyOrders.size(), order.price, order.quantity, order.accountId, ReportStatus.FILLED);
            }
            break; // No full match found
        }
        return new Report(ReportType.exe_report, buyOrders.size(), order.price, order.quantity, order.accountId, ReportStatus.FILLED);
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
