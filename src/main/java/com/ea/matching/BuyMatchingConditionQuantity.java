package com.ea.matching;

import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;
import com.ea.model.ReportStatus;
import com.ea.model.ReportType;

public class BuyMatchingConditionQuantity implements MatchingCondition {
    @Override
    public Report process(OrderBook orderBook, Order orderCandidate, Order order) {
        if (orderCandidate.getQuantity() >= order.getQuantity()) {
            orderCandidate.setQuantity(orderCandidate.getQuantity() - order.getQuantity());
            if (orderCandidate.getQuantity() == 0) {
                orderBook.getSellOrders().poll(); // Remove fully matched order
            }
            System.out.println("[SERVER] BUY FILLED at $" + orderCandidate.getPrice() + " for " + order.getQuantity() + " units");
            return new Report(ReportType.exe_report, 
                    orderBook.getSellOrders().size(), 
                    order.getPrice(), 
                    order.getQuantity(), 
                    order.getAccountId(),
                    ReportStatus.FILLED);
        }
        return null;
    }
}