package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserController {
    private Connection connection;
    public UserController(Connection db) {
        this.connection = db;
    }

    int addUser(String username, String password, UserLanguage lang) throws SQLException {
        return connection.createStatement().executeUpdate(String.format("INSERT INTO users (username, password, language) VALUES (\"%s\", \"%s\", \"%s\")", username, password, lang));
    }

    int updateUser(Integer id, String key, String value) throws SQLException {
        return connection.createStatement().executeUpdate(String.format("UPDATE users SET %s=\"%s\" WHERE id=%d", key, value, id));
    }
    int deleteUser(Integer id) throws SQLException {
        return connection.createStatement().executeUpdate(String.format("DELETE from users where id=%d", id));
    }

    ArrayList<User> findUsersByLanguage(UserLanguage lang) throws SQLException {
        ResultSet result = connection.createStatement().executeQuery(String.format("SELECT * from users WHERE language=\"%s\"", lang));
        ArrayList<User> list = new ArrayList<>();
        while(result.next()) {
            list.add(new User(result.getString("username"), result.getString("password"), result.getString("language")));
        }
        return list;
    }
}
