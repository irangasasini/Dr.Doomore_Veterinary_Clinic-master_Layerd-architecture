package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.DocterDto;
import lk.ijse.entity.Doctor;

import java.sql.SQLException;

public interface DoctorDAO extends CrudDAO<Doctor> {
    String getDocter(String vId) throws SQLException;

}
