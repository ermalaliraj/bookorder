package com.ea.engine;

import com.ea.matching.BuyMatchingConditionPrice;
import com.ea.matching.BuyMatchingConditionQuantity;
import com.ea.matching.MatchingCondition;
import com.ea.matching.MatchingConditionDefault;
import com.ea.matching.MatchingConditionEmptyOrder;
import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;

import java.util.List;

public class OrderMatchingBuy implements OrderMatching {
    private final List<MatchingCondition> conditions = List.of(
            new MatchingConditionEmptyOrder(),
            new BuyMatchingConditionPrice(),
            new BuyMatchingConditionQuantity()
    );

    @Override
    public Report match(OrderBook orderBook, Order order) {
        Order bestBuy = orderBook.getSellOrders().peek(); // Get highest ASK price
        for (MatchingCondition condition : conditions) { // Iterate over all conditions and return the first valid Report
            Report result = condition.process(orderBook, bestBuy, order);
            if (result != null) {
                return result;
            }
        }
        return new MatchingConditionDefault().process(orderBook, bestBuy, order);
    }
}
