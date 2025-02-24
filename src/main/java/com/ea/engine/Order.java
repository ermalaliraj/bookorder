package com.ea.engine;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Order {
    OrderType type;
    int quantity;
    int price;
    String accountId;

    @JsonCreator
    public Order(
            @JsonProperty("type") OrderType type,
            @JsonProperty("quantity") int quantity,
            @JsonProperty("price") int price,
            @JsonProperty("accountId") String accountId) {
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "type=" + type +
                ", quantity=" + quantity +
                ", price=" + price +
                ", accountId=" + accountId +
                "}";
    }
}