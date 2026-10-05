package ua.kpi.comsys.dop;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class OrderStatusTest {
    // перевіряємо коректні дані
    @Test
    void shouldCreateValidPaid() {
        Paid paid = new Paid("PAY-123", new BigDecimal("100.00"));

        assertEquals("PAY-123", paid.paymentID());
        assertEquals(new BigDecimal("100.00"), paid.amount());
    }

    // неправильний id платежу
    @Test
    void shouldThrowExceptionWhenPaymentIdIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Paid(null, new BigDecimal("100.00"))
        );
    }
    @Test
    void shouldThrowExceptionWhenPaymentIdIsBlank() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Paid("   ", new BigDecimal("100.00"))
        );
    }

    // перевірка суми
    @Test
    void shouldThrowExceptionWhenAmountIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Paid("PAY-123", null)
        );
    }

    @Test
    void shouldThrowExceptionWhenAmountIsZero() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Paid("PAY-123", BigDecimal.ZERO)
        );
    }
    @Test
    void shouldThrowExceptionWhenAmountIsNegative() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Paid("PAY-123", new BigDecimal("-10"))
        );
    }

    //перевіряємо shipped
    @Test
    void shouldCreateValidShipped() {
        LocalDate date = LocalDate.of(2026, 10, 5);

        Shipped shipped = new Shipped("TRACK-123", date);

        assertEquals("TRACK-123", shipped.trackingCode());
        assertEquals(date, shipped.dispatchDate());
    }

    @Test
    void shouldThrowExceptionWhenTrackingCodeIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Shipped(null, LocalDate.now())
        );
    }
    @Test
    void shouldThrowExceptionWhenTrackingCodeIsBlank() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Shipped("   ", LocalDate.now())
        );
    }

    @Test
    void shouldThrowExceptionWhenDispatchDateIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Shipped("TRACK-123", null)
        );
    }

    // перевірка cancelled
    @Test
    void shouldCreateValidCancelled() {
        Cancelled cancelled = new Cancelled("Customer request");

        assertEquals("Customer request", cancelled.reason());
    }
    @Test
    void shouldThrowExceptionWhenReasonIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Cancelled(null)
        );
    }

    @Test
    void shouldThrowExceptionWhenReasonIsBlank() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Cancelled("   ")
        );
    }

    // у pending немає додаткових полів
    @Test
    void shouldCreatePending() {
        Pending pending = new Pending();

        assertNotNull(pending);
    }
}