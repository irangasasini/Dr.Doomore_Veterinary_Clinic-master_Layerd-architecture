package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.SheduleDto;

import java.sql.SQLException;
import java.util.List;

public interface ScheduleBO extends SuperBO {

    boolean addShedule(SheduleDto dto) throws SQLException;


    boolean updateShedule(SheduleDto dto) throws SQLException;

    boolean deleteShedule(String sh_id) throws SQLException;

    SheduleDto searchShedule(String code) throws SQLException;

    List<SheduleDto> getAllShedule() throws SQLException;

}