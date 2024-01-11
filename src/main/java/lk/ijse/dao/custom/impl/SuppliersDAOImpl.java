package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SuppliersDAO;
import lk.ijse.dto.SuppliersDto;
import lk.ijse.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuppliersDAOImpl implements SuppliersDAO {

    @Override
    public List<Supplier> getAll() throws SQLException  {
        ResultSet resultSet=SQLUtil.sql("SELECT * FROM suppliers");
        List<Supplier> list= new ArrayList<>();

        while (resultSet.next()){
            list.add(new Supplier( resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)));
        }
        return list;
    }

    @Override
    public boolean save(Supplier dto) throws SQLException  {
        return SQLUtil.sql("INSERT INTO suppliers VALUES (?,?,?,?,?)", dto.getSup_id(), dto.getEmail(), dto.getAddress(), dto.getTel_number(), dto.getE_id());
    }

    @Override
    public boolean update(Supplier dto) throws SQLException  {
        return SQLUtil.sql("UPDATE suppliers SET email =? ,address =? ,tel_number =? ,e_id =? WHERE sup_id =?", dto.getSup_id(), dto.getEmail(), dto.getAddress(), dto.getTel_number(), dto.getE_id());
    }

    @Override
    public boolean delete(String id) throws SQLException  {
        return SQLUtil.sql("DELETE FROM suppliers WHERE sup_id = ?", id);
    }

    @Override
    public Supplier search(String newValue) throws SQLException  {

        ResultSet resultSet = SQLUtil.sql("SELECT * FROM suppliers WHERE p_id = ?", newValue);

        Supplier dto = null;

        if (resultSet.next()) {
            dto = new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return dto;
    }

    @Override
    public List<Supplier> getAllSupplier() throws SQLException {

        ResultSet resultSet = SQLUtil.sql("SELECT * FROM suppliers");


        List<Supplier> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return dtoList;
    }
}
