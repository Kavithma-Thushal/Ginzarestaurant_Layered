package lk.ijse.restaurant.bo.custom;

import lk.ijse.restaurant.bo.SuperBO;
import lk.ijse.restaurant.dto.EmployeeDTO;
import lk.ijse.restaurant.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.List;

public interface SalaryBO extends SuperBO {

    int saveSalary(SalaryDTO s) throws SQLException;

    List<SalaryDTO> loadAllSalary() throws SQLException;

    String generateNextSalaryCode() throws SQLException;

    List<String> loadEmployeeIds() throws SQLException;

    EmployeeDTO searchByEmployeeId(String employeeId) throws SQLException;
}
