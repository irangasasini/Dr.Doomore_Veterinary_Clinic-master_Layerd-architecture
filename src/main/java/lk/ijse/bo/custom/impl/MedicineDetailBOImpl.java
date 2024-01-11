package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.MedicineDetailBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.MedicineDetailsDAO;
import lk.ijse.dto.MedicineDetailsDto;
import lk.ijse.dto.Tm.CartTm;
import lk.ijse.entity.MedicineDetail;

import java.sql.SQLException;
import java.util.List;

public class MedicineDetailBOImpl implements MedicineDetailBO {

    MedicineDetailsDAO medicineDetailsDAO = (MedicineDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEDICINEDETAILS);

    @Override
    public boolean saveMedicineDetail(List<CartTm> cartTmList, String appoinmentId) throws SQLException {
        for (CartTm cartTm : cartTmList) {
            if (!saveOrderDetail(appoinmentId, cartTm)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean saveOrderDetail(String appoinmentId, CartTm cartTm) throws SQLException {
        return medicineDetailsDAO.save(new MedicineDetail(cartTm.getCode(), appoinmentId,String.valueOf(cartTm.getQty())));
    }
}
