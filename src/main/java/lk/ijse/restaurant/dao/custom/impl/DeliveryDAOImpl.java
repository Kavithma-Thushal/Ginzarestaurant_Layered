package lk.ijse.restaurant.dao.custom.impl;

import lk.ijse.restaurant.dao.custom.DeliveryDAO;
import lk.ijse.restaurant.dao.custom.impl.util.SQLUtil;
import lk.ijse.restaurant.entity.Delivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDAOImpl implements DeliveryDAO {

    @Override
    public List<Delivery> loadAll() throws SQLException {
        List<Delivery> deliveryList=new ArrayList<>();
        ResultSet rst= SQLUtil.execute("SELECT * FROM delivery");
        while (rst.next()){
            Delivery delivery=new Delivery(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6));
            deliveryList.add(delivery);
        }
        return deliveryList;
    }

    @Override
    public int save(Delivery d) throws SQLException {
        return SQLUtil.execute("INSERT INTO delivery VALUES(?, ?, ?, ?, ?, ?)",d.getCode(),d.getCustomerId(),d.getEmployeeId(),d.getOrderId(),d.getDetails(),d.getLocation());
    }

    @Override
    public Delivery search(String code) throws SQLException {
        ResultSet rst=SQLUtil.execute("SELECT * FROM delivery WHERE code=?",code);
        if(rst.next()){
            return new Delivery(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6));
        }
        return null;
    }

    @Override
    public int update(Delivery d) throws SQLException {
        return SQLUtil.execute("UPDATE delivery SET employeeid=? , customerid=? , orderid=? , details=? , location=?  WHERE code=?",d.getCode(),d.getEmployeeId(),d.getCustomerId(),d.getOrderId(),d.getDetails(),d.getLocation());
    }

    @Override
    public int delete(String code) throws SQLException {
        return SQLUtil.execute("DELETE FROM delivery WHERE code=?",code);
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet rst=SQLUtil.execute("SELECT code FROM delivery ORDER BY code DESC LIMIT 1");
        if (rst.next()) {
            return splitDeliveryCode(rst.getString(1));
        }
        return splitDeliveryCode(null);
    }

    private static String splitDeliveryCode(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("D");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "D" + String.format("%02d", id);
        }
        return "D01";
    }
}
