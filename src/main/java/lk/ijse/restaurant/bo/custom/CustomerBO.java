package lk.ijse.restaurant.bo.custom;

import lk.ijse.restaurant.bo.SuperBO;
import lk.ijse.restaurant.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {

    List<CustomerDTO> loadAllCustomers() throws SQLException;

    int saveCustomer(CustomerDTO customerDTO) throws SQLException;

    CustomerDTO searchCustomer(String id) throws SQLException;

    int updateCustomer(CustomerDTO customerDTO) throws SQLException;

    int deleteCustomer(String id) throws SQLException;
}
