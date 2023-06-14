package lk.ijse.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Order_Details {
    private String orderId;
    private String itemCode;
    private Integer qty;
    private Double total;
    private LocalDate date;
}
