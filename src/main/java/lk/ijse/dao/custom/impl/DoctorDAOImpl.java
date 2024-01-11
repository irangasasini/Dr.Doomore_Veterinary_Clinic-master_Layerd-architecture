package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.DoctorDAO;
import lk.ijse.entity.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAOImpl implements DoctorDAO {


    @Override
    public List<Doctor> getAll() throws SQLException  {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM veterinary");


        List<Doctor> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new Doctor(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return dtoList;
    }

    @Override
    public boolean save(Doctor dto) throws SQLException  {
        return SQLUtil.sql("INSERT INTO veterinary VALUES (?,?,?,?)",dto.getV_id(),dto.getAddress(),dto.getName(),dto.getTel_number());
    }

    @Override
    public boolean update(Doctor dto) throws SQLException  {
        return SQLUtil.sql("UPDATE veterinary SET address =? ,name =? ,tel_number=? WHERE v_id =?",dto.getV_id(),dto.getAddress(),dto.getName(),dto.getTel_number());
    }

    @Override
    public boolean delete(String id) throws SQLException  {
        return SQLUtil.sql( "DELETE FROM veterinary WHERE v_id = ?",id);
    }

    @Override
    public Doctor search(String newValue) throws SQLException  {
        ResultSet resultSet = SQLUtil.sql( "SELECT * FROM veterinary WHERE v_id = ?",newValue);

        Doctor dto = null;

        if(resultSet.next()) {
            dto = new Doctor(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return dto;
    }

    @Override
    public  String getDocter(String vId) throws SQLException {

        ResultSet resultSet = SQLUtil.sql("SELECT * FROM veterinary WHERE v_id = ?",vId);

        Doctor dto = null;

        if(resultSet.next()) {
            return resultSet.getString(2);
        }
        return null;

    }
}


