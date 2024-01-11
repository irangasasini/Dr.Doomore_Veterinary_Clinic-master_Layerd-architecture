package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.OrdersBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.dao.custom.impl.OrderDAOImpl;
import lk.ijse.dto.OrdersDto;
import lk.ijse.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersBOImpl implements OrdersBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public boolean deleteOrders(String o_id) throws SQLException {
        return orderDAO.delete(o_id);
    }

    @Override
    public OrdersDto searchOrders(String code) throws SQLException {
        Order order = orderDAO.search(code);
        return new OrdersDto(order.getO_id(),order.getDate(),order.getType());
    }

    @Override
    public boolean updateOrders(OrdersDto dto) throws SQLException {
        return orderDAO.update(new Order(dto.getO_id(),dto.getDate(),dto.getTyp()));
    }

    @Override
    public boolean addOrders(OrdersDto dto) throws SQLException {
        return orderDAO.save(new Order(dto.getO_id(),dto.getDate(),dto.getTyp()));
    }

    @Override
    public List<OrdersDto> getAllOrders() throws SQLException {
        List<Order> list = orderDAO.getAllOrders();
        List<OrdersDto> dtoList = new ArrayList<>();

        for (Order order : list) {
            dtoList.add(new OrdersDto(order.getO_id(), order.getDate(), order.getType()));
        }
        return dtoList;
    }
}

