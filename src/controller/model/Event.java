package controller.model;
import java.time.LocalDateTime;

public class Event {
    private String nome;
    private String endereco;
    private String categoria;
    private int tempo;
    private String descricao;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    public Event(String nome, String endereco, String categoria, int tempo, String descricao, LocalDateTime inicio, LocalDateTime fim) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.tempo = tempo;
        this.descricao = descricao;
        this.inicio = inicio;
        this.fim = fim;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getTempo() {
        return tempo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }
    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }
    public LocalDateTime getFim() {
        return fim;
    }

    @Override
    public String toString () {
        return "Nome: " + nome +
                ", Endereço: " + endereco +
                ", Categoria: " + categoria +
                ", Horário: " + tempo +
                ", Descrição: " + descricao;
    }
}
