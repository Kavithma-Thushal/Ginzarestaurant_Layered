package lk.ijse.restaurant.bo.custom.impl;

import lk.ijse.restaurant.bo.custom.SalaryBO;
import lk.ijse.restaurant.dao.DAOFactory;
import lk.ijse.restaurant.dao.custom.EmployeeDAO;
import lk.ijse.restaurant.dao.custom.SalaryDAO;
import lk.ijse.restaurant.dto.SalaryDTO;
import lk.ijse.restaurant.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryBOImpl implements SalaryBO {

    private SalaryDAO salaryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SALARY);

    @Override
    public List<SalaryDTO> loadAllSalary() throws SQLException {
        List<SalaryDTO> salaryDTOList = new ArrayList<>();
        List<Salary> salaryList = salaryDAO.loadAll();
        for (Salary s : salaryList) {
            salaryDTOList.add(new SalaryDTO(s.getCode(), s.getEmployeeId(), s.getAmount(), s.getDate()));
        }
        return salaryDTOList;
    }

    @Override
    public int saveSalary(SalaryDTO s) throws SQLException{
        return salaryDAO.save(new Salary(s.getCode(),s.getEmployeeid(),s.getAmount(),s.getDate()));
    }

    @Override
    public SalaryDTO searchSalary(String code) throws SQLException {
        Salary s=salaryDAO.search(code);
        return new SalaryDTO(s.getCode(),s.getEmployeeId(),s.getAmount(),s.getDate());
    }

    @Override
    public int updateSalary(SalaryDTO s) throws SQLException {
        return salaryDAO.update(new Salary(s.getCode(),s.getEmployeeid(),s.getAmount(),s.getDate()));
    }

    @Override
    public int deleteSalary(String code) throws SQLException {
        return salaryDAO.delete(code);
    }
}
