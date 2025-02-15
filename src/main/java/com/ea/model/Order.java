package com.ea.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Order {
    public enum Type {
        BUY, SELL
    }

    Type type;
    int quantity;
    int price; // does this belongs here? Or should be in the entity we are selling, like Books?
    String accountId;

    @JsonCreator
    public Order(
            @JsonProperty("type") Type type,
            @JsonProperty("quantity") int quantity,
            @JsonProperty("price") int price,
            @JsonProperty("accountId") String accountId) {
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.accountId = accountId;
    }

    @JsonCreator
    public Order(
            @JsonProperty("type") Type type,
            @JsonProperty("quantity") int quantity,
            @JsonProperty("accountId") String accountId) {
        this.type = type;
        this.quantity = quantity;
        this.accountId = accountId;
    }
}