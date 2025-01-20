import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/projeto";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER,PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERRO!! Conexão com o banco de dados falhou!");
            return null;
        }
    }

}
