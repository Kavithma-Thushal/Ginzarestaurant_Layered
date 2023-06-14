package lk.ijse.restaurant.dao.custom.impl;

import lk.ijse.restaurant.dao.custom.SupplierDAO;
import lk.ijse.restaurant.dao.custom.impl.util.SQLUtil;
import lk.ijse.restaurant.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public List<Supplier> loadAll() throws SQLException {
        List<Supplier> supplierList = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier");
        while (rst.next()) {
            Supplier supplier = new Supplier(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4));
            supplierList.add(supplier);
        }
        return supplierList;
    }

    @Override
    public int save(Supplier s) throws SQLException {
        return SQLUtil.execute("INSERT INTO supplier VALUES(?,?,?,?)", s.getId(), s.getName(), s.getContact(), s.getAddress());
    }

    @Override
    public Supplier search(String id) throws SQLException {
        return null;
    }

    @Override
    public int update(Supplier entity) throws SQLException {
        return 0;
    }

    @Override
    public int delete(String id) throws SQLException {
        return 0;
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM supplier ORDER BY id DESC LIMIT 1");
        if (rst.next()) {
            return splitSupplierId(rst.getString(1));
        }
        return splitSupplierId(null);
    }

    private String splitSupplierId(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("S");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "S" + String.format("%02d", id);
        }
        return "S01";
    }
}
