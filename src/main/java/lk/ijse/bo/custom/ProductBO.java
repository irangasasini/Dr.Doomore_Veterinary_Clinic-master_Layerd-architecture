package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.ProductDto;

import java.sql.SQLException;
import java.util.List;

public interface ProductBO extends SuperBO {

       boolean addProduct(ProductDto dto) throws SQLException ;

       boolean deleteProduct(String i_id) throws SQLException ;

      boolean updateProduct(ProductDto dto) throws SQLException ;

      ProductDto searchProduct(String code) throws SQLException ;

    List<ProductDto> getAllProduct() throws SQLException ;
}
