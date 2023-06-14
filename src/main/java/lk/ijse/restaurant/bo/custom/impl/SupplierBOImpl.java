package lk.ijse.restaurant.bo.custom.impl;

import lk.ijse.restaurant.bo.custom.SupplierBO;
import lk.ijse.restaurant.dao.DAOFactory;
import lk.ijse.restaurant.dao.custom.ItemDAO;
import lk.ijse.restaurant.dao.custom.SupplierDAO;
import lk.ijse.restaurant.dao.custom.SupplierDetailDAO;
import lk.ijse.restaurant.db.DBConnection;
import lk.ijse.restaurant.dto.ItemDTO;
import lk.ijse.restaurant.dto.SupplierDTO;
import lk.ijse.restaurant.dto.Supplier_DetailsDTO;
import lk.ijse.restaurant.entity.Item;
import lk.ijse.restaurant.entity.Supplier;
import lk.ijse.restaurant.entity.Supplier_Details;

import java.sql.Connection;
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
    public String generateNextSupplierId() throws SQLException {
        return supplierDAO.generateNextId();
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
    public boolean addSupplier(SupplierDTO dto) {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            connection.setAutoCommit(false);

            int supplier_Save = supplierDAO.save(new Supplier(dto.getId(), dto.getName(), dto.getContact(), dto.getAddress()));
            if (supplier_Save > 0) {
                boolean success = true;

                for (Supplier_DetailsDTO s : dto.getSupplier_DetailsDTOList()) {

                    int supplierItem_Update = itemDAO.updateSupplyQty(new Supplier_Details(s.getSupplierId(), s.getItemCode(), s.getQty()));
                    if (supplierItem_Update <= 0) {
                        success = false;
                        break;
                    }

                    int supplierDetails_Save = supplierDetailDAO.save(new Supplier_Details(s.getSupplierId(), s.getItemCode(), s.getQty()));
                    if (supplierDetails_Save <= 0) {
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
