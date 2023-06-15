package lk.ijse.restaurant.bo.custom;

import lk.ijse.restaurant.bo.SuperBO;
import lk.ijse.restaurant.dto.DeliveryDTO;

import java.sql.SQLException;
import java.util.List;

public interface DeliveryBO extends SuperBO {

    List<DeliveryDTO> loadAllDelivers() throws SQLException;

    int saveDelivers(DeliveryDTO d) throws SQLException;

    DeliveryDTO searchDeliver(String id) throws SQLException;

    int updateDeliver(DeliveryDTO deliveryDTO) throws SQLException;

    int deleteDeliver(String id) throws SQLException;
}
