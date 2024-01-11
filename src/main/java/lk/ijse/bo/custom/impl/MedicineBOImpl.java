package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.MedicineBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.MedicineDAO;
import lk.ijse.dao.custom.impl.MedicineDAOImpl;
import lk.ijse.dto.MedicineDto;
import lk.ijse.dto.Tm.CartTm;
import lk.ijse.entity.Medicine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineBOImpl implements MedicineBO {

    MedicineDAO medicineDAO = (MedicineDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEDICINE);

    @Override
    public boolean deleteMedicine(String m_code) throws SQLException {
        return medicineDAO.delete(m_code);
    }

    @Override
    public List<MedicineDto> getAllMedicine() throws SQLException {
        List<Medicine> list = medicineDAO.getAll();
        List<MedicineDto>dtoList=new ArrayList<>();

        for (Medicine medicine : list){
            dtoList.add(new MedicineDto(medicine.getM_code(), medicine.getDescription(), medicine.getUnitPrice(), medicine.getQty(), medicine.getA_id()));
        }
        return dtoList;
    }

    @Override
    public boolean addMedicine(MedicineDto dto) throws SQLException {
        return medicineDAO.save(new Medicine(dto.getM_code(), dto.getDescription(), dto.getUnitPrice(), dto.getQty(), dto.getA_id()));
    }

    @Override
    public boolean updateMedicine(MedicineDto dto) throws SQLException {
        return medicineDAO.update(new Medicine(dto.getM_code(), dto.getDescription(), dto.getUnitPrice(), dto.getQty(), dto.getA_id()));
    }

    @Override
    public MedicineDto searchMedicine(String code) throws SQLException {
        Medicine medicine = medicineDAO.search(code);
        return new MedicineDto(medicine.getM_code(), medicine.getDescription(), medicine.getUnitPrice(), medicine.getQty(), medicine.getA_id());
    }

    @Override
    public boolean updateMedicine(List<CartTm> cartTmList) throws SQLException {
        for (CartTm cartTm : cartTmList) {
            if (!updateQty(cartTm)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean updateQty(CartTm cartTm) throws SQLException {
        return medicineDAO.updateQty(cartTm);
    }
}

