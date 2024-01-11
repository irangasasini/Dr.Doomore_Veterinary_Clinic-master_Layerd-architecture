package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.PaymentDto;
import lk.ijse.entity.Payment;

import java.sql.SQLException;

public interface PayementDAO extends CrudDAO<Payment> {
    String generateNextPaymentId() throws SQLException;

    String splitId(String current);
}
