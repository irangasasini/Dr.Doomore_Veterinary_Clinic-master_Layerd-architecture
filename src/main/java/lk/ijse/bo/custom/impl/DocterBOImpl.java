package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.DoctorBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.DoctorDAO;
import lk.ijse.dto.DocterDto;
import lk.ijse.entity.Doctor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocterBOImpl implements DoctorBO {

    DoctorDAO doctorDAO = (DoctorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DOCTOR);

    @Override
    public boolean deleteDocter(String v_id) throws SQLException {

        return SQLUtil.sql("DELETE FROM veterinary WHERE v_id = ?", v_id);
    }

    @Override
    public boolean addDocter(DocterDto dto) throws SQLException {
        return doctorDAO.save(new Doctor(dto.getV_id(), dto.getAddress(),dto.getName(),dto.getTel_number()));
    }

    @Override
    public List<DocterDto> getAllDocter() throws SQLException {
        List<Doctor> list = doctorDAO.getAll();
        List<DocterDto>dtoList=new ArrayList<>();

        for (Doctor doctor : list) {
          dtoList.add(new DocterDto(doctor.getV_id(),doctor.getAddress(),doctor.getName(),doctor.getTel_number()));
          }
        return dtoList;
        }


    @Override
    public boolean updateDocter(DocterDto dto) throws SQLException {
        return doctorDAO.update(new Doctor(dto.getV_id(), dto.getAddress(),dto.getName(),dto.getTel_number()));
    }

    @Override
    public DocterDto searchDocter(String code) throws SQLException {
        Doctor doctor = doctorDAO.search(code);
        return new DocterDto(doctor.getV_id(),doctor.getAddress(),doctor.getName(),doctor.getTel_number());
    }

    @Override
    public String getDocter(String vId) throws SQLException {
        return doctorDAO.getDocter(vId);

    }
}
