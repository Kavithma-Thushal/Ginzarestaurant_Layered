package lk.ijse.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    private String id;
    private String name;
    private String contact;
    private String jobRole;
    private String username;
    private String password;
}
