package lk.ijse.restaurant.bo.custom;

import lk.ijse.restaurant.bo.SuperBO;
import lk.ijse.restaurant.dto.ItemDTO;

import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {

    List<ItemDTO> loadAllItems() throws SQLException;

    int saveItem(ItemDTO itemDTO) throws SQLException;

    ItemDTO searchItem(String itemCode) throws SQLException;

    int updateItem(ItemDTO itemDTO) throws SQLException;

    int deleteItem(String itemCode) throws SQLException;
}
