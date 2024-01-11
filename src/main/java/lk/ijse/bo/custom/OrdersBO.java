package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.OrdersDto;

import java.sql.SQLException;
import java.util.List;

public interface OrdersBO extends SuperBO {

    boolean deleteOrders(String o_id) throws SQLException;

    OrdersDto searchOrders(String code) throws SQLException;

    boolean updateOrders(OrdersDto dto) throws SQLException;

    boolean addOrders(OrdersDto dto) throws SQLException;

    List<OrdersDto> getAllOrders() throws SQLException;
}
