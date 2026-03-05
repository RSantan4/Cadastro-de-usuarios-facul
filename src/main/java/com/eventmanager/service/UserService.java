package com.eventmanager.service;

import com.eventmanager.model.User;
import com.eventmanager.util.FileManager;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> usuarios;
    private FileManager fileManager;

    public UserService() {
        this.usuarios = new ArrayList<>();
        this.fileManager = new FileManager();
        carregarUsuarios();
    }

    public void cadastrarUsuario(User usuario) {
        usuarios.add(usuario);
        salvarUsuarios();
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public User buscarUsuario(String nome) {
        return usuarios.stream()
                      .filter(u -> u.getNome().equalsIgnoreCase(nome))
                      .findFirst()
                      .orElse(null);
    }

    public List<User> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }

    private void carregarUsuarios() {
        // Implementar carregamento do arquivo
        usuarios = fileManager.carregarUsuarios();
    }

    private void salvarUsuarios() {
        fileManager.salvarUsuarios(usuarios);
    }
}