package lk.ijse.restaurant.model;

import lk.ijse.restaurant.dto.OrderDTO;
import lk.ijse.restaurant.dto.ItemDTO;
import lk.ijse.restaurant.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemModel {

    public static int save(ItemDTO itemDTO) throws SQLException {
        String sql = "INSERT INTO items VALUES (?,?,?,?)";
        return CrudUtil.execute(
                sql,
                itemDTO.getCode(),
                itemDTO.getDescription(),
                itemDTO.getUnitprice(),
                itemDTO.getQtyonhand()
        );
    }

    public static ItemDTO search(String code) throws SQLException {

        String sql = "SELECT * FROM items WHERE code=?";
        ResultSet resultSet = CrudUtil.execute(sql, code);

        if (resultSet.next()) {
            return new ItemDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
        }
        return null;
    }

    public static int update(ItemDTO itemDTO) throws SQLException {

        String sql = "UPDATE items SET description=? , unitPrice=? , qtyOnHand=? WHERE code=?";
        return CrudUtil.execute(
                sql,
                itemDTO.getDescription(),
                itemDTO.getUnitprice(),
                itemDTO.getQtyonhand(),
                itemDTO.getCode()
        );
    }

    public static int delete(String code) throws SQLException {
        String sql = "DELETE FROM items WHERE code=?";
        return CrudUtil.execute(sql, code);
    }

    public static List<ItemDTO> getAll() throws SQLException {

        List<ItemDTO> itemDTOList = new ArrayList<>();
        String sql = "SELECT * FROM items";
        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()) {
            ItemDTO itemDTO = new ItemDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
            itemDTOList.add(itemDTO);
        }
        return itemDTOList;
    }

    public static List<String> loadCodes() throws SQLException {
        String sql = "SELECT code FROM items";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String> data = new ArrayList<>();
        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static ItemDTO searchById(String code) throws SQLException {
        String sql = "SELECT * FROM items WHERE code = ?";
        ResultSet resultSet = CrudUtil.execute(sql, code);

        if (resultSet.next()) {
            return new ItemDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
        }
        return null;
    }

    public static boolean updateQty(List<OrderDTO> orderDTOList) throws SQLException {
        for (OrderDTO dto : orderDTOList) {
            if (!updateQty(dto)) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(OrderDTO dto) throws SQLException {
        String sql = "UPDATE items SET qtyOnHand = (qtyOnHand - ?) WHERE code = ?";
        Integer affectedRows = CrudUtil.execute(sql, dto.getQty(), dto.getCode());
        return affectedRows > 0;
    }
}
