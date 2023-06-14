package lk.ijse.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SupplierDTO {
    private String id;
    private String name;
    private String contact;
    private String address;
    private List<Supplier_DetailsDTO> supplier_DetailsDTOList;

    public SupplierDTO(String id, String name, String contact, String address) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.address = address;
    }
}
