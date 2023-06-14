package lk.ijse.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Delivery {
    private String code;
    private String customerId;
    private String employeeId;
    private String orderId;
    private String location;
    private LocalDate date;
}
