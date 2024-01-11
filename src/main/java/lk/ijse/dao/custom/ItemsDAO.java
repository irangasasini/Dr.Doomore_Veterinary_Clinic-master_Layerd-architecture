package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.ProductDto;
import lk.ijse.entity.Item;

import java.sql.SQLException;

public interface ItemsDAO extends CrudDAO<Item> {
    Item searchProduct(String code) throws SQLException;
}
