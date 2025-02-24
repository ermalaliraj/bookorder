package com.ea.client;

import com.ea.coder.OrderEncoder;
import com.ea.coder.ReportDecoder;
import com.ea.model.Order;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class Client {
    public void sendOrder(String host, int port, Order order) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(
                                    new StringEncoder(),   // Converts outgoing String → bytes
                                    new StringDecoder(),   // Converts incoming bytes → String
                                    new OrderEncoder(),    // Converts Order object → JSON String before sending
                                    new ReportDecoder(),   // Converts received JSON String → Report object
                                    new ClientHandler(group)    // Handles the processed Report response
                            );
                        }
                    });
            Channel channel = bootstrap.connect(host, port).sync().channel();
            channel.writeAndFlush(order);
            channel.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}