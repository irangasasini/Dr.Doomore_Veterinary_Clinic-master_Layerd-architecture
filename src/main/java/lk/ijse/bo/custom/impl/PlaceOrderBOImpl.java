package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.MedicineBO;
import lk.ijse.bo.custom.MedicineDetailBO;
import lk.ijse.bo.custom.PaymentsBO;
import lk.ijse.bo.custom.PlaceOrderBO;
import lk.ijse.dao.custom.AppoinmentDAO;
import lk.ijse.dao.custom.PayementDAO;
import lk.ijse.dao.custom.impl.AppoinmentDAOImpl;
import lk.ijse.dao.custom.impl.PayemntDAOImpl;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.PaymentDto;
import lk.ijse.dto.PlaceOrderDto;
import lk.ijse.entity.Payment;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    PayementDAO payementDAO = new PayemntDAOImpl();
    AppoinmentDAO appoinmentDAO = new AppoinmentDAOImpl();

    PaymentsBO paymentsBO = new PaymentsBOImpl();

    MedicineBO medicineBO = new MedicineBOImpl();

    MedicineDetailBO medicineDetailBO = new MedicineDetailBOImpl();

    @Override
    public boolean placeOrder(PlaceOrderDto pDto) throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isPaymentSaved = payementDAO.save(new Payment(paymentsBO.generateNextPaymentId(), String.valueOf(pDto.getTime()), String.valueOf(pDto.getDate()), String.valueOf(pDto.getAmount()), pDto.getAppoinmentId()));
            if (isPaymentSaved) {
                if(pDto.getCartTmList().size()!=0){
                    boolean medicineUpdated = medicineBO.updateMedicine(pDto.getCartTmList());
                    if (medicineUpdated){
                        boolean medicineDetailSaved =  medicineDetailBO.saveMedicineDetail(pDto.getCartTmList(),pDto.getAppoinmentId());
                        if (medicineDetailSaved){
                            boolean appoinmentUpdated= appoinmentDAO.updateAppoinmentToPaid(pDto.getAppoinmentId());
                            if (appoinmentUpdated){
                                connection.commit();
                                result = true;
                            }
                        }
                    }
                }else{
                    connection.commit();
                    result = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return result;
    }
}
