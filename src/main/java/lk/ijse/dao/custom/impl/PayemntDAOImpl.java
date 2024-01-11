package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.PayementDAO;
import lk.ijse.dto.PaymentDto;
import lk.ijse.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PayemntDAOImpl implements PayementDAO {
    @Override
    public List<Payment> getAll() throws SQLException  {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM payment");


        List<Payment> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new Payment(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return dtoList;
    }

    @Override
    public boolean save(Payment dto) throws SQLException  {
        return SQLUtil.sql("INSERT INTO payment VALUES (?,?,?,?,?)", dto.getP_id(), dto.getTime(), dto.getDate(), dto.getAmount(), dto.getA_id());
    }

    @Override
    public boolean update(Payment dto) throws SQLException  {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException  {
        return false;
    }

    @Override
    public Payment search(String newValue) throws SQLException  {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM payment WHERE p_id = ?", newValue);

        Payment dto = null;

        if (resultSet.next()) {
            dto = new Payment(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return dto;
    }

    @Override
    public String generateNextPaymentId() throws SQLException {
        ResultSet resultSet = SQLUtil.sql("SELECT p_id FROM payment ORDER BY p_id DESC LIMIT 1");


        String current = null;

        if (resultSet.next()) {
            current = resultSet.getString(1);
            return splitId(current);
        }
        return splitId(null);

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
}
