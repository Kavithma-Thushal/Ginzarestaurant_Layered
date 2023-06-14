package lk.ijse.restaurant.dao.custom.impl;

import lk.ijse.restaurant.dao.custom.EmployeeDAO;
import lk.ijse.restaurant.dao.custom.impl.util.SQLUtil;
import lk.ijse.restaurant.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public List<Employee> loadAll() throws SQLException {
        List<Employee> employeeList = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee");
        while (rst.next()) {
            Employee employee = new Employee(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6));
            employeeList.add(employee);
        }
        return employeeList;
    }

    @Override
    public int save(Employee e) throws SQLException {
        return SQLUtil.execute("INSERT INTO employee VALUES (?,?,?,?,?,?)", e.getId(), e.getName(), e.getContact(), e.getJobRole(), e.getUsername(), e.getPassword());
    }

    @Override
    public Employee search(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee WHERE id=?", id);
        if (rst.next()) {
            return new Employee(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6));
        }
        return null;
    }

    @Override
    public int update(Employee e) throws SQLException {
        return SQLUtil.execute("UPDATE employee SET name=? , contact=? , jobRole=? , username=? , password=? WHERE id=?", e.getName(), e.getContact(), e.getJobRole(), e.getUsername(), e.getPassword(), e.getId());
    }

    @Override
    public int delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM employee WHERE id=?", id);
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM employee ORDER BY id DESC LIMIT 1");
        if (rst.next()) {
            return splitEmployeeId(rst.getString(1));
        }
        return splitEmployeeId(null);
    }

    private String splitEmployeeId(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("E");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "E" + String.format("%02d", id);
        }
        return "E01";
    }

    @Override
    public List<String> loadEmployeeIds() throws SQLException {
        List<String> arrayList = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT id FROM employee ORDER BY id ASC");
        while (rst.next()) {
            arrayList.add(rst.getString(1));
        }
        return arrayList;
    }

    @Override
    public List<String> loadEmployeeIdsToRepair() throws SQLException {
        List<String> arrayList = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT id FROM employee WHERE   jobRole IN ('Technician','technician') ORDER BY id ASC");
        while (rst.next()) {
            arrayList.add(rst.getString(1));
        }
        return arrayList;
    }
}
