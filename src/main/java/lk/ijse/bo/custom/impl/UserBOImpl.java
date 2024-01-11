package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.UserBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.User;

import java.sql.SQLException;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);


    @Override
    public boolean searchUser(String username, String password) throws SQLException {
        User user = userDAO.search(username);

        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean saveUser(UserDto dto) throws SQLException {
        return userDAO.save(new User(dto.getUserId(), dto.getUsername(), dto.getPosition(), dto.getPassword()));
    }

    @Override
    public boolean deleteUser(String id) throws SQLException {
        return userDAO.delete(id);

    }

    @Override
    public String generateId() throws SQLException {

        return splitId(userDAO.getLastId());

    }

    @Override
    public String splitId(String current) {
        if (current != null) {
            String[] split = current.split("U");
            int id = Integer.parseInt(split[1]);
            id++;
            return "U00" + id;
        }
        return "U001";
    }
}
