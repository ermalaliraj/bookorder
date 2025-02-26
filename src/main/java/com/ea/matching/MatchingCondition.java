package com.ea.matching;

import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;

import java.util.Iterator;

public interface MatchingCondition {
    Report process(Iterator<Order> orderBookIterator, OrderBook orderBook, Order orderCandidate, Order order);
}