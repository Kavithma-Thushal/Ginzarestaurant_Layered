package lk.ijse.restaurant.model;

import lk.ijse.restaurant.dto.SalaryDTO;
import lk.ijse.restaurant.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryModel {

    public static int save(SalaryDTO salaryDTO) throws SQLException {
        String sql = "INSERT INTO salary VALUES (?,?,?,?)";
        return CrudUtil.execute(
                sql,
                salaryDTO.getCode(),
                salaryDTO.getEmployeeid(),
                salaryDTO.getAmount(),
                salaryDTO.getDatetime()
        );
    }

    public static SalaryDTO search(String code) throws SQLException {

        String sql = "SELECT * FROM salary WHERE code=?";
        ResultSet resultSet = CrudUtil.execute(sql, code);

        if (resultSet.next()) {
            return new SalaryDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4)
            );
        }
        return null;
    }

    public static int update(SalaryDTO salaryDTO) throws SQLException {

        String sql = "UPDATE salary SET employeeid=? , amount=? , datetime=? WHERE code=?";
        return CrudUtil.execute(
                sql,
                salaryDTO.getEmployeeid(),
                salaryDTO.getAmount(),
                salaryDTO.getDatetime(),
                salaryDTO.getCode()
        );
    }

    public static int delete(String code) throws SQLException {
        String sql = "DELETE FROM salary WHERE code=?";
        return CrudUtil.execute(sql, code);
    }

    public static List<SalaryDTO> getAll() throws SQLException {

        List<SalaryDTO> salaryDTOList = new ArrayList<>();
        String sql = "SELECT * FROM salary";
        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()) {
            SalaryDTO salaryDTO = new SalaryDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4)
            );
            salaryDTOList.add(salaryDTO);
        }
        return salaryDTOList;
    }
}
