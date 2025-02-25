package com.ea.matching;

import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;

import static com.ea.model.ReportFactory.createRejectReport;

public class SellMatchingConditionPrice implements MatchingCondition {
    @Override
    public Report process(OrderBook orderBook, Order orderCandidate, Order order) {
        if (orderCandidate.getPrice() < order.getPrice()) {
            System.out.println("[SERVER] Cannot match! Best BID " + orderCandidate.getPrice() + " is lower than " + order.getPrice());
            return createRejectReport(orderBook.getBuyOrders().size(), order.getAccountId());
        }
        return null;
    }
}