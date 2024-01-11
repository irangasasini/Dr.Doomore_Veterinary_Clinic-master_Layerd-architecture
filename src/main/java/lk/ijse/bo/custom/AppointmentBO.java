package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.AppointmentDto;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentBO extends SuperBO {

    boolean deleteAppointment(String a_id) throws SQLException;

    boolean addAppoinment(AppointmentDto dto) throws SQLException;

    List<AppointmentDto> getAllAppointment() throws SQLException;

    AppointmentDto searchAppointment(String code) throws SQLException;

    boolean updateAppointment(AppointmentDto dto) throws SQLException;

    boolean updateAppoinmentToPaid(String appoinmentId) throws SQLException;
}
