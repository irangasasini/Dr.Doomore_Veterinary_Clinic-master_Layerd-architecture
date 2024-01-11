package lk.ijse.bo.custom.impl;


import lk.ijse.bo.custom.AppointmentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AppoinmentDAO;
import lk.ijse.dto.AppointmentDto;
import lk.ijse.entity.Appointment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentBOImpl implements AppointmentBO {

    AppoinmentDAO appoinmentDAO = (AppoinmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.APPOINTMENT);

    @Override
    public boolean deleteAppointment(String a_id) throws SQLException  {
        return appoinmentDAO.delete(a_id);

    }

    @Override
    public boolean addAppoinment(AppointmentDto dto) throws SQLException  {
        return appoinmentDAO.save(new Appointment(dto.getA_id(),dto.getAppoinment_status(),dto.getDate(),dto.getP_id(),dto.getV_id()));
    }

    @Override
    public List<AppointmentDto> getAllAppointment() throws SQLException  {
        List<Appointment> list = appoinmentDAO.getAll();
        List<AppointmentDto>dtoList=new ArrayList<>();

        for (Appointment appointment : list){
            dtoList.add(new AppointmentDto(appointment.getA_id(),appointment.getAppointment_status(),appointment.getDate(),appointment.getP_id(),appointment.getV_id()));
        }
        return dtoList;
    }

    @Override
    public AppointmentDto searchAppointment(String code) throws SQLException  {
        Appointment appointment = appoinmentDAO.search(code);
        return new AppointmentDto(appointment.getA_id(),appointment.getAppointment_status(),appointment.getDate(),appointment.getP_id(),appointment.getV_id());
    }

    @Override
    public boolean updateAppointment(AppointmentDto dto) throws SQLException  {
        return appoinmentDAO.update(new Appointment(dto.getA_id(),dto.getAppoinment_status(),dto.getDate(),dto.getP_id(),dto.getV_id()));

    }

    @Override
    public boolean updateAppoinmentToPaid(String appoinmentId) throws SQLException {
        return appoinmentDAO.updateAppoinmentToPaid(appoinmentId);
    }
}
