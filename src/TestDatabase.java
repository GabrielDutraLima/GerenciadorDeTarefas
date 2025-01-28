import java.sql.Connection;

public class TestDatabase {
    public static void main(String[] args) {
        try (Connection conn = Database.conectar()) {
            if (conn != null) {
                System.out.println("✅ Conexão bem-sucedida!");
            } else {
                System.out.println("❌ Falha ao conectar ao banco!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
    }
}
