package com.ea.engine;

import com.ea.matching.MatchingConditionDefault;
import com.ea.matching.MatchingCondition;
import com.ea.matching.SellMatchingConditionPrice;
import com.ea.matching.SellMatchingConditionQuantity;
import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;

import java.util.Iterator;
import java.util.List;

public class OrderMatchingSell implements OrderMatching {
    private final List<MatchingCondition> conditions = List.of(
            new SellMatchingConditionPrice(),
            new SellMatchingConditionQuantity()
    );

    @Override
    public Report match(OrderBook orderBook, Order order) {
        Iterator<Order> buyOrdersIterator = orderBook.getBuyOrders().iterator();
        while (buyOrdersIterator.hasNext()) {
            Order bestBuy = buyOrdersIterator.next();
            Report processResult = processConditions(conditions, buyOrdersIterator, orderBook, bestBuy, order);
            if (processResult != null) {
                return processResult;
            }
        }
        return new MatchingConditionDefault().process(null, orderBook, null, order);
    }
}
