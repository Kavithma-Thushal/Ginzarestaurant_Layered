package lk.ijse.restaurant.model;

import lk.ijse.restaurant.dto.DeliveryDTO;
import lk.ijse.restaurant.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryModel {

    public static int save(DeliveryDTO deliveryDTO) throws SQLException {
        String sql = "INSERT INTO delivery VALUES (?,?,?,?,?,?)";
        return CrudUtil.execute(
                sql,
                deliveryDTO.getCode(),
                deliveryDTO.getEmployeeid(),
                deliveryDTO.getCustomerid(),
                deliveryDTO.getOrderid(),
                deliveryDTO.getDetails(),
                deliveryDTO.getLocation()
        );
    }

    public static DeliveryDTO search(String code) throws SQLException {

        String sql = "SELECT * FROM delivery WHERE code=?";
        ResultSet resultSet = CrudUtil.execute(sql, code);

        if (resultSet.next()) {
            return new DeliveryDTO(
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

    public static int update(DeliveryDTO deliveryDTO) throws SQLException {

        String sql = "UPDATE delivery SET employeeid=? , customerid=? , orderid=? , details=? , location=?  WHERE code=?";
        return CrudUtil.execute(
                sql,
                deliveryDTO.getEmployeeid(),
                deliveryDTO.getCustomerid(),
                deliveryDTO.getOrderid(),
                deliveryDTO.getDetails(),
                deliveryDTO.getLocation(),
                deliveryDTO.getCode()
        );
    }

    public static int delete(String code) throws SQLException {
        String sql = "DELETE FROM delivery WHERE code=?";
        return CrudUtil.execute(sql, code);
    }

    public static List<DeliveryDTO> getAll() throws SQLException {

        List<DeliveryDTO> deliveryDTOList = new ArrayList<>();
        String sql = "SELECT * FROM delivery";
        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()) {
            DeliveryDTO deliveryDTO = new DeliveryDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
            deliveryDTOList.add(deliveryDTO);
        }
        return deliveryDTOList;
    }

}
