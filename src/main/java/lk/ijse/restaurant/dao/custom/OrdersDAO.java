package lk.ijse.restaurant.dao.custom;

import lk.ijse.restaurant.dao.CrudDAO;
import lk.ijse.restaurant.entity.Orders;

import java.sql.SQLException;

public interface OrdersDAO extends CrudDAO<Orders> {
    String generateNextId() throws SQLException;
}
