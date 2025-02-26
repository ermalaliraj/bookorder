package com.ea.matching;

import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;
import com.ea.model.ReportStatus;
import com.ea.model.ReportType;

import java.util.Iterator;

import static com.ea.model.ReportFactory.createRejectReport;

public class MatchingConditionDefault implements MatchingCondition {
    @Override
    public Report process(Iterator<Order> orderBookIterator, OrderBook orderBook, Order orderCandidate, Order order) {
        System.out.println("[SERVER] Cannot match! Default Condition. Order: " + order);
        return createRejectReport(order.getQuantity(), order.getAccountId());
    }
}