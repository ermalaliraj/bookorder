package com.ea.matching;

import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;
import com.ea.model.ReportStatus;
import com.ea.model.ReportType;

public class MatchingConditionEmptyOrder implements MatchingCondition {
    @Override
    public Report process(OrderBook orderBook, Order orderCandidate, Order order) {
        if (orderCandidate == null) {
            System.out.println("[SERVER] Cannot match! MatchingConditionEmptyOrder. orderCandidate: null, order: " + order);
            return new Report(ReportType.exe_report,
                    orderBook.getBuyOrders().size(),
                    null,
                    null,
                    order.getAccountId(),
                    ReportStatus.REJECTED);
        }
        return null;
    }
}