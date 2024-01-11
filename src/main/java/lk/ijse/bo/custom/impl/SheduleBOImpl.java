package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ScheduleBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ScheduleDAO;
import lk.ijse.dao.custom.impl.ScheduleDAOImpl;
import lk.ijse.dto.AppointmentDto;
import lk.ijse.dto.SheduleDto;
import lk.ijse.entity.Appointment;
import lk.ijse.entity.Schedule;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SheduleBOImpl implements ScheduleBO {

    ScheduleDAO scheduleDAO = (ScheduleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SCHEDULE);

    @Override
    public boolean addShedule(SheduleDto dto) throws SQLException {
        return scheduleDAO.save(new Schedule(dto.getSh_id(), dto.getDate(), dto.getTime(), dto.getV_id()));
    }

    @Override
    public boolean updateShedule(SheduleDto dto) throws SQLException {
        return scheduleDAO.update(new Schedule(dto.getSh_id(), dto.getDate(), dto.getTime(), dto.getV_id()));
    }

    @Override
    public boolean deleteShedule(String sh_id) throws SQLException {
        return scheduleDAO.delete(sh_id);
    }

    @Override
    public SheduleDto searchShedule(String code) throws SQLException {
        Schedule schedule = scheduleDAO.search(code);
        return new SheduleDto(schedule.getSh_id(), schedule.getDate(), schedule.getTime(), schedule.getV_id());
    }

    @Override
    public List<SheduleDto> getAllShedule() throws SQLException {
        List<Schedule> list = scheduleDAO.getAll();
        List<SheduleDto>dtoList=new ArrayList<>();

        for (Schedule schedule : list){
            dtoList.add(new SheduleDto(schedule.getSh_id(), schedule.getDate(), schedule.getTime(), schedule.getV_id()));
        }
        return dtoList;
    }
}
