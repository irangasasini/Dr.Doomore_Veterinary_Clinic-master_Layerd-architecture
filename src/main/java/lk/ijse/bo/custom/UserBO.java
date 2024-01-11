package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.UserDto;

import java.sql.SQLException;

public interface UserBO extends SuperBO {
    boolean searchUser(String username, String password) throws SQLException;


    boolean saveUser(UserDto dto) throws SQLException;

    boolean deleteUser(String id) throws SQLException;

    String generateId() throws SQLException;

    String splitId(String current);
}
