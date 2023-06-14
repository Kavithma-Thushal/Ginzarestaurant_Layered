package lk.ijse.restaurant.dao.custom.impl;

import lk.ijse.restaurant.dao.custom.OrderDetailsDAO;
import lk.ijse.restaurant.dao.custom.impl.util.SQLUtil;
import lk.ijse.restaurant.entity.Order_Details;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public List<Order_Details> loadAll() throws SQLException {
        return null;
    }

    @Override
    public int save(Order_Details entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO order_details VALUES(?, ?, ?, ?,?)", entity.getOrderId(), entity.getItemCode(), entity.getQty(), entity.getDate());
    }

    @Override
    public Order_Details search(String id) throws SQLException {
        return null;
    }

    @Override
    public int update(Order_Details entity) throws SQLException {
        return 0;
    }

    @Override
    public int delete(String id) throws SQLException {
        return 0;
    }

    @Override
    public String generateNextId() throws SQLException {
        return null;
    }
}
