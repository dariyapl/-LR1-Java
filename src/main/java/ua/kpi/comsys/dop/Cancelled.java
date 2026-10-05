package ua.kpi.comsys.dop;

public record Cancelled(String reason) implements OrderStatus {
    public Cancelled {
        //причина скасування обов'язкова
        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException("Reason cannot be null or blank");
        }
    }
}