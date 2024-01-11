package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ItemsDAO;
import lk.ijse.dto.ProductDto;
import lk.ijse.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemsDAOImpl implements ItemsDAO {
    @Override
    public List<Item> getAll() throws SQLException {

        ResultSet resultSet = SQLUtil.sql("SELECT * FROM items");


        List<Item> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new Item(
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            ));
        }
        return dtoList;
    }

    @Override
    public boolean save(Item dto) throws SQLException {
        return SQLUtil.sql("INSERT INTO items VALUES (?,?,?,?)", dto.getI_id(), dto.getQty(), dto.getDescription(), dto.getO_id());
    }

    @Override
    public boolean update(Item dto) throws SQLException {
        return SQLUtil.sql("UPDATE items SET qty =? ,description =? ,o_id =? WHERE i_id =?", dto.getI_id(), dto.getQty(), dto.getDescription(), dto.getO_id());

    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.sql("DELETE FROM items WHERE i_id = ?", id);
    }

    @Override
    public Item search(String newValue) throws SQLException {
        return null;
    }


    @Override
    public Item searchProduct(String code) throws SQLException {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM items WHERE i_id = ?", code);

        Item dto = null;

        if (resultSet.next()) {
            dto = new Item(
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return dto;
    }
}
