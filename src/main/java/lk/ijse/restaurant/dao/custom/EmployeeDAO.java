package lk.ijse.restaurant.dao.custom;

import lk.ijse.restaurant.dao.CrudDAO;
import lk.ijse.restaurant.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee> {

    List<String> loadEmployeeIds() throws SQLException;

    List<String> loadEmployeeIdsToRepair() throws SQLException;
}
