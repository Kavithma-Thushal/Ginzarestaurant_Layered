package lk.ijse.restaurant.dao.custom.impl;

import lk.ijse.restaurant.dao.custom.OrdersDAO;
import lk.ijse.restaurant.dao.custom.impl.util.SQLUtil;
import lk.ijse.restaurant.entity.Orders;
import lk.ijse.restaurant.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql = "SELECT id FROM Orders ORDER BY id DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("Or0");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "Or0" + id;
        }
        return "Or01";
    }
}
