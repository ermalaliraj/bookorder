package com.ea.engine;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Report {
    private ReportType type;
    private Integer initialQuantity;
    private Integer executedPrice;
    private Integer executedQuantity;
    private String accountId;
    private ReportStatus status;

    @JsonCreator
    public Report(
            @JsonProperty("type") ReportType type,
            @JsonProperty("initialQuantity") Integer initialQuantity,
            @JsonProperty("executedPrice") Integer executedPrice,
            @JsonProperty("executedQuantity") Integer executedQuantity,
            @JsonProperty("accountId") String accountId,
            @JsonProperty("status") ReportStatus status) {
        this.type = type;
        this.initialQuantity = initialQuantity;
        this.executedPrice = executedPrice;
        this.executedQuantity = executedQuantity;
        this.accountId = accountId;
        this.status = status;
    }

    public String toString() {
        return "Report{" +
                "type=" + type +
                ", initialQuantity=" + initialQuantity +
                ", executedPrice=" + executedPrice +
                ", executedQuantity=" + executedQuantity +
                ", accountId=" + accountId +
                ", status=" + status +
                '}';
    }
}