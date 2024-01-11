package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.AppoinmentDAO;
import lk.ijse.dto.AppointmentDto;
import lk.ijse.entity.Appointment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppoinmentDAOImpl implements AppoinmentDAO {
    @Override
    public List<Appointment> getAll() throws SQLException  {

        ResultSet resultSet = SQLUtil.sql("SELECT * FROM appointment");


        List<Appointment> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new Appointment(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return dtoList;
    }

    @Override
    public boolean save(Appointment dto) throws SQLException  {
        return SQLUtil.sql("INSERT INTO appointment VALUES (?,?,?,?,?)", dto.getA_id(), dto.getAppointment_status(), dto.getDate(), dto.getP_id(), dto.getV_id());
    }

    @Override
    public boolean update(Appointment dto) throws SQLException  {
        return SQLUtil.sql("UPDATE appointment SET appointment_status =? ,date =? , p_id =? ,v_id=? WHERE a_id =?", dto.getA_id(), dto.getAppointment_status(), dto.getDate(), dto.getP_id(), dto.getP_id());
    }

    @Override
    public boolean delete(String id) throws SQLException  {
        return SQLUtil.sql("DELETE FROM appointment WHERE a_id = ?", id);
    }

    @Override
    public Appointment search(String newValue) throws SQLException  {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM appointment WHERE a_id = ?", newValue);

        Appointment dto = null;

        if (resultSet.next()) {
            dto = new Appointment(
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
    public boolean updateAppoinmentToPaid(String appoinmentId) throws SQLException {

        return SQLUtil.sql("UPDATE appointment SET appointment_status = 'Paid' WHERE a_id = ?",appoinmentId);
    }
}
