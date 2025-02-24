package com.ea.coder;

import com.ea.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class OrderEncoder extends MessageToMessageEncoder<Order> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void encode(ChannelHandlerContext ctx, Order order, List<Object> out) throws Exception {
        String json = objectMapper.writeValueAsString(order);
        out.add(json);
    }
}