package lk.ijse.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Delivery {
    private String code;
    private String employeeId;
    private String customerId;
    private String orderId;
    private String details;
    private String location;
}
