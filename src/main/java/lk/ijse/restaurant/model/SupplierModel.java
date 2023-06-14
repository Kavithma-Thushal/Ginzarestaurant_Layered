package lk.ijse.restaurant.model;

import lk.ijse.restaurant.dto.SupplierDTO;
import lk.ijse.restaurant.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {

    public static int save(SupplierDTO supplierDTO) throws SQLException {

        String sql = "INSERT INTO suppliers VALUES (?,?,?,?)";

        return CrudUtil.execute(
                sql,
                supplierDTO.getId(),
                supplierDTO.getName(),
                supplierDTO.getContact(),
                supplierDTO.getAddress()
        );
    }

    public static SupplierDTO search(String id) throws SQLException {

        String sql = "SELECT * FROM suppliers WHERE id=?";

        ResultSet resultSet = CrudUtil.execute(sql, id);

        if (resultSet.next()) {
            return new SupplierDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return null;
    }

    public static int update(SupplierDTO supplierDTO) throws SQLException {

        String sql = "UPDATE suppliers SET name=? , contact=? , address=? WHERE id=?";

        return CrudUtil.execute(
                sql,
                supplierDTO.getName(),
                supplierDTO.getContact(),
                supplierDTO.getAddress(),
                supplierDTO.getId()
        );
    }

    public static int delete(String id) throws SQLException {
        String sql = "DELETE FROM suppliers WHERE id=?";
        return CrudUtil.execute(sql, id);
    }

    public static List<SupplierDTO> getAll() throws SQLException {

        List<SupplierDTO> supplierDTOList = new ArrayList<>();
        String sql = "SELECT * FROM suppliers";
        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()) {
            SupplierDTO supplierDTO = new SupplierDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            supplierDTOList.add(supplierDTO);
        }
        return supplierDTOList;
    }
}
