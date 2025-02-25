package com.ea.matching;

import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;

import static com.ea.model.ReportFactory.createFilledReport;

public class SellMatchingConditionQuantity implements MatchingCondition {
    @Override
    public Report process(OrderBook orderBook, Order orderCandidate, Order order) {
        if (orderCandidate.getQuantity() >= order.getQuantity()) {
            orderCandidate.setQuantity(orderCandidate.getQuantity() - order.getQuantity());
            if (orderCandidate.getQuantity() == 0) {
                orderBook.getBuyOrders().poll(); // Remove fully matched order
            }
            System.out.println("[SERVER] SELL FILLED at $" + orderCandidate.getPrice() + " for " + order.getQuantity() + " units");
            return createFilledReport(
                    orderBook.getBuyOrders().size(),
                    orderCandidate.getPrice(),
                    orderCandidate.getQuantity(),
                    order.getAccountId());
        }
        return null;
    }
}