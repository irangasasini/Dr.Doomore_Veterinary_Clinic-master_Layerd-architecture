package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.SuppliersDto;
import lk.ijse.entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SuppliersDAO extends CrudDAO<Supplier> {
    List<Supplier> getAllSupplier() throws SQLException;
}
