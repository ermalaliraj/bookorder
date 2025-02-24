package com.ea;

import com.ea.model.Report;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<Report> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Report report) {
        System.out.println("[CLIENT] report from server: " + report);
    }
}
