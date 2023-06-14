package lk.ijse.restaurant.dao.custom;

import lk.ijse.restaurant.dao.CrudDAO;
import lk.ijse.restaurant.entity.Orders;

import java.sql.SQLException;
import java.util.List;

public interface OrdersDAO extends CrudDAO<Orders> {
    List<String> loadOrderIds() throws SQLException;
}
