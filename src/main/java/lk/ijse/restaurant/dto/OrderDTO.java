package lk.ijse.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {
    private String id;
    private String customerId;
    private List<Order_DetailsDTO> order_detailsDTOList;
}
