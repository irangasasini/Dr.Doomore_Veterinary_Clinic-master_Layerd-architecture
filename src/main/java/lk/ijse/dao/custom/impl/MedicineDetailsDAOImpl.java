package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.MedicineDetailsDAO;
import lk.ijse.dto.MedicineDetailsDto;
import lk.ijse.dto.Tm.CartTm;
import lk.ijse.entity.MedicineDetail;

import java.sql.SQLException;
import java.util.List;

public class MedicineDetailsDAOImpl implements MedicineDetailsDAO {

    @Override
    public List<MedicineDetail> getAll() throws SQLException  {
        return null;
    }

    @Override
    public boolean save(MedicineDetail dto) throws SQLException  {
        return SQLUtil.sql("INSERT INTO medicine_detail VALUES(?, ?, ?)", dto.getM_code(), dto.getA_id(), dto.getQty());
    }

    @Override
    public boolean update(MedicineDetail dto) throws SQLException  {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException  {
        return false;
    }

    @Override
    public MedicineDetail search(String newValue) throws SQLException  {
        return null;
    }

}
