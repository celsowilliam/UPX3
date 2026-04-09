package src.modelo;

public class Usuario {
    private int idUsuario;
    private String nome;
    private String login; // Email
    private String senha;
    private String cidade;
    private double rendaFamiliar;

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
    public Usuario(int idUsuario, String nome, String login, String senha, String cidade, double rendaFamiliar) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.cidade = cidade;
        this.rendaFamiliar = rendaFamiliar;
    }

    public int getIdUsuario() { return idUsuario; }
    public String getNome() { return nome; }
    public String getLogin() { return login; }
    public String getSenha() { return senha; }
    public String getCidade() { return cidade; }
    public double getRendaFamiliar() { return rendaFamiliar; }


    // ............................................ \\


}