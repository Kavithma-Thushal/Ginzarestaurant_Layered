package lk.ijse.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Supplier_DetailsDTO {
    private String supplierId;
    private String itemCode;
    private Integer qty;
    private LocalDate date;
}
