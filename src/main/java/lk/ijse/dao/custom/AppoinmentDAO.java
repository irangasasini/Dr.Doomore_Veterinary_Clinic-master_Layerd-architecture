package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.AppointmentDto;
import lk.ijse.entity.Appointment;

import java.sql.SQLException;

public interface AppoinmentDAO extends CrudDAO<Appointment> {
    boolean updateAppoinmentToPaid(String appoinmentId) throws SQLException;
}
