package com.ea.engine;

import com.ea.model.OrderType;

import java.util.HashMap;
import java.util.Map;

public class OrderMatchingFactory {
    private static final Map<OrderType, OrderMatching> matchersMap = new HashMap<>();

    static {
        matchersMap.put(OrderType.BUY, new OrderMatchingBuy());
        matchersMap.put(OrderType.SELL, new OrderMatchingSell());
    }

    public static OrderMatching getMatcher(OrderType orderType) {
        return matchersMap.get(orderType);
    }
}
