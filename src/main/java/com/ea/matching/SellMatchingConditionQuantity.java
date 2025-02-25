package com.ea.matching;

import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;
import com.ea.model.ReportStatus;
import com.ea.model.ReportType;

public class SellMatchingConditionQuantity implements MatchingCondition {
    @Override
    public Report process(OrderBook orderBook, Order orderCandidate, Order order) {
        if (orderCandidate.getQuantity() >= order.getQuantity()) {
            orderCandidate.setQuantity(orderCandidate.getQuantity() - order.getQuantity());
            if (orderCandidate.getQuantity() == 0) {
                orderBook.getBuyOrders().poll(); // Remove fully matched order
            }
            System.out.println("[SERVER] SELL FILLED at $" + orderCandidate.getPrice() + " for " + order.getQuantity() + " units");
            return new Report(ReportType.exe_report, 
                    orderBook.getBuyOrders().size(), 
                    order.getPrice(), 
                    order.getQuantity(), 
                    order.getAccountId(), 
                    ReportStatus.FILLED);
        }
        return null;
    }
}