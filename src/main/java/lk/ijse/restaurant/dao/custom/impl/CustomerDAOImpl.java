package lk.ijse.restaurant.dao.custom.impl;

import lk.ijse.restaurant.dao.custom.CustomerDAO;
import lk.ijse.restaurant.dao.custom.impl.util.SQLUtil;
import lk.ijse.restaurant.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public List<Customer> loadAll() throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");
        while (rst.next()) {
            Customer customer = new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6));
            customerList.add(customer);
        }
        return customerList;
    }

    @Override
    public int save(Customer c) throws SQLException {
        return SQLUtil.execute("INSERT INTO customer VALUES (?,?,?,?,?,?)", c.getId(), c.getName(), c.getNic(), c.getEmail(), c.getContact(), c.getAddress());
    }

    @Override
    public Customer search(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer WHERE id=?", id);
        if (rst.next()) {
            return new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6));
        }
        return null;
    }

    @Override
    public int update(Customer c) throws SQLException {
        return SQLUtil.execute("UPDATE customer SET name=? , nic=? , email=? , contact=? , address=? WHERE id=?", c.getName(), c.getNic(), c.getEmail(), c.getContact(), c.getAddress(), c.getId());
    }

    @Override
    public int delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM customer WHERE id=?", id);
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM customer ORDER BY id DESC LIMIT 1");
        if (rst.next()) {
            return splitCustomerId(rst.getString(1));
        }
        return splitCustomerId(null);
    }

    private String splitCustomerId(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("C");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "C" + String.format("%02d", id);
        }
        return "C01";
    }

    @Override
    public List<String> loadCustomerIds() throws SQLException {
        List<String> arrayList = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT id FROM customer ORDER BY id ASC");
        while (rst.next()) {
            arrayList.add(rst.getString(1));
        }
        return arrayList;
    }
}
