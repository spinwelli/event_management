package controller.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String nome;
    private String cidade;
    private int senha;
    private final List<Event> eventosConfirmados;

    public User(String nome, String email, String cidade) {
        this.nome = nome;
        this.cidade = cidade;
        this.eventosConfirmados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setSenha(int senha) { this.senha = senha; }

    public int getSenha () { return senha; }

    public void adicionarEventoConfirmado(Event evento) {
        eventosConfirmados.add(evento); // Adiciona o evento à lista de eventos confirmados
    }

    public List<Event> getEventosConfirmados() {
        return eventosConfirmados;
    }

}
