package com.ea.matching;

import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;

import static com.ea.model.ReportFactory.createRejectReport;

public class BuyMatchingConditionPrice implements MatchingCondition {
    @Override
    public Report process(OrderBook orderBook, Order orderCandidate, Order order) {
        if (orderCandidate.getPrice() > order.getPrice()) {
            System.out.println("[SERVER] Cannot match! Best ASK " + orderCandidate.getPrice() + " is higher than " + order.getPrice());
            return createRejectReport(order.getQuantity(), order.getAccountId());
        }
        return null;
    }
}