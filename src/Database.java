import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/projeto?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection conectar() {
        try {
            // 👇 Adicione essa linha para garantir que o driver JDBC do MySQL seja carregado
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexão bem-sucedida com o banco de dados!");
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("❌ ERRO!! Driver JDBC do MySQL não encontrado!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ ERRO!! Conexão com o banco de dados falhou!");
            e.printStackTrace();
        }
        return null;
    }
}
