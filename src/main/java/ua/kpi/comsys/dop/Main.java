package ua.kpi.comsys.dop;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        OrderProcessor processor = new OrderProcessor();
        // приклади для перевірки
        System.out.println(processor.process(new Pending()));

        System.out.println(processor.process(
                new Paid("PAY-123", new BigDecimal("150.50"))
        ));
        System.out.println(processor.process(
                new Shipped("TRACK-456", LocalDate.of(2026, 10, 5))
        ));

        System.out.println(processor.process(
                new Cancelled("Customer request")
        ));
    }
}