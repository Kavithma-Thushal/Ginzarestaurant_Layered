package lk.ijse.restaurant.bo;

import lk.ijse.restaurant.bo.custom.impl.*;

public class BoFactory {

    private static BoFactory boFactory;

    private BoFactory() {

    }

    public static BoFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BoFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER, EMPLOYEE, ITEM, DELIVERY, SALARY, ORDERS, SUPPLIER
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case EMPLOYEE:
                return (T) new EmployeeBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            case DELIVERY:
                return (T) new DeliveryBOImpl();
            case SALARY:
                return (T) new SalaryBOImpl();
            case ORDERS:
                return (T) new OrdersBOImpl();
            case SUPPLIER:
                return (T) new SupplierBOImpl();
            default:
                return null;
        }
    }
}
