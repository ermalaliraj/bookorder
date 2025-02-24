
package com.ea.decoder;

import com.ea.model.Report;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class ReportEncoder extends MessageToMessageEncoder<Report> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void encode(ChannelHandlerContext ctx, Report report, List<Object> out) throws Exception {
        String json = objectMapper.writeValueAsString(report);
        out.add(json);
    }
}
