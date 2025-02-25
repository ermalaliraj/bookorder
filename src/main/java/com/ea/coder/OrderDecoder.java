package com.ea.coder;

import com.ea.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class OrderDecoder extends MessageToMessageDecoder<String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void decode(ChannelHandlerContext ctx, String msg, List<Object> out) {
        try {
            Order order = objectMapper.readValue(msg, Order.class);
            out.add(order);
        } catch (Exception e) {
            System.err.println("[SERVER] Invalid order format received: " + msg);
        }
    }
}

