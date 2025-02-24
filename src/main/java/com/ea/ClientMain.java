package com.ea;

import com.ea.client.Client;
import com.ea.engine.Order;
import com.ea.engine.OrderType;

public class ClientMain {
    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        client.sendOrder("localhost", 8080, new Order(OrderType.BUY, 50, 1009, "1233"));
    }
}
