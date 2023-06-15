package lk.ijse.restaurant.bo.custom;

import lk.ijse.restaurant.bo.SuperBO;
import lk.ijse.restaurant.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBO extends SuperBO {

    List<SupplierDTO> loadAllSuppliers() throws SQLException;

    int saveSupplier(SupplierDTO supplierDTO) throws SQLException;

    SupplierDTO searchSupplier(String id) throws SQLException;

    int updateSupplier(SupplierDTO supplierDTO) throws SQLException;

    int deleteSupplier(String id) throws SQLException;
}
