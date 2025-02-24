package com.ea.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Order {
    private OrderType type;
    private int quantity;
    private int price;
    private String accountId;

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

    public OrderType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public String getAccountId() {
        return accountId;
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