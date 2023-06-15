package lk.ijse.restaurant.bo.custom.impl;

import lk.ijse.restaurant.bo.custom.DeliveryBO;
import lk.ijse.restaurant.dao.DAOFactory;
import lk.ijse.restaurant.dao.custom.CustomerDAO;
import lk.ijse.restaurant.dao.custom.DeliveryDAO;
import lk.ijse.restaurant.dao.custom.EmployeeDAO;
import lk.ijse.restaurant.dao.custom.OrdersDAO;
import lk.ijse.restaurant.dto.DeliveryDTO;
import lk.ijse.restaurant.entity.Delivery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryBOImpl implements DeliveryBO {

    private DeliveryDAO deliveryDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DELIVERY);
    private CustomerDAO customerDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private EmployeeDAO employeeDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    private OrdersDAO ordersDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERS);

    @Override
    public List<DeliveryDTO> loadAllDelivers() throws SQLException {
        List<DeliveryDTO> deliveryDTOList=new ArrayList<>();
        List<Delivery> deliveryList=deliveryDAO.loadAll();
        for (Delivery d:deliveryList) {
            deliveryDTOList.add(new DeliveryDTO(d.getCode(),d.getEmployeeId(),d.getCustomerId(),d.getOrderId(),d.getDetails(),d.getLocation()));
        }
        return deliveryDTOList;
    }

    @Override
    public int saveDelivers(DeliveryDTO d) throws SQLException {
        return deliveryDAO.save(new Delivery(d.getCode(),d.getEmployeeid(),d.getCustomerid(),d.getOrderid(),d.getDetails(),d.getLocation()));
    }

    @Override
    public DeliveryDTO searchDeliver(String code) throws SQLException {
        Delivery d=deliveryDAO.search(code);
        return new DeliveryDTO(d.getCode(),d.getEmployeeId(),d.getCustomerId(),d.getOrderId(),d.getDetails(),d.getLocation());
    }

    @Override
    public int updateDeliver(DeliveryDTO d) throws SQLException {
        return deliveryDAO.update(new Delivery(d.getCode(),d.getEmployeeid(),d.getCustomerid(),d.getOrderid(),d.getDetails(),d.getLocation()));
    }

    @Override
    public int deleteDeliver(String code) throws SQLException {
        return deliveryDAO.delete(code);
    }
}
