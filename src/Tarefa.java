import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tarefa implements Comparable<Tarefa> {
    private String titulo;
    private String descricao;
    private boolean concluido;
    private LocalDate dataVencimento;
    private String prioridade;
    private int id;
    // contrutor
    public Tarefa(int id ,String titulo, String descricao, LocalDate dataVencimento, String prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluido = false;
        this.dataVencimento = dataVencimento;
        this.prioridade = prioridade;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int anInt) {
        this.id = id;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPrioridade() {
        return prioridade;
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
        String dataFormatada = dataVencimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return "Título: " + titulo + ", Descrição: " + descricao + "Vencimento: " + dataFormatada + " [" + status + "]";
    }

    @Override
    public int compareTo(Tarefa outra) {
        int prioridadeComparacao = this.prioridade.compareTo(outra.prioridade);
        if (prioridadeComparacao == 0) {
            return this.dataVencimento.compareTo(outra.dataVencimento);
        }
        return prioridadeComparacao;
    }
}
