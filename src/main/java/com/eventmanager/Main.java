package com.eventmanager;

import com.eventmanager.controller.EventController;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EventController controller = new EventController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SISTEMA DE GERENCIAMENTO DE EVENTOS ===");

        while (true) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Criar evento");
            System.out.println("3. Listar eventos");
            System.out.println("4. Participar de evento");
            System.out.println("5. Cancelar participação");
            System.out.println("6. Visualizar meus eventos");
            System.out.println("7. Eventos próximos");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1:
                    controller.cadastrarUsuario();
                    break;
                case 2:
                    controller.criarEvento();
                    break;
                case 3:
                    controller.listarEventos();
                    break;
                case 4:
                    controller.participarEvento();
                    break;
                case 5:
                    controller.cancelarParticipacao();
                    break;
                case 6:
                    controller.visualizarMeusEventos();
                    break;
                case 7:
                    controller.eventosProximos();
                    break;
                case 8:
                    System.out.println("Encerrando sistema...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}