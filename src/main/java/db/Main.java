package db;


import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.Properties;

public class Main {


        public static void main(String[] args) throws SQLException {
            final String DB_URL = "jdbc:mysql://localhost:3306/sda_test";
            Dotenv dotenv = Dotenv.configure().load();


            Properties connectionProps = new Properties();
            connectionProps.put("user", "lou");
            connectionProps.put("password", dotenv.get("DB_PASSWORD"));
            connectionProps.put("serverTimezone", "CET");
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(DB_URL, connectionProps);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (conn == null) return;


            UserController controller = new UserController(conn);
            controller.addUser("test213u1u23", "ksadjasd", UserLanguage.POLISH);
//            controller.updateUser(3, "username", "xd");
//            controller.deleteUser(4);
            System.out.println(controller.findUsersByLanguage(UserLanguage.POLISH));
            conn.close();

    }
}
