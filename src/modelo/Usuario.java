package src.modelo;

import java.util.HashSet;
import java.util.Set;

public class Usuario {
    private int idUsuario;
    private String nome;
    private String login; // Email
    private String senha;
    private String cidade;
    private String ddd; //DDD SP

    //Conjunto de DDDs válidos para SP, para validação no cadastro/login
    private static Set<String> DDD_SP = new HashSet<>(); // Para evitar logins duplicados

    static {
        DDD_SP.add("11");
        DDD_SP.add("12");
        DDD_SP.add("13");
        DDD_SP.add("14");
        DDD_SP.add("15");
        DDD_SP.add("16");
        DDD_SP.add("17");
        DDD_SP.add("18");
        DDD_SP.add("19");
    }

    //    Novo Cadastro
    public Usuario(String nome_usuario, String login, String senha) {
        if (nome_usuario == null || nome_usuario.trim().isEmpty() || login == null || login.trim().isEmpty() || senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome, Login e Senha são obrigatórios.");
        }

        this.nome = nome_usuario;
        this.login = login;
        this.senha = senha;
    }

    //   Login / Banco de Dados
    public Usuario(int idUsuario, String nome, String login, String senha, String cidade, String ddd) {

        validarNome(nome);
        validarEmail(login);
        validarSenha(senha);
        validarCidade(cidade);

        if (!dddValidoSP(ddd)) {
            throw new IllegalArgumentException("DDD inválido para São Paulo. Deve ser entre 11 e 19.");
        }

        this.idUsuario = idUsuario;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.cidade = cidade;
        this.ddd = ddd;
    }
    
    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
    }

    private void validarEmail(String email) {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Email inválido.");
        }
    }

    private void validarSenha(String senha) {
        if (senha == null || senha.length() < 8) {
            throw new IllegalArgumentException("Senha deve conter pelo menos 8 caracteres.");
        }

        if (!senha.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos uma letra maiúscula.");
        }

        if (!senha.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos um número.");
        }
    }

    private void validarCidade(String cidade) {
        if (cidade == null || cidade.trim().isEmpty()) {
            throw new IllegalArgumentException("Cidade é obrigatória.");
        }
    }
    
    private boolean dddValidoSP(String ddd) {
        return DDD_SP.contains(ddd);
    }

    public int getIdUsuario() { return idUsuario; }
    public String getNome() { return nome; }
    public String getLogin() { return login; }
    public String getSenha() { return senha; }
    public String getCidade() { return cidade; }
    public String getDdd() { return ddd; }

    // ............................................ \\
// teste git

}