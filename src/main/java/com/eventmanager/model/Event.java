package com.eventmanager.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private String nome;
    private String endereco;
    private String categoria;
    private LocalDateTime horario;
    private String descricao;
    private List<String> participantes;

    public Event(String nome, String endereco, String categoria, LocalDateTime horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
        this.participantes = new ArrayList<>();
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public LocalDateTime getHorario() { return horario; }
    public void setHorario(LocalDateTime horario) { this.horario = horario; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public List<String> getParticipantes() { return participantes; }
    public void setParticipantes(List<String> participantes) { this.participantes = participantes; }

    public void adicionarParticipante(String nomeUsuario) {
        if (!participantes.contains(nomeUsuario)) {
            participantes.add(nomeUsuario);
        }
    }

    public void removerParticipante(String nomeUsuario) {
        participantes.remove(nomeUsuario);
    }

    public boolean isProximo() {
        return horario.isAfter(LocalDateTime.now()) && 
               horario.isBefore(LocalDateTime.now().plusDays(7));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("Evento: %s | Endereço: %s | Categoria: %s | Horário: %s | Descrição: %s | Participantes: %d", 
                           nome, endereco, categoria, horario.format(formatter), descricao, participantes.size());
    }
}