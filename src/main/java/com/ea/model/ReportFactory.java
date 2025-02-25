package com.ea.model;

public class ReportFactory {
    public static Report createFilledReport(int ordersSize, int executedPrice, int executedQuantity, String accountId) {
        return new Report(ReportType.exe_report,
                ordersSize,
                executedPrice,
                executedQuantity,
                accountId,
                ReportStatus.FILLED);
    }

    public static Report createRejectReport(int ordersSize, String accountId) {
        return new Report(ReportType.exe_report,
                ordersSize,
                null,
                null,
                accountId,
                ReportStatus.REJECTED);
    }
}