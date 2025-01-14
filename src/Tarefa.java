import com.sun.source.tree.ReturnTree;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Tarefa implements Comparable<Tarefa> {
    private String titulo;
    private String descricao;
    private boolean concluido;
    private LocalDate dataVencimento;

    // contrutor
    public Tarefa(String titulo, String descricao, LocalDate dataVencimento) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluido = false;
        this.dataVencimento = dataVencimento;
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
        return this.dataVencimento.compareTo(outra.dataVencimento);
    }
}
