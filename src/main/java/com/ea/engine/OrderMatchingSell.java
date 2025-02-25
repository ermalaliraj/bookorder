package com.ea.engine;

import com.ea.matching.MatchingConditionDefault;
import com.ea.matching.MatchingConditionEmptyOrder;
import com.ea.matching.MatchingCondition;
import com.ea.matching.SellMatchingConditionPrice;
import com.ea.matching.SellMatchingConditionQuantity;
import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;

import java.util.List;

public class OrderMatchingSell implements OrderMatching {
    private final List<MatchingCondition> conditions = List.of(
            new MatchingConditionEmptyOrder(),
            new SellMatchingConditionPrice(),
            new SellMatchingConditionQuantity()
    );

    @Override
    public Report match(OrderBook orderBook, Order order) {
        Order bestBuy = orderBook.getBuyOrders().peek(); // Get highest BID price
        for (MatchingCondition condition : conditions) { // Iterate over all conditions and return the first valid Report
            Report result = condition.process(orderBook, bestBuy, order);
            if (result != null) {
                return result;
            }
        }
        return new MatchingConditionDefault().process(orderBook, bestBuy, order);
    }
}
