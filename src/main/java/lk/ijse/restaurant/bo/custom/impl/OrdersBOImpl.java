package lk.ijse.restaurant.bo.custom.impl;

import lk.ijse.restaurant.bo.custom.OrdersBO;
import lk.ijse.restaurant.dao.DAOFactory;
import lk.ijse.restaurant.dao.custom.CustomerDAO;
import lk.ijse.restaurant.dao.custom.ItemDAO;
import lk.ijse.restaurant.dao.custom.OrderDetailsDAO;
import lk.ijse.restaurant.dao.custom.OrdersDAO;
import lk.ijse.restaurant.db.DBConnection;
import lk.ijse.restaurant.dto.*;
import lk.ijse.restaurant.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrdersBOImpl implements OrdersBO {

    private OrdersDAO ordersDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERS);
    private OrderDetailsDAO orderDetailsDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    private CustomerDAO customerDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private ItemDAO itemDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public String generateNextOrderId() throws SQLException {
        return ordersDAO.generateNextId();
    }

    @Override
    public List<String> loadCustomerIds() throws SQLException {
        return customerDAO.loadCustomerIds();
    }

    @Override
    public CustomerDTO searchByCustomerId(String id) throws SQLException {
        Customer c = customerDAO.search(id);
        return new CustomerDTO(c.getId(), c.getName(), c.getNic(), c.getEmail(), c.getContact(), c.getAddress());
    }

    @Override
    public List<String> loadItemCodes() throws SQLException {
        return itemDAO.loadItemCodes();
    }

    @Override
    public ItemDTO searchByItemCode(String code) throws SQLException {
        Item i = itemDAO.search(code);
        return new ItemDTO(i.getCode(), i.getDescription(), i.getUnitPrice(), i.getQtyOnHand());
    }

    @Override
    public boolean placeOrder(OrderDTO dto) throws SQLException {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            connection.setAutoCommit(false);

            int orders_Save = ordersDAO.save(new Orders(dto.getId(), dto.getCustomerId()));
            if (orders_Save > 0) {
                boolean success = true;

                for (Order_DetailsDTO detailsDTO : dto.getOrder_detailsDTOList()) {

                    int placeOrderItem_Update = itemDAO.updateOrderQty(new Order_Details(detailsDTO.getOrderId(), detailsDTO.getItemCode(), detailsDTO.getQty(), detailsDTO.getDate()));
                    if (placeOrderItem_Update <= 0) {
                        success = false;
                        break;
                    }

                    int orderDetails_Save = orderDetailsDAO.save(new Order_Details(detailsDTO.getOrderId(), detailsDTO.getItemCode(), detailsDTO.getQty(), detailsDTO.getDate()));
                    if (orderDetails_Save <= 0) {
                        success = false;
                        break;
                    }
                }

                if (success) {
                    connection.commit();
                    connection.setAutoCommit(true);
                    return true;
                }
            }

            connection.rollback();
            connection.setAutoCommit(true);
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
