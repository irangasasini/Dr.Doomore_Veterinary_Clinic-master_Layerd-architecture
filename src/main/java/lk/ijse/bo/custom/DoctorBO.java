package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.DocterDto;

import java.sql.SQLException;
import java.util.List;

public interface DoctorBO extends SuperBO {

    boolean deleteDocter(String v_id) throws SQLException;

    boolean addDocter(DocterDto dto) throws SQLException;

    List<DocterDto> getAllDocter() throws SQLException;

    boolean updateDocter(DocterDto dto) throws SQLException;

    DocterDto searchDocter(String code) throws SQLException;

    String getDocter(String vId) throws SQLException;
}
