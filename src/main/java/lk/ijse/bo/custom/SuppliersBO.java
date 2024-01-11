package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.SuppliersDto;

import java.sql.SQLException;
import java.util.List;

public interface SuppliersBO extends SuperBO {

    boolean addSuppliers(SuppliersDto dto) throws SQLException;

    boolean updateSuppliers(SuppliersDto dto) throws SQLException;

    boolean deleteSuppliers(String sup_id) throws SQLException;

    SuppliersDto searchSuppliers(String code) throws SQLException;

    List<SuppliersDto> getAllSupplier() throws SQLException;
}
