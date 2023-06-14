package lk.ijse.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Salary {
    private String code;
    private String employeeId;
    private Double amount;
    private LocalDate date;
}
