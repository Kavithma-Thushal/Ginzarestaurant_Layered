package lk.ijse.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Orders {
    private String orderId;
    private String customerId;
}
