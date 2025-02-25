
package com.ea.coder;

import com.ea.model.Report;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class ReportEncoder extends MessageToMessageEncoder<Report> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void encode(ChannelHandlerContext ctx, Report report, List<Object> out) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(report);
        out.add(json);
    }
}
