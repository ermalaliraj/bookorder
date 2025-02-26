package com.ea.engine;

import com.ea.matching.BuyMatchingConditionPrice;
import com.ea.matching.BuyMatchingConditionQuantity;
import com.ea.matching.MatchingCondition;
import com.ea.matching.MatchingConditionDefault;
import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;

import java.util.Iterator;
import java.util.List;

public class OrderMatchingBuy implements OrderMatching {
    private final List<MatchingCondition> conditions = List.of(
            new BuyMatchingConditionPrice(),
            new BuyMatchingConditionQuantity()
    );

    @Override
    public Report match(OrderBook orderBook, Order order) {
        Iterator<Order> sellOrdersIterator = orderBook.getSellOrders().iterator();
        while (sellOrdersIterator.hasNext()) {
            Order bestSeller = sellOrdersIterator.next();
            Report processResult = processConditions(conditions, sellOrdersIterator, orderBook, bestSeller, order);
            if (processResult != null) {
                return processResult;
            }
        }
        return new MatchingConditionDefault().process(null, orderBook, null, order);
    }

}
