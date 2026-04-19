package app;

import java.util.Scanner;

public class Main {

    private static Scanner scn = new Scanner(System.in);
    private static String usuarioAtual = null;

    public static void main(String[] args) {
        boolean rodando = true;

        while (rodando) {
            if (usuarioAtual == null) {
                rodando = menuDeslogado();
            } else {
                rodando = menuLogado();
            }
        }

        System.out.println("Encerrando o sistema...");
        scn.close();
    }

    //   ---- MENU DESLOGADO ----   \\

    private static boolean menuDeslogado() {
        System.out.println("\n    SIMULADOR SOLAR - UPX3    \n");
        System.out.println("Deslogado! \n");
        System.out.println("1 - Cadastrar Novo Usuário");
        System.out.println("2 - Login");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");

        int opcao = lerOpcao();

        switch (opcao) {
            case 1 -> cadastrar();
            case 2 -> login();
            case 0 -> { return false; }
            default -> System.out.println("Opção inválida!");
        }

        return true;
    }

    //   ---- MENU LOGADO ----   \\

    private static boolean menuLogado() {
        System.out.println("\n    Bem-vindo, " + usuarioAtual + "!    \n");
        System.out.println("Logado! \n");
        System.out.println("1 - Simular");
        System.out.println("2 - Ver Histórico");
        System.out.println("3 - Logout");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");

        int opcao = lerOpcao();

        switch (opcao) {
            case 1 -> simular();
            case 2 -> verHistorico();
            case 3 -> logout();
            case 0 -> { return false; }
            default -> System.out.println("Opção inválida!");
        }

        return true;
    }

    // ─── AÇÕES ────────────────────────────────────────────────────────────────

    private static void cadastrar() {
        System.out.println("[ Cadastro - a implementar ]");
    }

    private static void login() {
        System.out.println("[ Login - a implementar ]");
        usuarioAtual = "Celso"; // simulando login por enquanto
    }

    private static void logout() {
        System.out.println("Até logo, " + usuarioAtual + "!");
        usuarioAtual = null;
    }

    private static void simular() {
        System.out.println("[ Simulação - a implementar ]");
    }

    private static void verHistorico() {
        System.out.println("[ Histórico - a implementar ]");
    }

    // ─── UTILITÁRIO ───────────────────────────────────────────────────────────

    private static int lerOpcao() {
        if (scn.hasNextInt()) {
            int opcao = scn.nextInt();
            scn.nextLine();
            return opcao;
        }
        scn.nextLine(); // descarta entrada inválida
        return -1;
    }
}