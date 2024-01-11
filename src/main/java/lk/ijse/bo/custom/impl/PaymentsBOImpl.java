package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PaymentsBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AppoinmentDAO;
import lk.ijse.dao.custom.MedicineDAO;
import lk.ijse.dao.custom.PayementDAO;
import lk.ijse.dto.AppointmentDto;
import lk.ijse.dto.MedicineDto;
import lk.ijse.dto.PaymentDto;
import lk.ijse.entity.Appointment;
import lk.ijse.entity.Medicine;
import lk.ijse.entity.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentsBOImpl implements PaymentsBO {

    PayementDAO payementDAO = (PayementDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    AppoinmentDAO appoinmentDAO = (AppoinmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.APPOINTMENT);

    MedicineDAO medicineDAO = (MedicineDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEDICINE);

    @Override
    public List<PaymentDto> getAllPayments() throws SQLException {
        List<Payment> list = payementDAO.getAll();
        List<PaymentDto>dtoList = new ArrayList<>();

        for (Payment payment : list){
            dtoList.add(new PaymentDto(payment.getP_id(),payment.getTime(),payment.getDate(),payment.getAmount(),payment.getA_id()));
        }
        return dtoList;
    }

    @Override
    public boolean addPayment(PaymentDto dto) throws SQLException {
        return payementDAO.save(new Payment(dto.getP_id(),dto.getTime(),dto.getDate(),dto.getAmount(),dto.getA_id()));
    }

    @Override
    public PaymentDto searchPayment(String code) throws SQLException {
        Payment payment = payementDAO.search(code);
        return new PaymentDto(payment.getP_id(),payment.getTime(),payment.getDate(),payment.getAmount(),payment.getA_id());

    }

    @Override
    public String generateNextPaymentId() throws SQLException {
        return payementDAO.generateNextPaymentId();
    }

    @Override
    public String splitId(String current) {
        if (current != null) {
            String[] split = current.split("P");
            int id = Integer.parseInt(split[1]);
            id++;
            if (9 >= id && id > 0) return "P00" + id;
            else if (99 >= id && id > 9) return "P0" + id;
            else if (id > 99) return String.valueOf(id);
        }
        return "P001";
    }

    @Override
    public List<AppointmentDto> getAllAppointment() throws SQLException {
        List<Appointment> list = appoinmentDAO.getAll();
        List<AppointmentDto>dtoList=new ArrayList<>();

        for (Appointment appointment : list){
            dtoList.add(new AppointmentDto(appointment.getA_id(),appointment.getAppointment_status(),appointment.getDate(),appointment.getP_id(),appointment.getV_id()));
        }
        return dtoList;
    }

    @Override
    public List<MedicineDto> getAllMedicine() throws SQLException {
        List<Medicine> list = medicineDAO.getAll();
        List<MedicineDto>dtoList=new ArrayList<>();

        for (Medicine medicine : list){
            dtoList.add(new MedicineDto(medicine.getM_code(), medicine.getDescription(), medicine.getUnitPrice(), medicine.getQty(), medicine.getA_id()));
        }
        return dtoList;    }
}
