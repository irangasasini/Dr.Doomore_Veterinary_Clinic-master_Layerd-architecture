package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ProductBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ItemsDAO;
import lk.ijse.dao.custom.impl.ItemsDAOImpl;
import lk.ijse.dto.AppointmentDto;
import lk.ijse.dto.ProductDto;
import lk.ijse.entity.Appointment;
import lk.ijse.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBOImpl implements ProductBO {

    ItemsDAO itemsDAO = (ItemsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEMS);

    @Override
    public boolean addProduct(ProductDto dto) throws SQLException {
        return itemsDAO.save(new Item(dto.getI_id(), dto.getQty(), dto.getDescription(), dto.getO_id()));
    }

    @Override
    public boolean deleteProduct(String i_id) throws SQLException {
        return itemsDAO.delete(i_id);
    }

    @Override
    public boolean updateProduct(ProductDto dto) throws SQLException {
        return itemsDAO.update(new Item(dto.getI_id(), dto.getQty(), dto.getDescription(), dto.getO_id()));
    }

    @Override
    public ProductDto searchProduct(String code) throws SQLException {
        Item item = itemsDAO.searchProduct(code);
        return new ProductDto(item.getI_id(), item.getQty(), item.getDescription(), item.getO_id());
    }

    @Override
    public List<ProductDto> getAllProduct() throws SQLException {
        List<Item> list = itemsDAO.getAll();
        List<ProductDto>dtoList=new ArrayList<>();

        for (Item item : list){
            dtoList.add(new ProductDto(item.getI_id(), item.getQty(), item.getDescription(), item.getO_id()));
        }
        return dtoList;
       }
}

