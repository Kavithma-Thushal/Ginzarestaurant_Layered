package lk.ijse.restaurant.bo.custom.impl;

import lk.ijse.restaurant.bo.custom.SalaryBO;
import lk.ijse.restaurant.dao.DAOFactory;
import lk.ijse.restaurant.dao.custom.EmployeeDAO;
import lk.ijse.restaurant.dao.custom.SalaryDAO;
import lk.ijse.restaurant.dto.EmployeeDTO;
import lk.ijse.restaurant.dto.SalaryDTO;
import lk.ijse.restaurant.entity.Employee;
import lk.ijse.restaurant.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryBOImpl implements SalaryBO {

    private SalaryDAO salaryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SALARY);
    private EmployeeDAO employeeDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public int saveSalary(SalaryDTO s) throws SQLException{
        return salaryDAO.save(new Salary(s.getCode(),s.getEmployeeid(),s.getAmount(),s.getDate()));
    }

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
    public String generateNextSalaryCode() throws SQLException {
        return salaryDAO.generateNextId();
    }

    @Override
    public List<String> loadEmployeeIds() throws SQLException {
        return employeeDAO.loadEmployeeIds();
    }

    @Override
    public EmployeeDTO searchByEmployeeId(String id) throws SQLException {
        Employee e=employeeDAO.search(id);
        return new EmployeeDTO(e.getId(),e.getName(),e.getContact(),e.getJobRole(),e.getUsername(),e.getPassword());
    }
}
