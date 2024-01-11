package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.OrdersDto;
import lk.ijse.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO extends CrudDAO<Order> {
    List<Order> getAllOrders() throws SQLException;
}
