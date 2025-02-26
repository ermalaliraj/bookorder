package com.ea.engine;

import com.ea.matching.MatchingCondition;
import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;

import java.util.Iterator;
import java.util.List;

public interface OrderMatching {

    Report match(OrderBook orderBook, Order order);

    default Report processConditions(List<MatchingCondition> conditions, Iterator<Order> orderBookIterator, OrderBook orderBook, Order orderCandidate, Order order) {
        for (MatchingCondition condition : conditions) { // Iterate over all conditions and return the first valid Report
            Report processResult = condition.process(orderBookIterator, orderBook, orderCandidate, order);
            if (processResult != null) {
                return processResult;
            }
        }
        return null;
    }
}
