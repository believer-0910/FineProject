package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static utils.BaseData.*;

public interface BaseDB {

    static Connection getConnection(){
        try {
            return DriverManager.getConnection(URL, USERNAME_DATABASE, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
