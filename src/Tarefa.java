
public class Tarefa {
    private String titulo;
    private String descricao;

    // contrutor
    public Tarefa(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Descrição: " + descricao;
    }
}
