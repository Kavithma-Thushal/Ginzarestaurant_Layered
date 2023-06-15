package lk.ijse.restaurant.dao.custom.impl;

import lk.ijse.restaurant.dao.custom.SupplierDetailDAO;
import lk.ijse.restaurant.dao.custom.impl.util.SQLUtil;
import lk.ijse.restaurant.entity.Supplier_Details;

import java.sql.SQLException;
import java.util.List;

public class SupplierDetailDAOImpl implements SupplierDetailDAO {

    @Override
    public List<Supplier_Details> loadAll() throws SQLException {
        return null;
    }

    @Override
    public int save(Supplier_Details s) throws SQLException {
        return SQLUtil.execute("INSERT INTO supplier_details VALUES(?, ?, ?, ?)", s.getSupplierId(), s.getItemCode(), s.getQty());
    }

    @Override
    public Supplier_Details search(String id) throws SQLException {
        return null;
    }

    @Override
    public int update(Supplier_Details entity) throws SQLException {
        return 0;
    }

    @Override
    public int delete(String id) throws SQLException {
        return 0;
    }
}
