package lk.ijse.restaurant.bo.custom;

import lk.ijse.restaurant.bo.SuperBO;
import lk.ijse.restaurant.dto.ItemDTO;
import lk.ijse.restaurant.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBO extends SuperBO {

    List<SupplierDTO> loadAllSuppliers() throws SQLException;

    String generateNextSupplierId() throws SQLException;

    List<String> loadItemCodes() throws SQLException;

    ItemDTO searchByItemCode(String itemCode) throws SQLException;

    boolean addSupplier(SupplierDTO supplierDTO);
}
