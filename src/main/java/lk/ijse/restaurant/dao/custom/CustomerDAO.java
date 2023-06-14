package lk.ijse.restaurant.dao.custom;

import lk.ijse.restaurant.dao.CrudDAO;
import lk.ijse.restaurant.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {

    List<String> loadCustomerIds() throws SQLException;
}
