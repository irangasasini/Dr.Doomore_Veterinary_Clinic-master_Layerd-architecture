package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> getAll() throws SQLException  {
        return null;
    }

    @Override
    public boolean save(User dto) throws SQLException  {
        return SQLUtil.sql("INSERT INTO user VALUES (?,?,?,?)",dto.getU_id(),dto.getUsername(),dto.getPosition(),dto.getPassword());
    }

    @Override
    public boolean update(User dto) throws SQLException  {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException  {
        ResultSet resultSet = SQLUtil.sql("DELETE FROM user WHERE u_id=?",id);

        return resultSet.next();    }

    @Override
    public User search(String username) throws SQLException  {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM user WHERE username = ?",username);

        if (resultSet.next()){
            return new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            );
        }
        return null;
    }

    @Override
    public String getLastId() throws SQLException {
        ResultSet resultSet = SQLUtil.sql("SELECT u_id FROM user ORDER BY u_id DESC LIMIT 1");

        String current=null;

        if (resultSet.next()){
            current=resultSet.getString(1);
            return (current);
        }
        return (null);

    }

}
