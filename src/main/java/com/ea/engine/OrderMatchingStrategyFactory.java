package com.ea.engine;

import com.ea.model.OrderType;

import java.util.HashMap;
import java.util.Map;

public class OrderMatchingStrategyFactory {
    private static final Map<OrderType, OrderMatchingStrategy> strategyMap = new HashMap<>();

    static {
        strategyMap.put(OrderType.BUY, new OrderMatchingStrategyBuy());
        strategyMap.put(OrderType.SELL, new OrderMatchingStrategySell());
    }

    public static OrderMatchingStrategy getStrategy(OrderType orderType) {
        return strategyMap.get(orderType);
    }
}
