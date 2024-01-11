package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PetsBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.PetDAO;
import lk.ijse.dao.custom.impl.PetDAOImpl;
import lk.ijse.dto.AppointmentDto;
import lk.ijse.dto.PetDto;
import lk.ijse.entity.Appointment;
import lk.ijse.entity.Pet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetsBOImpl implements PetsBO {

    PetDAO petDAO = (PetDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PET);

    @Override
    public boolean deletePets(String p_id) throws SQLException {
        return petDAO.delete(p_id);
    }

    @Override
    public boolean addPets(PetDto dto) throws SQLException {
        return petDAO.save(new Pet(dto.getP_id(), dto.getName(), dto.getAge(), dto.getBreed(), dto.getC_id()));
    }

    @Override
    public boolean updatePets(PetDto dto) throws SQLException {
        return petDAO.update(new Pet(dto.getP_id(), dto.getName(), dto.getAge(), dto.getBreed(), dto.getC_id()));
    }

    @Override
    public PetDto searchPets(String code) throws SQLException {
        Pet pet = petDAO.search(code);
        return new PetDto(pet.getP_id(),pet.getName(), pet.getAge(),pet.getBreed(),pet.getC_id());
    }

    @Override
    public List<PetDto> getAllPets() throws SQLException {
        List<Pet> list = petDAO.getAll();
        List<PetDto>dtoList=new ArrayList<>();

        for (Pet pet : list){
            dtoList.add(new PetDto(pet.getP_id(),pet.getName(), pet.getAge(),pet.getBreed(),pet.getC_id()));
        }
        return dtoList;
    }

    @Override
    public String getPetName(String pId) throws SQLException {
        return petDAO.getPetName(pId);

    }

}

