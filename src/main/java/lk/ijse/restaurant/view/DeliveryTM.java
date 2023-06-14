package lk.ijse.restaurant.view;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryTM {
    private String code;
    private String employeeid;
    private String customerid;
    private String orderid;
    private String details;
    private String location;
}
