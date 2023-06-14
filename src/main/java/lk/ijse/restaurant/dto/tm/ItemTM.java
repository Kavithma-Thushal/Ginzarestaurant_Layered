package lk.ijse.restaurant.dto.tm;

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
