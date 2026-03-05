package com.eventmanager.controller;

import com.eventmanager.model.Event;
import com.eventmanager.model.User;
import com.eventmanager.service.EventService;
import com.eventmanager.service.UserService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class EventController {
    private UserService userService;
    private EventService eventService;
    private Scanner scanner;

    public EventController() {
        this.userService = new UserService();
        this.eventService = new EventService();
        this.scanner = new Scanner(System.in);
    }

    public void cadastrarUsuario() {
        System.out.println("\n--- CADASTRO DE USUÁRIO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Horário preferido: ");
        String horario = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        User usuario = new User(nome, endereco, categoria, horario, descricao);
        userService.cadastrarUsuario(usuario);
    }

    public void criarEvento() {
        System.out.println("\n--- CRIAR EVENTO ---");
        System.out.print("Nome do evento: ");
        String nome = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Data e hora (dd/MM/yyyy HH:mm): ");
        String dataHoraStr = scanner.nextLine();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime horario = LocalDateTime.parse(dataHoraStr, formatter);

            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();

            Event evento = new Event(nome, endereco, categoria, horario, descricao);
            eventService.criarEvento(evento);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido!");
        }
    }

    public void listarEventos() {
        System.out.println("\n--- LISTA DE EVENTOS ---");
        List<Event> eventos = eventService.listarEventos();

        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }

        for (int i = 0; i < eventos.size(); i++) {
            System.out.println((i + 1) + ". " + eventos.get(i));
        }
    }

    public void participarEvento() {
        System.out.println("\n--- PARTICIPAR DE EVENTO ---");
        System.out.print("Seu nome: ");
        String nomeUsuario = scanner.nextLine();

        User usuario = userService.buscarUsuario(nomeUsuario);
        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        System.out.print("Nome do evento: ");
        String nomeEvento = scanner.nextLine();

        Event evento = eventService.buscarEvento(nomeEvento);
        if (evento == null) {
            System.out.println("Evento não encontrado!");
            return;
        }

        evento.adicionarParticipante(nomeUsuario);
        usuario.adicionarEvento(nomeEvento);
        System.out.println("Participação confirmada!");
    }

    public void cancelarParticipacao() {
        System.out.println("\n--- CANCELAR PARTICIPAÇÃO ---");
        System.out.print("Seu nome: ");
        String nomeUsuario = scanner.nextLine();

        User usuario = userService.buscarUsuario(nomeUsuario);
        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        System.out.print("Nome do evento: ");
        String nomeEvento = scanner.nextLine();

        Event evento = eventService.buscarEvento(nomeEvento);
        if (evento == null) {
            System.out.println("Evento não encontrado!");
            return;
        }

        evento.removerParticipante(nomeUsuario);
        usuario.removerEvento(nomeEvento);
        System.out.println("Participação cancelada!");
    }

    public void visualizarMeusEventos() {
        System.out.println("\n--- MEUS EVENTOS ---");
        System.out.print("Seu nome: ");
        String nomeUsuario = scanner.nextLine();

        User usuario = userService.buscarUsuario(nomeUsuario);
        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        List<String> eventosUsuario = usuario.getEventosParticipando();
        if (eventosUsuario.isEmpty()) {
            System.out.println("Você não está participando de nenhum evento.");
            return;
        }

        System.out.println("Eventos que você está participando:");
        for (String nomeEvento : eventosUsuario) {
            Event evento = eventService.buscarEvento(nomeEvento);
            if (evento != null) {
                System.out.println("- " + evento);
            }
        }
    }

    public void eventosProximos() {
        System.out.println("\n--- EVENTOS PRÓXIMOS ---");
        List<Event> eventosProximos = eventService.buscarEventosProximos();

        if (eventosProximos.isEmpty()) {
            System.out.println("Nenhum evento próximo encontrado.");
            return;
        }

        System.out.println("Eventos nos próximos 7 dias:");
        for (Event evento : eventosProximos) {
            System.out.println("- " + evento);
        }
    }
}