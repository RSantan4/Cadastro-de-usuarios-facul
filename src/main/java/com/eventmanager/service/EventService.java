package com.eventmanager.service;

import com.eventmanager.model.Event;
import com.eventmanager.util.FileManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventService {
    private List<Event> eventos;
    private FileManager fileManager;

    public EventService() {
        this.eventos = new ArrayList<>();
        this.fileManager = new FileManager();
        carregarEventos();
    }

    public void criarEvento(Event evento) {
        eventos.add(evento);
        salvarEventos();
        System.out.println("Evento criado com sucesso!");
    }

    public Event buscarEvento(String nome) {
        return eventos.stream()
                     .filter(e -> e.getNome().equalsIgnoreCase(nome))
                     .findFirst()
                     .orElse(null);
    }

    public List<Event> listarEventos() {
        return new ArrayList<>(eventos);
    }

    public List<Event> buscarEventosProximos() {
        return eventos.stream()
                     .filter(Event::isProximo)
                     .collect(Collectors.toList());
    }

    public List<Event> buscarEventosPorCategoria(String categoria) {
        return eventos.stream()
                     .filter(e -> e.getCategoria().equalsIgnoreCase(categoria))
                     .collect(Collectors.toList());
    }

    private void carregarEventos() {
        eventos = fileManager.carregarEventos();
    }

    private void salvarEventos() {
        fileManager.salvarEventos(eventos);
    }
}