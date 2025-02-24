package com.ea.client;

import com.ea.model.Report;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<Report> {

    private final EventLoopGroup group;

    public ClientHandler(EventLoopGroup group) {
        this.group = group;
    }
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Report report) {
        System.out.println("[CLIENT] report from server: " + report);
        ctx.channel().close().addListener(future -> group.shutdownGracefully());
    }
}
