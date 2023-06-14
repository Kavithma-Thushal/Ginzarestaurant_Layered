package lk.ijse.restaurant.model;

import lk.ijse.restaurant.dto.CustomerDTO;
import lk.ijse.restaurant.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {

    public static int save(CustomerDTO customerDTO) throws SQLException {

        String sql = "INSERT INTO customers VALUES (?,?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getNic(),
                customerDTO.getEmail(),
                customerDTO.getContact(),
                customerDTO.getAddress()
        );

    }

    public static CustomerDTO search(String id) throws SQLException {

        String sql = "SELECT * FROM customers WHERE id=?";

        ResultSet resultSet = CrudUtil.execute(sql, id);

        if (resultSet.next()) {
            return new CustomerDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }
        return null;
    }

    public static int update(CustomerDTO customerDTO) throws SQLException {

        String sql = "UPDATE customers SET name=? , nic=? , email=? , contact=? , address=? WHERE id=?";

        return CrudUtil.execute(
                sql,
                customerDTO.getName(),
                customerDTO.getNic(),
                customerDTO.getEmail(),
                customerDTO.getContact(),
                customerDTO.getAddress(),
                customerDTO.getId()
        );
    }

    public static int delete(String id) throws SQLException {
        String sql = "DELETE FROM customers WHERE id=?";
        return CrudUtil.execute(sql, id);
    }

    public static List<CustomerDTO> getAll() throws SQLException {

        List<CustomerDTO> customerDTOList = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()) {
            CustomerDTO customerDTO = new CustomerDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }

    public static List<String> loadIds() throws SQLException {
        String sql = "SELECT id FROM customers ORDER BY id ASC ";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String> data = new ArrayList<>();
        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static CustomerDTO searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Customers WHERE id = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);

        if (resultSet.next()) {
            return new CustomerDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }
        return null;
    }
}
