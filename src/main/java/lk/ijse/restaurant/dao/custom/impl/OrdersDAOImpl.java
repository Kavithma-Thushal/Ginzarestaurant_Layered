package lk.ijse.restaurant.dao.custom.impl;

import lk.ijse.restaurant.dao.custom.OrdersDAO;
import lk.ijse.restaurant.dao.custom.impl.util.SQLUtil;
import lk.ijse.restaurant.entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {

    @Override
    public List<Orders> loadAll() throws SQLException {
        return null;
    }

    @Override
    public int save(Orders entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO Orders(id, customerId) VALUES(?, ?)", entity.getId(), entity.getCustomerId());
    }

    @Override
    public Orders search(String id) throws SQLException {
        return null;
    }

    @Override
    public int update(Orders entity) throws SQLException {
        return 0;
    }

    @Override
    public int delete(String id) throws SQLException {
        return 0;
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT orderId FROM Orders ORDER BY orderId DESC LIMIT 1");
        if (rst.next()) {
            return splitOrderId(rst.getString(1));
        }
        return splitOrderId(null);
    }

    private String splitOrderId(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("O");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "O" + String.format("%02d", id);
        }
        return "O01";
    }

    @Override
    public List<String> loadOrderIds() throws SQLException {
        List<String> arrayList = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT orderId FROM orders ORDER BY orderId ASC");
        while (rst.next()) {
            arrayList.add(rst.getString(1));
        }
        return arrayList;
    }
}
