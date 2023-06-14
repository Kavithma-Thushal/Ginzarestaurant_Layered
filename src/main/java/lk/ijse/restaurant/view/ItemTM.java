package lk.ijse.restaurant.view;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemTM {
    private String code;
    private String description;
    private Double unitprice;
    private Integer qtyonhand;
}
