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
        ResultSet rst = SQLUtil.execute("SELECT * FROM employees");
        while (rst.next()) {
            Employee employee = new Employee(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6));
            employeeList.add(employee);
        }
        return employeeList;
    }

    @Override
    public int save(Employee e) throws SQLException {
        return SQLUtil.execute("INSERT INTO employees VALUES (?,?,?,?,?,?)", e.getId(), e.getName(), e.getContact(), e.getJobRole(), e.getUsername(), e.getPassword());
    }

    @Override
    public Employee search(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM employees WHERE id=?", id);
        if (rst.next()) {
            return new Employee(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6));
        }
        return null;
    }

    @Override
    public int update(Employee e) throws SQLException {
        return SQLUtil.execute("UPDATE employees SET name=? , contact=? , jobRole=? , username=? , password=? WHERE id=?", e.getName(), e.getContact(), e.getJobRole(), e.getUsername(), e.getPassword(), e.getId());
    }

    @Override
    public int delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM employees WHERE id=?", id);
    }
}
