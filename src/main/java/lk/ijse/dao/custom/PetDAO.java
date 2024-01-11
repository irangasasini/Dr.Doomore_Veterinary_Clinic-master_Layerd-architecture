package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.PetDto;
import lk.ijse.entity.Pet;

import java.sql.SQLException;

public interface PetDAO extends CrudDAO<Pet> {
    String getPetName(String pId) throws SQLException;
}
