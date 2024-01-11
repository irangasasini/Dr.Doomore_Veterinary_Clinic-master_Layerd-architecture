package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.MedicineDto;
import lk.ijse.dto.Tm.CartTm;
import lk.ijse.entity.Medicine;

import java.sql.SQLException;
import java.util.List;

public interface MedicineDAO extends CrudDAO<Medicine> {

    boolean updateQty(CartTm cartTm) throws SQLException;
}
