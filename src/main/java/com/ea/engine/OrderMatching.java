package com.ea.engine;

import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;

public interface OrderMatching {
    Report match(OrderBook orderBook, Order order);
}
