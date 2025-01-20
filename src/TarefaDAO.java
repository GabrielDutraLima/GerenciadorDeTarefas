import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TarefaDAO {
    public static void salvarTarefa(Tarefa tarefa) {
        String sql = "INSERT INTO tarefas (titulo, descricao, prioridade, concluido, data_vencimento) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Database.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tarefa.getTitulo());
            pstmt.setString(2, tarefa.getDescricao());
            pstmt.setString(3, tarefa.getPrioridade());
            pstmt.setBoolean(4, tarefa.isConcluido());
            pstmt.setDate(5, java.sql.Date.valueOf(tarefa.getDataVencimento()));

            pstmt.executeUpdate();
            System.out.println("Tarefa salva com sucesso no banco de dados!");

        } catch (SQLException e) {
            System.out.println("ERRO ao salvar a tarefa" + e.getMessage());
        }
    }

    // Método para carregar todas as tarefas do banco
    public static ArrayList<Tarefa> carregarTarefas() {
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas";

        try (Connection conn = Database.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                String prioridade = rs.getString("prioridade");

            }

        }catch (Exception e) {

        }
        return tarefas;
    }
}
