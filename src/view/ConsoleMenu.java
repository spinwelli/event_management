package view;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import controller.EventManager;
import controller.model.Event;
import controller.model.User;

import static controller.EventManager.cadastrarEvento;

public class ConsoleMenu {
    public static void main(String[] args) {
        EventManager eventManager = new EventManager();
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Cadastrar evento");
            System.out.println("3. Consultar eventos");
            System.out.println("4. Participar de um evento");
            System.out.println("5. Visualizar eventos confirmados");
            System.out.println("6. Cancelar participação em um evento");
            System.out.println("7. Ver eventos próximos");
            System.out.println("8. Ver eventos passados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            String nomeUsuario; // Declaração da variável nomeUsuario fora do switch-case

            switch (opcao) {
                case 1:
                    System.out.print("Nome do usuário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email do usuário: ");
                    String email = scanner.nextLine();
                    System.out.print("Cidade do usuário: ");
                    String cidade = scanner.nextLine();
                    eventManager.cadastrarUsuario(nome, email, cidade);
                    System.out.println("Usuário cadastrado com sucesso!");
                    break;
                case 2:
                    cadastrarEvento(scanner, eventManager);
                    break;
                case 3:
                    eventManager.consultarEventos();
                    break;
                case 4:
                    System.out.println("Digite o nome do evento que deseja participar: ");
                    String nomeEvento = scanner.nextLine();
                    System.out.println("Digite seu nome de usuário: ");
                    nomeUsuario = scanner.nextLine();
                    User usuario = eventManager.buscarUsuarioPeloNome(nomeUsuario);

                    // Procurar o evento na lista de eventos pelo nome
                    Event evento = null;
                    for (Event e : eventManager.getEventos()) {
                        if (e.getNome().equalsIgnoreCase(nomeEvento)) {
                            evento = e;
                            break;
                        }
                    }

                    // Verificar se o evento foi encontrado
                    if (evento != null) {
                        // Participar do evento encontrado
                        eventManager.participarEvento(evento, usuario);
                    } else {
                        System.out.println("Evento não encontrado.");
                    }
                    break;
                case 5:
                    System.out.println("Digite o nome do usuário para visualizar seus eventos confirmados: ");
                    nomeUsuario = scanner.nextLine(); // Reutilização da variável nomeUsuario
                    User usuarioExistente = eventManager.buscarUsuarioPeloNome(nomeUsuario);
                    if (usuarioExistente != null) {
                        eventManager.visualizarEventosConfirmados(usuarioExistente);
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
                case 6:
                    System.out.println("Digite o nome do usuário para cancelar a participação no evento: ");
                    String nomeUsuarioCancelar = scanner.nextLine();
                    usuarioExistente = eventManager.buscarUsuarioPeloNome(nomeUsuarioCancelar);
                    if (usuarioExistente != null) {
                        System.out.println("Digite o nome do evento que deseja cancelar a participação: ");
                        String nomeEventoCancelar = scanner.nextLine();
                        eventManager.cancelarParticipacaoEvento(nomeEventoCancelar, usuarioExistente);
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
                case 7:
                    System.out.println("Eventos próximos:");
                    List<Event> eventosProximos = eventManager.ordenarEventosPorHorario();
                    LocalDateTime now7 = LocalDateTime.now();
                    for (Event evento7 : eventosProximos) {
                        if (evento7.getInicio().isAfter(now7)) {
                            System.out.println(evento7);
                        }
                    }
                    break;
                case 8:
                    System.out.println("Eventos passados:");
                    List<Event> eventosPassados = eventManager.ordenarEventosPorHorario();
                    LocalDateTime now8 = LocalDateTime.now();
                    for (Event evento8 : eventosPassados) {
                        if (evento8.getFim().isBefore(now8)) {
                            System.out.println(evento8);
                        }
                    }
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);
    }
}
