package lk.ijse.restaurant.bo.custom.impl;

import lk.ijse.restaurant.bo.custom.SupplierBO;
import lk.ijse.restaurant.dao.DAOFactory;
import lk.ijse.restaurant.dao.custom.ItemDAO;
import lk.ijse.restaurant.dao.custom.SupplierDAO;
import lk.ijse.restaurant.dao.custom.SupplierDetailDAO;
import lk.ijse.restaurant.dto.SupplierDTO;
import lk.ijse.restaurant.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    private ItemDAO itemDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private SupplierDAO supplierDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    private SupplierDetailDAO supplierDetailDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SUPPLIERDETAIL);

    @Override
    public List<SupplierDTO> loadAllSuppliers() throws SQLException {
        List<SupplierDTO> arrayList = new ArrayList<>();
        List<Supplier> suppliers = supplierDAO.loadAll();
        for (Supplier s : suppliers) {
            arrayList.add(new SupplierDTO(s.getId(), s.getName(), s.getContact(), s.getAddress()));
        }
        return arrayList;
    }

    @Override
    public int saveSupplier(SupplierDTO s) throws SQLException {
        return supplierDAO.save(new Supplier(s.getId(),s.getName(),s.getContact(),s.getAddress()));
    }

    @Override
    public SupplierDTO searchSupplier(String id) throws SQLException {
        Supplier s=supplierDAO.search(id);
        return new SupplierDTO(s.getId(),s.getName(),s.getContact(),s.getAddress());
    }

    @Override
    public int updateSupplier(SupplierDTO s) throws SQLException {
        return supplierDAO.update(new Supplier(s.getId(),s.getName(),s.getContact(),s.getAddress()));
    }

    @Override
    public int deleteSupplier(String id) throws SQLException {
        return supplierDAO.delete(id);
    }
}
