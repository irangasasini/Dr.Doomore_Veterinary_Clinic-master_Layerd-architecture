package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public List<Customer> getAll() throws SQLException  {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM customer");


        List<Customer> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return dtoList;
    }

    @Override
    public boolean save(Customer dto) throws SQLException  {
        return SQLUtil.sql( "INSERT INTO customer VALUES (?,?,?,?)",dto.getC_id(),dto.getEmail(),dto.getTel_number(),dto.getE_id());
    }

    @Override
    public boolean update(Customer dto) throws SQLException  {
        return SQLUtil.sql("UPDATE customer SET email =? ,tel_number =? ,e_id =? WHERE c_id =?",dto.getEmail(),dto.getTel_number(),dto.getE_id(),dto.getC_id());

    }

    @Override
    public boolean delete(String id) throws SQLException  {
        return SQLUtil.sql("DELETE FROM customer WHERE c_id = ?",id);
    }

    @Override
    public Customer search(String newValue) throws SQLException  {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM customer WHERE c_id = ?",newValue);

        Customer dto = null;

        if(resultSet.next()) {
            dto = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return dto;    }
}
