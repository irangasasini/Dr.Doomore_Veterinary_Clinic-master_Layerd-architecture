package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.MedicineDto;
import lk.ijse.dto.Tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public interface MedicineBO extends SuperBO {

      boolean deleteMedicine(String m_code) throws SQLException ;

     List<MedicineDto> getAllMedicine() throws SQLException ;

     boolean addMedicine(MedicineDto dto) throws SQLException ;

     boolean updateMedicine(MedicineDto dto) throws SQLException ;

     MedicineDto searchMedicine(String code) throws SQLException ;

     boolean updateMedicine(List<CartTm> cartTmList) throws SQLException ;

     boolean updateQty(CartTm cartTm) throws SQLException ;
}
