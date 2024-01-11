package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.dto.OrdersDto;
import lk.ijse.entity.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDAO {
    @Override
    public List<Order> getAll() throws SQLException  {
        return null;
    }

    @Override
    public boolean save(Order dto) throws SQLException  {
        return false;
    }

    @Override
    public boolean update(Order dto) throws SQLException  {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException  {
        return false;
    }

    @Override
    public Order search(String newValue) throws SQLException  {
        return null;
    }

    @Override
    public List<Order> getAllOrders() throws SQLException {
        return null;
    }
}
