package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public List<Employee> getAll() throws SQLException  {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM employees");


        List<Employee> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return dtoList;
    }

    @Override
    public boolean save(Employee dto) throws SQLException  {
        return SQLUtil.sql("INSERT INTO employees VALUES (?,?,?,?)",dto.getE_id(),dto.getName(),dto.getAddress(),dto.getTel_number());

    }

    @Override
    public boolean update(Employee dto) throws SQLException  {
        return SQLUtil.sql( "UPDATE employees SET name =? ,address =? ,tel_number =? WHERE e_id =?",dto.getE_id(),dto.getName(),dto.getAddress(),dto.getTel_number());

    }

    @Override
    public boolean delete(String id) throws SQLException  {
        return SQLUtil.sql("DELETE FROM employees WHERE e_id = ?",id);
    }

    @Override
    public Employee search(String newValue) throws SQLException  {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM employees WHERE e_id = ?",newValue);

        Employee dto = null;

        if(resultSet.next()) {
            dto = new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return dto;
    }
}
