package com.ea.matching;

import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;
import com.ea.model.ReportStatus;
import com.ea.model.ReportType;

public class SellMatchingConditionPrice implements MatchingCondition {
    @Override
    public Report process(OrderBook orderBook, Order orderCandidate, Order order) {
        if (orderCandidate.getPrice() < order.getPrice()) {
            System.out.println("[SERVER] Cannot match! Best BID " + orderCandidate.getPrice() + " is lower than " + order.getPrice());
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