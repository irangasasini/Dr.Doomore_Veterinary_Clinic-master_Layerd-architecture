package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ScheduleDAO;
import lk.ijse.dto.SheduleDto;
import lk.ijse.entity.Schedule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAOImpl implements ScheduleDAO {
    @Override
    public List<Schedule> getAll() throws SQLException  {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM schedule");


        List<Schedule> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new Schedule(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return dtoList;    }

    @Override
    public boolean save(Schedule dto) throws SQLException  {
        return SQLUtil.sql( "INSERT INTO schedule VALUES (?,?,?,?)",dto.getSh_id(),dto.getDate(),dto.getTime(),dto.getV_id());
    }

    @Override
    public boolean update(Schedule dto) throws SQLException  {
        return SQLUtil.sql("UPDATE schedule SET date =? ,time =? ,v_id =? WHERE sh_id =?",dto.getSh_id(),dto.getDate(),dto.getTime(),dto.getV_id());
    }

    @Override
    public boolean delete(String id) throws SQLException  {
        return SQLUtil.sql("DELETE FROM schedule WHERE sh_id = ?",id);
    }

    @Override
    public Schedule search(String newValue) throws SQLException  {

        ResultSet resultSet = SQLUtil.sql("SELECT * FROM schedule WHERE sh_id = ?",newValue);

        Schedule dto = null;

        if(resultSet.next()) {
            dto = new Schedule(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return dto;    }
}
