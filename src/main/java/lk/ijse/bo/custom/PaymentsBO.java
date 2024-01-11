package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.AppointmentDto;
import lk.ijse.dto.MedicineDto;
import lk.ijse.dto.PaymentDto;

import java.sql.SQLException;
import java.util.List;

public interface PaymentsBO extends SuperBO {

     List<PaymentDto> getAllPayments() throws SQLException ;

     boolean addPayment(PaymentDto dto) throws SQLException ;

     PaymentDto searchPayment(String code) throws SQLException ;

     String generateNextPaymentId() throws SQLException ;

    String splitId(String current);

    List<AppointmentDto> getAllAppointment() throws SQLException;

    List<MedicineDto> getAllMedicine() throws SQLException;
}


