package lk.ijse.restaurant.dao.custom;

import lk.ijse.restaurant.dao.CrudDAO;
import lk.ijse.restaurant.entity.Item;
import lk.ijse.restaurant.entity.Order_Details;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item> {

    List<String> loadItemCodes() throws SQLException;

    int updateOrderQty(Order_Details o) throws SQLException;
}
