package lk.ijse.restaurant.dao;

import lk.ijse.restaurant.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER, EMPLOYEE, ITEM, DELIVERY, SALARY, ORDERS, ORDERDETAILS, SUPPLIER, SUPPLIERDETAIL, QUERY
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();
            case ITEM:
                return (T) new ItemDAOImpl();
            case DELIVERY:
                return (T) new DeliveryDAOImpl();
            case SALARY:
                return (T) new SalaryDAOImpl();
            case ORDERS:
                return (T) new OrdersDAOImpl();
            case ORDERDETAILS:
                return (T) new OrderDetailsDAOImpl();
            case SUPPLIER:
                return (T) new SupplierDAOImpl();
            case SUPPLIERDETAIL:
                return (T) new SupplierDetailDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            default:
                return null;
        }
    }
}
