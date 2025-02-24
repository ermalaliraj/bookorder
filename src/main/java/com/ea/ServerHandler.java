package com.ea;

import com.ea.model.Order;
import com.ea.model.OrderBook;
import com.ea.model.Report;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<Order> {
    private final OrderBook orderBook;

    public ServerHandler(OrderBook orderBook) {
        this.orderBook = orderBook;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Order order) {
        Report response = orderBook.processMarketOrder(order);
        System.out.println("[SERVER] processed order: " + response);
        ctx.writeAndFlush(response);
    }
}
