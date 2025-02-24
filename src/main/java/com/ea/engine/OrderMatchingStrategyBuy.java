package com.ea.engine;

import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;
import com.ea.model.ReportStatus;
import com.ea.model.ReportType;

public class OrderMatchingStrategyBuy implements OrderMatchingStrategy {
    @Override
    public Report match(OrderBook orderBook, Order order) {
        while (!orderBook.getSellOrders().isEmpty()) {
            Order bestSell = orderBook.getSellOrders().peek();
            if (bestSell.getPrice() > order.getPrice()) {
                System.out.println("[SERVER] Cannot match! Best ASK " + bestSell.getPrice() + " is higher than " + order.getPrice());
                break;
            }
            if (bestSell.getQuantity() == order.getQuantity()) {
                orderBook.getSellOrders().poll();
                System.out.println("[SERVER] BUY FILLED at $" + bestSell.getPrice() + " for " + order.getQuantity() + " units");
                return new Report(ReportType.exe_report, orderBook.getSellOrders().size(), order.getPrice(), order.getQuantity(), order.getAccountId(), ReportStatus.FILLED);
            }
            break;
        }
        return new Report(ReportType.exe_report, orderBook.getSellOrders().size(), null, null, order.getAccountId(), ReportStatus.REJECTED);
    }
}
