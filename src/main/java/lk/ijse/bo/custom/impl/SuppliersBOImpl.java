package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SuppliersBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.SuppliersDAO;
import lk.ijse.dao.custom.impl.SuppliersDAOImpl;
import lk.ijse.dto.AppointmentDto;
import lk.ijse.dto.SuppliersDto;
import lk.ijse.entity.Appointment;
import lk.ijse.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuppliersBOImpl implements SuppliersBO {

    SuppliersDAO suppliersDAO = (SuppliersDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public boolean addSuppliers(SuppliersDto dto) throws SQLException {
        return suppliersDAO.save(new Supplier(dto.getSup_id(), dto.getEmail(), dto.getAddress(), dto.getTel_number(), dto.getE_id()));
    }

    @Override
    public boolean updateSuppliers(SuppliersDto dto) throws SQLException {
        return suppliersDAO.update(new Supplier(dto.getSup_id(), dto.getEmail(), dto.getAddress(), dto.getTel_number(), dto.getE_id()));
    }

    @Override
    public boolean deleteSuppliers(String sup_id) throws SQLException {
        return suppliersDAO.delete(sup_id);
    }

    @Override
    public SuppliersDto searchSuppliers(String code) throws SQLException {
        Supplier supplier = suppliersDAO.search(code);
        return new SuppliersDto(supplier.getSup_id(), supplier.getEmail(), supplier.getAddress(), supplier.getTel_number(), supplier.getE_id());
    }

    @Override
    public List<SuppliersDto> getAllSupplier() throws SQLException {
        List<Supplier> list = suppliersDAO.getAll();
        List<SuppliersDto> dtoList = new ArrayList<>();

        for (Supplier supplier : list) {
            dtoList.add(new SuppliersDto(supplier.getSup_id(), supplier.getEmail(), supplier.getAddress(), supplier.getTel_number(), supplier.getE_id()));
        }
        return dtoList;
    }
}
