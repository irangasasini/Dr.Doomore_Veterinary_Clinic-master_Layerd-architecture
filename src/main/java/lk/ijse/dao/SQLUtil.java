package lk.ijse.dao;

import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {

    public static <T> T sql(String sql, Object... args) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            stm.setObject((i + 1), args[i]);
        }

        if (sql.startsWith("SELECT") || sql.startsWith("select") || sql.startsWith("Select")) {
            return (T) stm.executeQuery();
        } else {
            return (T) (Boolean) (stm.executeUpdate() > 0);
        }
    }
}
