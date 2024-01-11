package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.PetDAO;
import lk.ijse.dto.DocterDto;
import lk.ijse.dto.PetDto;
import lk.ijse.entity.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetDAOImpl implements PetDAO {
    @Override
    public List<Pet> getAll() throws SQLException  {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM pet");


        List<Pet> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new Pet(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            ));
        }
        return dtoList;
    }

    @Override
    public boolean save(Pet dto) throws SQLException  {
        return SQLUtil.sql("INSERT INTO pet VALUES (?,?,?,?,?)",dto.getP_id(),dto.getName(),dto.getAge(),dto.getBreed(),dto.getC_id());
    }

    @Override
    public boolean update(Pet dto) throws SQLException  {
        return SQLUtil.sql( "UPDATE pet SET name =? ,age =? ,breed =? ,c_id=? WHERE p_id =?",dto.getP_id(),dto.getName(),dto.getAge(),dto.getBreed(),dto.getC_id());
    }

    @Override
    public boolean delete(String id) throws SQLException  {
        return SQLUtil.sql("DELETE FROM pet WHERE p_id = ?",id);
    }

    @Override
    public Pet search(String newValue) throws SQLException  {
        ResultSet resultSet = SQLUtil.sql( "SELECT * FROM pet WHERE p_id = ?",newValue);

        Pet dto = null;

        if(resultSet.next()) {
            dto = new Pet(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return dto;
    }

    @Override
    public String getPetName(String pId) throws SQLException {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM pet WHERE p_id = ?",pId);

        DocterDto dto = null;

        if(resultSet.next()) {
            return resultSet.getString(2);
        }
        return null;


    }
}
