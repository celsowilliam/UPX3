package src.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import src.dao.AparelhoDAO;
import src.dao.ConsumoDAO;
import src.dao.EconomiaSolarDAO;
import src.dao.UsuarioDAO;

public class Sistema {

    private Scanner scn = new Scanner(System.in);
    private Usuario usuarioAtual = null;

    private UsuarioDAO       usuarioDAO       = new UsuarioDAO();
    private AparelhoDAO      aparelhoDAO      = new AparelhoDAO();
    private ConsumoDAO       consumoDAO       = new ConsumoDAO();
    private EconomiaSolarDAO economiaSolarDAO = new EconomiaSolarDAO();

    // --- Iniciar sistema ---

    public void iniciar() {
        boolean rodando = true;

        while (rodando) {
            if (!isLogado()) {
                rodando = menuDeslogado();
            } else {
                System.out.println("\n --- Menu --- ");
                System.out.println("1 - Simular consumo");
                System.out.println("2 - Ver histórico de economia solar");
                System.out.println("3 - Revisar dados");
                System.out.println("9 - Logout");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = lerOpcao();
                System.out.println("");

                switch (opcao) {
                    case 1 -> simular();
                    case 2 -> economiaSolarDAO.exibirHistorico(usuarioAtual.getIdUsuario());
                    case 3 -> revisarDados();
                    case 9 -> logout();
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
            case 0 -> { return false; }
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
        String email = scn.nextLine().trim();

        System.out.print("Senha: ");
        String senha = scn.nextLine();

        System.out.println("\nSelecione seu DDD:");
        System.out.println("11-SP | 12-SJC | 13-Santos | 14-Bauru | 15-Sorocaba");
        System.out.println("16-Ribeirão | 17-SJRioPreto | 18-Pres.Prudente | 19-Campinas");
        System.out.print("DDD: ");
        String ddd = scn.nextLine().trim();

        if (!Usuario.dddValidoSP(ddd)) {
            System.out.println("DDD inválido!");
            cadastrar();
            return;
        }

        System.out.print("\nRenda familiar (R$): ");
        double renda = scn.nextDouble();
        scn.nextLine();

        System.out.print("Número de pessoas na residência: ");
        int pessoas = scn.nextInt();
        scn.nextLine();

        System.out.print("Tamanho do telhado da residência (m²): ");
        String telhado = scn.nextLine().trim();

        try {
            Usuario novo = new Usuario(nome, ddd, renda, pessoas, telhado, email, senha);
            int idGerado = usuarioDAO.cadastrarNovoUsuario(novo);

            if (idGerado != -1) {
                usuarioAtual = usuarioDAO.buscarPorId(idGerado);
                System.out.println("\nCadastro realizado! Bem-vindo, " + usuarioAtual.getNome() + "!");
            } else {
                System.out.println("Erro ao cadastrar. Email já cadastrado ou problema no banco.");
            }
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
            String email = scn.nextLine().trim();

            System.out.print("Senha: ");
            String senha = scn.nextLine();

            Usuario encontrado = usuarioDAO.fazerLogin(email, senha);

            if (encontrado != null) {
                usuarioAtual = encontrado;
                System.out.println("\nLogin realizado! Bem-vindo, " + usuarioAtual.getNome() + "!");
                return;
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

    // --- Revisar dados ---

    public void revisarDados() {
        System.out.println("\n --- Seus Dados --- ");
        System.out.println("Nome:               " + usuarioAtual.getNome());
        System.out.println("Email:              " + usuarioAtual.getEmail());
        System.out.println("DDD:                " + usuarioAtual.getDdd());
        System.out.printf ("Renda familiar:     R$ %.2f%n", usuarioAtual.getRendaFamiliar());
        System.out.println("Número de pessoas:  " + usuarioAtual.getNumeroPessoas());
        System.out.println("Tamanho do telhado: " + usuarioAtual.getTamanhoTelhado() + " m²");
    }

    // --- Simulação ---

    public void simular() {
        String[] nomesMeses = {
                "Janeiro", "Fevereiro", "Março", "Abril",
                "Maio", "Junho", "Julho", "Agosto",
                "Setembro", "Outubro", "Novembro", "Dezembro"
        };

        List<MesConta> meses = new ArrayList<>();

        System.out.println("\n --- Simulação de Consumo --- ");
        System.out.println("Digite -1 para encerrar\n");

        for (String nomeMes : nomesMeses) {
            MesConta mes = new MesConta(nomeMes);
            System.out.println(nomeMes + " -");

            double valorConta = 0;
            while (true) {
                System.out.print("Valor da conta (R$): ");
                if (scn.hasNextDouble()) {
                    valorConta = scn.nextDouble();
                    scn.nextLine();
                    if (valorConta == -1) {
                        System.out.println("Encerrando simulação...");
                        return;
                    }
                    if (valorConta >= 0) break;
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
                    if (consumoKwh > 0) break;
                    System.out.println("Consumo deve ser maior que zero!");
                } else {
                    System.out.println("Valor inválido!");
                    scn.nextLine();
                }
            }
            System.out.println("");

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