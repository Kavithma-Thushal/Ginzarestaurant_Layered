package lk.ijse.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryDTO {
    private String code;
    private String employeeid;
    private String customerid;
    private String orderid;
    private String details;
    private String location;
}
