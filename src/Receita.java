import java.time.LocalDate;

public class Receita {
    private int id;
    private String descricao;
    private double valor;
    private LocalDate data;

    public Receita(int id, String descricao, double valor, LocalDate data) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    //Getters
    public int getId(){
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    // Método toString para exibir as receitas de forma legível
    @Override
    public String toString() {
        return "ID: " + id + ", Descrição: " + descricao + ", Valor: R$ " + valor + ", Data: " + data;
    }
}
