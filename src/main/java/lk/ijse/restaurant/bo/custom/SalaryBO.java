package lk.ijse.restaurant.bo.custom;

import lk.ijse.restaurant.bo.SuperBO;
import lk.ijse.restaurant.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.List;

public interface SalaryBO extends SuperBO {

    List<SalaryDTO> loadAllSalary() throws SQLException;

    int saveSalary(SalaryDTO s) throws SQLException;

    SalaryDTO searchSalary(String id) throws SQLException;

    int updateSalary(SalaryDTO salaryDTO) throws SQLException;

    int deleteSalary(String id) throws SQLException;
}
