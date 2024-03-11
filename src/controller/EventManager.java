package controller;

import controller.model.Event;
import controller.model.EventCategory;
import controller.model.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class EventManager {
    private final List<User> usuarios;
    private final List<Event> eventos;

    public EventManager() {
        usuarios = new ArrayList<>();
        eventos = new ArrayList<>();
    }

    public List<Event> getEventos() {
        return eventos;
    }

    public void cadastrarUsuario(String nome, String email, String cidade) {
        // Cria um novo objeto User
        User novoUsuario = new User(nome, email, cidade);
        // Adiciona o novo usuário à lista de usuários
        usuarios.add(novoUsuario);
    }
    public void cadastrarEvento(Event event) {
        // Cria um novo objeto Event
        Event novoEvento = new Event(event.getNome(), event.getEndereco(), event.getCategoria(),event.getTempo(), event.getDescricao(), event.getInicio(), event.getFim());
        eventos.add(novoEvento);
    }

    public void consultarEventos() {
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
        } else {
            System.out.println("Eventos cadastrados: ");
            for (int i = 0; i < eventos.size(); i++) {
                System.out.println((i+1) + ". " + eventos.get(i));
            }
        }
    }

    public User buscarUsuarioPeloNome(String nome) {
        for (User usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                return usuario;
            }
        }
        return null; // Retorna null se o usuário não for encontrado
    }

    public void participarEvento(Event evento, User usuario) {
        if (eventos.contains(evento)) {
            if (usuario.getEventosConfirmados().contains(evento)) {
                System.out.println("Você já está participando deste evento.");
            } else {
                usuario.adicionarEventoConfirmado(evento);
                System.out.println("Você participou do evento: " + evento.getNome());
            }
        } else {
            System.out.println("Evento não encontrado.");
        }
    }
    public void visualizarEventosConfirmados(User usuario) {
        List<Event> eventosConfirmados = usuario.getEventosConfirmados();
        if (eventosConfirmados.isEmpty()) {
            System.out.println("Você não está confirmado em nenhum evento.");
        } else {
            System.out.println("Eventos confirmados:");
            for (int i = 0; i < eventosConfirmados.size(); i++) {
                System.out.println((i + 1) + ". " + eventosConfirmados.get(i));
            }
        }
    }
    public void cancelarParticipacaoEvento(String nomeEvento, User usuario) {
        List<Event> eventosConfirmados = usuario.getEventosConfirmados();
        Event eventoSelecionado = null;
        for (Event evento : eventosConfirmados) {
            if (evento.getNome().equalsIgnoreCase(nomeEvento)) {
                eventoSelecionado = evento;
                break;
            }
        }
        if (eventoSelecionado != null) {
            eventosConfirmados.remove(eventoSelecionado);
            System.out.println("Você cancelou sua participação no evento: " + eventoSelecionado.getNome());
        } else {
            System.out.println("Evento não encontrado.");
        }
    }
    public static void cadastrarEvento(Scanner scanner, EventManager eventManager) {
        System.out.println("Nome do evento: ");
        String nome = scanner.nextLine();

        System.out.println("Endereço do evento:" );
        String endereço = scanner.nextLine();

        System.out.println("Selecione uma dessas categorias de evento: ");
        for (EventCategory.CategoriaEvento categoria : EventCategory.CategoriaEvento.values()) {
            System.out.println(categoria);
        }
        EventCategory.CategoriaEvento categoriaSelecionada = EventCategory.CategoriaEvento.valueOf(scanner.next().toUpperCase());

        System.out.println("Tempo do evento em horas: ");
        int tempo = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Informe a data e hora de início do evento (no formato 'yyyy-MM-dd HH:mm:ss'): ");
        String inicioStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime inicio = LocalDateTime.parse(inicioStr, formatter);

        System.out.println("Informe a data e hora de fim do evento (no formato 'yyyy-MM-dd HH:mm:ss'): ");
        String fimStr = scanner.nextLine();
        LocalDateTime fim = LocalDateTime.parse(fimStr, formatter);

        System.out.println("Descreva o seu evento: ");
        String descricao = scanner.nextLine();

        eventManager.cadastrarEvento(new Event(nome,endereço, categoriaSelecionada.toString(), tempo, descricao, inicio, fim));
        System.out.println("Evento cadastrado com sucesso!");
    }
    public List<Event> ordenarEventosPorHorario() {
        List<Event> eventosOrdenados = new ArrayList<>(eventos);
        eventosOrdenados.sort(Comparator.comparing(Event::getInicio));
        return eventosOrdenados;
    }
}
