package lk.ijse.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalaryDTO {
    private String code;
    private String employeeid;
    private Double amount;
    private String datetime;
}
