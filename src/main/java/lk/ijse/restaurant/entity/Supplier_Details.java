package lk.ijse.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Supplier_Details {
    private String supplierId;
    private String itemCode;
    private Integer qty;
    private LocalDate date;
}
