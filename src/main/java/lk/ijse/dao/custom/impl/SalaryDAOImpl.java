package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SalaryDAO;
import lk.ijse.dto.SalaryDto;
import lk.ijse.entity.Salary;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public List<Salary> getAll() throws SQLException  {

        ResultSet resultSet = SQLUtil.sql("SELECT * FROM salary");


        List<Salary> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new Salary(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return dtoList;
    }

    @Override
    public boolean save(Salary dto) throws SQLException  {
        return SQLUtil.sql("INSERT INTO salary VALUES (?,?,?,?)",dto.getS_id(), Date.valueOf(dto.getDate()), Time.valueOf(LocalTime.now()),dto.getE_id());
    }

    @Override
    public boolean update(Salary dto) throws SQLException  {
        return SQLUtil.sql("UPDATE salary SET date =? ,time =? ,e_id =? WHERE s_id =?",dto.getS_id(),dto.getDate(),dto.getTime(),dto.getE_id());
    }

    @Override
    public boolean delete(String id) throws SQLException  {
        return SQLUtil.sql( "DELETE FROM salary WHERE s_id = ?",id);
    }

    @Override
    public Salary search(String newValue) throws SQLException  {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM salary WHERE s_id = ?",newValue);

        Salary dto = null;

        if(resultSet.next()) {
            dto = new Salary(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return dto;
    }
}
