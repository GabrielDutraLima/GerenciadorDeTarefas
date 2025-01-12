
public class Tarefa {
    private String titulo;
    private String descricao;
    private boolean concluido;

    // contrutor
    public Tarefa(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluido = false;
    }

    public void marcarConcluido() {
        this.concluido = true;
    }

    public boolean isConcluido() {
        return concluido;
    }

    @Override
    public String toString() {
        String status = concluido ? "Concluida" : "Pendente";
        return "Título: " + titulo + ", Descrição: " + descricao + " [" + status + "]";
    }
}
