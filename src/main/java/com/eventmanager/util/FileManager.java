package com.eventmanager.util;

import com.eventmanager.model.Event;
import com.eventmanager.model.User;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {
    private static final String EVENTS_FILE = "events.data";
    private static final String USERS_FILE = "users.data";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public void salvarEventos(List<Event> eventos) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(EVENTS_FILE))) {
            for (Event evento : eventos) {
                writer.println(String.format("%s|%s|%s|%s|%s|%s",
                    evento.getNome(),
                    evento.getEndereco(),
                    evento.getCategoria(),
                    evento.getHorario().format(formatter),
                    evento.getDescricao(),
                    String.join(",", evento.getParticipantes())
                ));
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar eventos: " + e.getMessage());
        }
    }

    public List<Event> carregarEventos() {
        List<Event> eventos = new ArrayList<>();
        File file = new File(EVENTS_FILE);

        if (!file.exists()) {
            return eventos;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split("\\|");
                if (dados.length >= 5) {
                    Event evento = new Event(
                        dados[0],
                        dados[1],
                        dados[2],
                        LocalDateTime.parse(dados[3], formatter),
                        dados[4]
                    );

                    if (dados.length > 5 && !dados[5].isEmpty()) {
                        List<String> participantes = Arrays.asList(dados[5].split(","));
                        evento.setParticipantes(new ArrayList<>(participantes));
                    }

                    eventos.add(evento);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar eventos: " + e.getMessage());
        }

        return eventos;
    }

    public void salvarUsuarios(List<User> usuarios) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USERS_FILE))) {
            for (User usuario : usuarios) {
                writer.println(String.format("%s|%s|%s|%s|%s|%s",
                    usuario.getNome(),
                    usuario.getEndereco(),
                    usuario.getCategoria(),
                    usuario.getHorario(),
                    usuario.getDescricao(),
                    String.join(",", usuario.getEventosParticipando())
                ));
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    public List<User> carregarUsuarios() {
        List<User> usuarios = new ArrayList<>();
        File file = new File(USERS_FILE);

        if (!file.exists()) {
            return usuarios;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split("\\|");
                if (dados.length >= 5) {
                    User usuario = new User(dados[0], dados[1], dados[2], dados[3], dados[4]);

                    if (dados.length > 5 && !dados[5].isEmpty()) {
                        List<String> eventos = Arrays.asList(dados[5].split(","));
                        usuario.setEventosParticipando(new ArrayList<>(eventos));
                    }

                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar usuários: " + e.getMessage());
        }

        return usuarios;
    }
}