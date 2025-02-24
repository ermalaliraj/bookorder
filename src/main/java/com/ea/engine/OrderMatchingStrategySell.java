package com.ea.engine;

import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;
import com.ea.model.ReportStatus;
import com.ea.model.ReportType;

public class OrderMatchingStrategySell implements OrderMatchingStrategy {
    @Override
    public Report match(OrderBook orderBook, Order order) {
        while (!orderBook.getBuyOrders().isEmpty()) {
            Order bestBuy = orderBook.getBuyOrders().peek(); // Get highest BID price
            if (bestBuy.getPrice() < order.getPrice()) {
                System.out.println("[SERVER] Cannot match! Best BID " + bestBuy.getPrice() + " is lower than " + order.getPrice());
                break;
            }
            if (bestBuy.getQuantity() >= order.getQuantity()) {
                bestBuy.setQuantity(bestBuy.getQuantity() - order.getQuantity());
                if (bestBuy.getQuantity() == 0) {
                    orderBook.getBuyOrders().poll(); // Remove fully matched order
                }
                System.out.println("[SERVER] SELL FILLED at $" + bestBuy.getPrice() + " for " + order.getQuantity() + " units");
                return new Report(ReportType.exe_report, orderBook.getBuyOrders().size(), order.getPrice(), order.getQuantity(), order.getAccountId(), ReportStatus.FILLED);
            }
            break;
        }
        return new Report(ReportType.exe_report, orderBook.getBuyOrders().size(), order.getPrice(), order.getQuantity(), order.getAccountId(), ReportStatus.REJECTED);
    }
}
