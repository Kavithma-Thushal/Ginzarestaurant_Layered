package lk.ijse.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Employee {
    private String id;
    private String name;
    private String contact;
    private String jobrole;
    private String username;
    private String password;
}
