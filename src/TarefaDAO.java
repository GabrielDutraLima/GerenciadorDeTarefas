import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TarefaDAO {
    public static void salvarTarefa(Tarefa tarefa) {
        String sql = "INSERT INTO tarefas (titulo, descricao, prioridade, concluido, data_vencimento) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Database.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, tarefa.getTitulo());
            pstmt.setString(2, tarefa.getDescricao());
            pstmt.setString(3, tarefa.getPrioridade());
            pstmt.setBoolean(4, tarefa.isConcluido());
            pstmt.setDate(5, java.sql.Date.valueOf(tarefa.getDataVencimento()));

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    tarefa.setId(generatedKeys.getInt(1)); // Define o ID gerado
                }
            }

            System.out.println("✅ Tarefa salva com sucesso no banco de dados!");

        } catch (SQLException e) {
            System.out.println("❌ ERRO ao salvar a tarefa: " + e.getMessage());
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
                boolean concluido = rs.getBoolean("concluido");
                LocalDate dataVencimento = rs.getDate("data_vencimento").toLocalDate();

                Tarefa tarefa = new Tarefa(id, titulo, descricao, dataVencimento, prioridade);
                if (concluido) {
                    tarefa.marcarConcluido();
                }
                tarefas.add(tarefa);
            }

        }catch (Exception e) {
            System.out.println("Erro ao coarregar tarefas: " + e.getMessage());
        }
        return tarefas;
    }

    // Método para atualizar uma tarefa no banco
    public static void atualizarTarefa(Tarefa tarefa) throws SQLException {
        String sql = "UPDATE tarefas SET titulo = ?, descricao = ?, prioridade = ?, concluido = ?, data_vencimento = ? WHERE id = ?";

        try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tarefa.getTitulo());
            pstmt.setString(2, tarefa.getDescricao());
            pstmt.setString(3, tarefa.getPrioridade());
            pstmt.setBoolean(4, tarefa.isConcluido());
            pstmt.setDate(5, java.sql.Date.valueOf(tarefa.getDataVencimento()));
            pstmt.setInt(6, tarefa.getId());

            pstmt.executeUpdate();
            System.out.println("Tarefa atualizada!!");

        } catch (SQLException e ) {
            System.out.println("Erro! Tarefa não atualizada: " + e.getMessage());
        }
    }


    // Método para excluir tarefas
    public static void excluirTarefa(int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";

        try (Connection conn = Database.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Tarefa excluída!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir a tarefa: " + e.getMessage());
        }
    }

    public static Tarefa buscarTarefaPorId(int id) {
        Tarefa tarefa = null;
        String sql = "SELECT * FROM tarefas WHERE id = ?";

        try (Connection conn = Database.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                String prioridade = rs.getString("prioridade");
                boolean concluido = rs.getBoolean("concluido");
                LocalDate dataVencimento = rs.getDate("data_vencimento").toLocalDate();

                tarefa = new Tarefa(id, titulo, descricao, dataVencimento, prioridade);
                if (concluido) {
                    tarefa.marcarConcluido();
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao buscar a tarefa: " + e.getMessage());
        }
        return tarefa;
    }



}

