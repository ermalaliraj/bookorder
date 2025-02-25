package com.ea.engine;

import com.ea.model.OrderType;

import java.util.HashMap;
import java.util.Map;

public class OrderMatchingFactory {
    private static final Map<OrderType, OrderMatching> strategyMap = new HashMap<>();

    static {
        strategyMap.put(OrderType.BUY, new OrderMatchingBuy());
        strategyMap.put(OrderType.SELL, new OrderMatchingSell());
    }

    public static OrderMatching getMatcher(OrderType orderType) {
        return strategyMap.get(orderType);
    }
}
