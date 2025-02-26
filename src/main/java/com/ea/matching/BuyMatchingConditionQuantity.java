package com.ea.matching;

import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;

import static com.ea.model.ReportFactory.createFilledReport;

public class BuyMatchingConditionQuantity implements MatchingCondition {
    @Override
    public Report process(OrderBook orderBook, Order orderCandidate, Order order) {
        int executedQuantity;
        if (orderCandidate.getQuantity() >= order.getQuantity()) {
            executedQuantity = order.getQuantity();
            orderCandidate.setQuantity(orderCandidate.getQuantity() - order.getQuantity());
        } else {
            executedQuantity = orderCandidate.getQuantity();
            orderCandidate.setQuantity(0);
        }
        System.out.println("[SERVER] BUY FILLED at $" + orderCandidate.getPrice() + " for " + order.getQuantity() + " units");
        return createFilledReport(order.getQuantity(), orderCandidate.getPrice(), executedQuantity, order.getAccountId());
    }
}