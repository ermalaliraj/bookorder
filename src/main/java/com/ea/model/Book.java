package com.ea.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Book {
    private String name;
    private int price;

    @JsonCreator
    public Book(
            @JsonProperty("name") String name,
            @JsonProperty("price") int price) {
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return name + " ($" + price + ")";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}