package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dao.custom.PetDAO;
import lk.ijse.dao.custom.impl.PetDAOImpl;
import lk.ijse.dto.PetDto;

import java.sql.SQLException;
import java.util.List;

public interface PetsBO extends SuperBO {

    PetDAO petDAO = new PetDAOImpl();

      boolean deletePets(String p_id) throws SQLException ;


     boolean addPets(PetDto dto) throws SQLException ;

     boolean updatePets(PetDto dto) throws SQLException ;

     PetDto searchPets(String code) throws SQLException ;


     List<PetDto> getAllPets() throws SQLException ;

     String getPetName(String pId) throws SQLException ;
}
