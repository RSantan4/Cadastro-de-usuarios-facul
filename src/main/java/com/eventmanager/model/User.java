package com.eventmanager.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String nome;
    private String endereco;
    private String categoria;
    private String horario;
    private String descricao;
    private List<String> eventosParticipando;

    public User(String nome, String endereco, String categoria, String horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
        this.eventosParticipando = new ArrayList<>();
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public List<String> getEventosParticipando() { return eventosParticipando; }
    public void setEventosParticipando(List<String> eventosParticipando) { 
        this.eventosParticipando = eventosParticipando; 
    }

    public void adicionarEvento(String nomeEvento) {
        if (!eventosParticipando.contains(nomeEvento)) {
            eventosParticipando.add(nomeEvento);
        }
    }

    public void removerEvento(String nomeEvento) {
        eventosParticipando.remove(nomeEvento);
    }

    @Override
    public String toString() {
        return String.format("Usuário: %s | Endereço: %s | Categoria: %s | Horário: %s | Descrição: %s", 
                           nome, endereco, categoria, horario, descricao);
    }
}