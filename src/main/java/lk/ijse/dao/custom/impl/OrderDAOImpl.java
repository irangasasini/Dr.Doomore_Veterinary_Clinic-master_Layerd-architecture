package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.dto.OrdersDto;
import lk.ijse.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public List<Order> getAll() throws SQLException  {
        return null;
    }

    @Override
    public boolean save(Order dto) throws SQLException  {
        return SQLUtil.sql("INSERT INTO orders VALUES (?,?,?)", dto.getO_id(), LocalDate.now(), dto.getType());
    }

    @Override
    public boolean update(Order dto) throws SQLException  {
        return SQLUtil.sql("UPDATE orders SET date =? ,type =?  WHERE o_id =?", dto.getO_id(), dto.getDate(), dto.getType());
    }

    @Override
    public boolean delete(String id) throws SQLException  {
        return SQLUtil.sql("DELETE FROM orders WHERE o_id = ?", id);
    }

    @Override
    public Order search(String newValue) throws SQLException  {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM orders WHERE o_id = ?", newValue);

        Order dto = null;

        if (resultSet.next()) {
            dto = new Order(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)

            );
        }
        return dto;
    }

    @Override
    public List<Order> getAllOrders() throws SQLException {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM orders");


        List<Order> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new Order(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        }
        return dtoList;
    }
}
