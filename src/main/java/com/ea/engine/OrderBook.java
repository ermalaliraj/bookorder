package com.ea.engine;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class OrderBook {
    private final PriorityQueue<Order> buyOrders = new PriorityQueue<>(Comparator.comparingInt(o -> -o.price)); // Highest price first, FIFO on same price
    private final PriorityQueue<Order> sellOrders = new PriorityQueue<>(Comparator.comparingInt(o -> o.price)); // Lowest price first, FIFO on same price

    public OrderBook(List<Order> buyOrders, List<Order> sellOrders) {
        this.buyOrders.addAll(buyOrders);
        this.sellOrders.addAll(sellOrders);
    }

    public synchronized Report processMarketOrder(Order order) {
        if (order.type == OrderType.BUY) {
            return matchSellOrder(order);
        } else if (order.type == OrderType.SELL) {
            return matchBuyPrice(order);
        } else {
            throw new IllegalArgumentException("Not known order type! Order: " + order);
        }
    }

    private Report matchSellOrder(Order order) {
        while (!sellOrders.isEmpty()) {
            Order bestSell = sellOrders.peek(); // Get the lowest ASK price
            if (bestSell.price > order.price) {
                System.out.println("[SERVER] Cannot match! Best ASK " + bestSell.price + " is higher than the order price " + order.price);
                break;
            }
            if (bestSell.quantity == order.quantity) { // Exact match
                sellOrders.poll();
                System.out.println("[SERVER] BUY at $" + bestSell.price + " for " + order.quantity + " units");
                return new Report(ReportType.exe_report, sellOrders.size(), order.price, order.quantity, order.accountId, ReportStatus.FILLED);
            }
            System.out.println("[SERVER] No full match found! Order quantity is " + bestSell.quantity + ", you are requesting to buy " + order.quantity);
            break;
        }
        return new Report(ReportType.exe_report, sellOrders.size(), null, null, order.accountId, ReportStatus.REJECTED);
    }

    private Report matchBuyPrice(Order order) {
        while (!buyOrders.isEmpty()) {
            Order bestBuy = buyOrders.peek(); // Get the highest BID price
            if (bestBuy.price < order.price) {
                System.out.println("[SERVER] Cannot match! Best BID " + bestBuy.price + " is lower than the order price " + order.price);
                break;
            }
            if (bestBuy.quantity == order.quantity) { // Exact match
                buyOrders.poll();
                System.out.println("[SERVER] SELL FILLED at $" + bestBuy.price + " for " + order.quantity + " units.");
                return new Report(ReportType.exe_report, buyOrders.size(), order.price, order.quantity, order.accountId, ReportStatus.FILLED);
            }
            System.out.println("[SERVER] No full match found! Order quantity is " + bestBuy.quantity + ", you are requesting to sell " + order.quantity);
            break;
        }
        return new Report(ReportType.exe_report, buyOrders.size(), order.price, order.quantity, order.accountId, ReportStatus.REJECTED);
    }

    @Override
    public String toString() {
        return "OrderBook{" +
                "buyOrders=" + buyOrders +
                ", sellOrders=" + sellOrders +
                '}';
    }
}
