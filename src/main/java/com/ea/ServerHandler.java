package com.ea;

import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<String> {
    private final OrderBook orderBook;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ServerHandler(OrderBook orderBook) {
        this.orderBook = orderBook;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Order order = objectMapper.readValue(msg, Order.class);
        Report response = orderBook.processMarketOrder(order);
        System.out.println("[SERVER] Order processed: " + response);
        ctx.writeAndFlush(response);
    }
}
