package com.ea;

import com.ea.decoder.OrderDecoder;
import com.ea.decoder.ReportEncoder;
import com.ea.model.OrderBook;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class Server {
    public final OrderBook orderBook = new OrderBook();
    {
        orderBook.addInitialOrders();
    }

    public void start(int port) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(
                                    new StringDecoder(),   // Converts incoming bytes → String
                                    new StringEncoder(),   // Converts outgoing String → bytes
                                    new OrderDecoder(),    // Converts received JSON String → Order object
                                    new ReportEncoder(),   // Converts outgoing Report object → JSON String
                                    new ServerHandler(orderBook) // Handles Order processing logic
                            );
                        }
                    });
            ChannelFuture future = bootstrap.bind(port).sync();
            System.out.println("Matching Engine Server started on port " + port);
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                new Server().start(8080);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(2000);
    }
}