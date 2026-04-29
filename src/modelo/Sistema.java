package src.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {

    private Scanner scn = new Scanner(System.in);
    private Usuario usuarioAtual = null;
    private List<Usuario> usuarios = new ArrayList<>();

    private static final double custoWp = 4.50;
    private static final double wattsPorPainel = 400.0;

    // --- INICIAR SISTEMA ---
    public void iniciar() {
        boolean rodando = true;

        while (rodando) {
            if (!isLogado()) {
                rodando = menuDeslogado();
            } else {
                System.out.println("\n --- Menu --- ");
                System.out.println("1 - Simular consumo");
                System.out.println("2 - Logout");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = lerOpcao();

                switch (opcao) {
                    case 1 -> simular();
                    case 2 -> logout();
                    case 0 -> rodando = false;
                    default -> System.out.println("Opção inválida.");
                }
            }
        }

        System.out.println("Encerrando sistema...");
    }

    // --- Menu Deslogado ---
    public boolean menuDeslogado() {
        System.out.println("\n Simulador Solar - UPX3 \n");
        System.out.println("Deslogado! \n");
        System.out.println("1 - Cadastrar novo usuário");
        System.out.println("2 - Fazer login");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");

        int opcao = lerOpcao();

        switch (opcao) {
            case 1 -> cadastrar();
            case 2 -> login();
            case 0 -> {
                return false;
            }
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
        return true;
    }

    // --- Cadastro ---
    public void cadastrar() {
        System.out.println("\n --- Cadastro --- ");

        System.out.print("Nome: ");
        String nome = scn.nextLine().trim();

        System.out.print("Email: ");
        String login = scn.nextLine().trim();

        System.out.print("Senha: ");
        String senha = scn.nextLine();

        try {
            Usuario novo = new Usuario(nome, login, senha);
            usuarios.add(novo);
            usuarioAtual = novo;
            System.out.println("\nCadastro realizado! Bem-vindo, " + novo.getNome() + "!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
            cadastrar();
        }
    }

    // --- Login ---
    public void login() {
        System.out.println("\n --- Login --- ");

        int tentativas = 0;

        while (tentativas < 3) {
            System.out.print("Email: ");
            String login = scn.nextLine().trim();

            System.out.print("Senha: ");
            String senha = scn.nextLine();

            for (Usuario u : usuarios) {
                if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                    usuarioAtual = u;
                    System.out.println("\nLogin realizado! Bem-vindo, " + u.getNome() + "!");
                    return;
                }
            }

            tentativas++;
            int restantes = 3 - tentativas;
            if (restantes > 0) {
                System.out.println("Email ou senha incorretos. Tentativas restantes: " + restantes);
            }
        }
        System.out.println("Muitas tentativas incorretas. Voltando ao menu...");
    }

    // --- Logout ---
    public void logout() {
        System.out.println("Até logo, " + usuarioAtual.getNome() + "!");
        usuarioAtual = null;
    }

    // --- Simulação ---
    public void simular() {
        String[] nomesMeses = {
                "Janeiro", "Fevereiro", "Março", "Abril",
                "Maio", "Junho", "Julho", "Agosto",
                "Setembro", "Outubro", "Novembro", "Dezembro"
        };

        List<MesConta> meses = new ArrayList<>();

        for (String nomeMes : nomesMeses) {
            MesConta mes = new MesConta(nomeMes);

            System.out.println(nomeMes + " - ");

            double valorConta = 0;
            while (true) {
                System.out.print("Valor da conta (R$): ");
                if (scn.hasNextDouble()) {
                    valorConta = scn.nextDouble();
                    scn.nextLine();
                    if (valorConta >= 0)
                        break;
                    System.out.println("Valor não pode ser negativo!");
                } else {
                    System.out.println("Valor inválido!");
                    scn.nextLine();
                }
            }

            double consumoKwh = 0;
            while (true) {
                System.out.print("Consumo (kWh): ");
                if (scn.hasNextDouble()) {
                    consumoKwh = scn.nextDouble();
                    scn.nextLine();
                    if (consumoKwh > 0)
                        break;
                    System.out.println("Consumo deve ser maior que zero!");
                } else {
                    System.out.println("Valor inválido!");
                    scn.nextLine();
                }
            }

            mes.setDados(valorConta, consumoKwh);
            meses.add(mes);
        }
    }

    public boolean isLogado() {
        return usuarioAtual != null;
    }

    // --- Leitura de opção ---
    private int lerOpcao() {
        if (scn.hasNextInt()) {
            int opcao = scn.nextInt();
            scn.nextLine();
            return opcao;
        }
        scn.nextLine();
        return -1;
    }
}