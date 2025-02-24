package com.ea.decoder;

import com.ea.model.Report;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class ReportDecoder extends MessageToMessageDecoder<String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void decode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        Report report = objectMapper.readValue(msg, Report.class);
        out.add(report);
    }
}
