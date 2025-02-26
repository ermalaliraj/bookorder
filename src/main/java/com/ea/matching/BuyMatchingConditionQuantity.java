package com.ea.matching;

import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;

import java.util.Iterator;

import static com.ea.model.ReportFactory.createFilledReport;

public class BuyMatchingConditionQuantity implements MatchingCondition {
    @Override
    public Report process(Iterator<Order> orderBookIterator, OrderBook orderBook, Order orderCandidate, Order order) {
        int executedQuantity;
        if (orderCandidate.getQuantity() >= order.getQuantity()) {
            executedQuantity = order.getQuantity();
            orderCandidate.setQuantity(orderCandidate.getQuantity() - order.getQuantity());
        } else {
            executedQuantity = orderCandidate.getQuantity();
            orderBookIterator.remove(); // we sold all quantities available with the sam price
        }
        System.out.println("[SERVER] BUY FILLED at $" + orderCandidate.getPrice() + " for " + executedQuantity + " units");
        return createFilledReport(order.getQuantity(), orderCandidate.getPrice(), executedQuantity, order.getAccountId());
    }
}