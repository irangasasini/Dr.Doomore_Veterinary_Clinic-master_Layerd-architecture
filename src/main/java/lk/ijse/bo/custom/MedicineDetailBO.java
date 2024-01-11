package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.MedicineDetailsDto;
import lk.ijse.dto.Tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public interface MedicineDetailBO extends SuperBO {

     boolean saveMedicineDetail(List<CartTm> cartTmList, String appoinmentId) throws SQLException ;


    boolean saveOrderDetail(String appoinmentId, CartTm cartTm) throws SQLException;
}
