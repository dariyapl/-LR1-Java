package ua.kpi.comsys.dop;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class OrderProcessorTest {
    private final OrderProcessor processor = new OrderProcessor();

    // перевіряємо всі статуси
    @Test
    void shouldProcessPending() {
        String result = processor.process(new Pending());

        assertEquals("""
                Order Status: PENDING
                Details: Order is currently pending processing.""", result);
    }
    @Test
    void shouldProcessPaid() {
        String result = processor.process(
                new Paid("PAY-123", new BigDecimal("150.50"))
        );

        assertEquals("""
                Order Status: PAID
                Details: Payment ID: PAY-123, Amount: $150.50""", result);
    }
    @Test
    void shouldProcessShipped() {
        String result = processor.process(
                new Shipped(
                        "TRACK-456",
                        LocalDate.of(2026, 10, 5)
                )
        );

        assertEquals("""
                Order Status: SHIPPED
                Details: Tracking Code: TRACK-456, Dispatch Date: 2026-10-05""", result);
    }

    @Test
    void shouldProcessCancelled() {
        String result = processor.process(
                new Cancelled("Customer request")
        );

        assertEquals("""
                Order Status: CANCELLED
                Details: Reason: Customer request""", result);
    }

    // окремо перевіряємо null
    @Test
    void shouldThrowExceptionWhenStatusIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> processor.process(null)
        );
        assertEquals(
                "Status cannot be null",
                exception.getMessage()
        );
    }
}