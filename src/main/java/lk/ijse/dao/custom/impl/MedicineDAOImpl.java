package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.MedicineDAO;
import lk.ijse.dto.MedicineDto;
import lk.ijse.dto.Tm.CartTm;
import lk.ijse.entity.Medicine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAOImpl implements MedicineDAO {
    @Override
    public List<Medicine> getAll() throws SQLException  {
        ResultSet resultSet = SQLUtil.sql( "SELECT * FROM medicine");


        List<Medicine> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new Medicine(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)
            ));
        }
        return dtoList;    }

    @Override
    public boolean save(Medicine dto) throws SQLException  {
        return SQLUtil.sql("INSERT INTO medicine VALUES (?,?,?,?,?)",dto.getM_code(),dto.getDescription(),dto.getUnitPrice(),dto.getQty(),dto.getA_id());
    }

    @Override
    public boolean update(Medicine dto) throws SQLException  {
        return SQLUtil.sql("UPDATE medicine SET description =?,unitPrice=? ,qty =? ,a_id =? WHERE m_code =?",dto.getM_code(),dto.getDescription(),dto.getUnitPrice(),dto.getQty(),dto.getA_id());
    }

    @Override
    public boolean delete(String id) throws SQLException  {
        return SQLUtil.sql("DELETE FROM medicine WHERE m_code = ?",id);
    }

    @Override
    public Medicine search(String newValue) throws SQLException  {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM medicine WHERE m_code = ?",newValue);

        Medicine dto = null;

        if(resultSet.next()) {
            dto = new Medicine(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)

            );
        }
        return dto;
    }

    @Override
    public boolean updateQty(CartTm cartTm) throws SQLException {

        return SQLUtil.sql("UPDATE medicine SET qty = qty - ? WHERE m_code = ?",cartTm.getQty(),cartTm.getCode());
    }
}
