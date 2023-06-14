package lk.ijse.restaurant.model;

import lk.ijse.restaurant.db.DBConnection;
import lk.ijse.restaurant.dto.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PlaceOrderModel {

    public static boolean placeOrder(String oId, String cusId, List<OrderDTO> orderDTOList) throws SQLException {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isSaved = OrderModel.save(oId, cusId);
            if (isSaved) {
                boolean isUpdated = ItemModel.updateQty(orderDTOList);
                if (isUpdated) {
                    boolean isOrdered = true;
                    if (isOrdered) {
                        connection.commit();
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception er) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }

    }
}
