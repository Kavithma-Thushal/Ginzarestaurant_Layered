package lk.ijse.restaurant.dao.custom.impl;

import lk.ijse.restaurant.dao.custom.ItemDAO;
import lk.ijse.restaurant.dao.custom.impl.util.SQLUtil;
import lk.ijse.restaurant.entity.Item;
import lk.ijse.restaurant.entity.Order_Details;
import lk.ijse.restaurant.entity.Supplier_Details;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public List<Item> loadAll() throws SQLException {
        List<Item> itemList = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM item");
        while (rst.next()) {
            Item item = new Item(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getInt(4));
            itemList.add(item);
        }
        return itemList;
    }

    @Override
    public int save(Item i) throws SQLException {
        return SQLUtil.execute("INSERT INTO item VALUES (?,?,?,?)", i.getCode(), i.getDescription(), i.getUnitPrice(), i.getQtyOnHand());
    }

    @Override
    public Item search(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM item WHERE code=?", id);
        if (rst.next()) {
            return new Item(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getInt(4));
        }
        return null;
    }

    @Override
    public int update(Item i) throws SQLException {
        return SQLUtil.execute("UPDATE item SET description=? , unitPrice=? , qtyOnHand=? WHERE code=?", i.getDescription(), i.getUnitPrice(), i.getQtyOnHand(), i.getCode());
    }

    @Override
    public int delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM item WHERE code=?", id);
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT code FROM item ORDER BY code DESC LIMIT 1");
        if (rst.next()) {
            return splitItemCode(rst.getString(1));
        }
        return splitItemCode(null);
    }

    private String splitItemCode(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("P");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "P" + String.format("%02d", id);
        }
        return "P01";
    }

    @Override
    public List<String> loadItemCodes() throws SQLException {
        List<String> arrayList = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT code FROM item  ORDER BY code ASC");
        while (rst.next()) {
            arrayList.add(rst.getString(1));
        }
        return arrayList;
    }

    @Override
    public int updateOrderQty(Order_Details o) throws SQLException {
        return SQLUtil.execute("UPDATE item SET qtyOnHand = (qtyOnHand - ?) WHERE code = ?", o.getQty(), o.getItemCode());
    }

    @Override
    public int updateSupplyQty(Supplier_Details s) throws SQLException {
        return SQLUtil.execute("UPDATE item SET qtyOnHand = (qtyOnHand + ?) WHERE code = ?", s.getQty(), s.getItemCode());
    }
}
