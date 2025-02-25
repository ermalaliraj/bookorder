package com.ea.matching;

import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;

public interface MatchingCondition {
    Report process(OrderBook orderBook, Order orderCandidate, Order order);
}