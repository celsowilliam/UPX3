package src.modelo;

import java.util.HashSet;
import java.util.Set;

public class Usuario {
    private int idUsuario;
    private String nome;
    private String email;
    private String senha;
    private String ddd;
    private double rendaFamiliar;
    private int numeroPessoas;
    private String tipoTelhado;

    private static Set<String> DDD_SP = new HashSet<>();

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

    // Cadastro inicial
    public Usuario(String nome, String email, String senha) {
        validarNome(nome);
        validarEmail(email);
        validarSenha(senha);

        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Leitura do banco
    public Usuario(int idUsuario, String nome, String ddd, double rendaFamiliar, int numeroPessoas,
            String tipoTelhado, String email, String senha) {
        validarNome(nome);
        validarDdd(ddd);
        validarRendaFamiliar(rendaFamiliar);
        validarNumeroPessoas(numeroPessoas);
        validarTipoTelhado(tipoTelhado);
        validarEmail(email);
        validarSenha(senha);

        this.idUsuario = idUsuario;
        this.nome = nome;
        this.ddd = ddd;
        this.rendaFamiliar = rendaFamiliar;
        this.numeroPessoas = numeroPessoas;
        this.tipoTelhado = tipoTelhado;
        this.email = email;
        this.senha = senha;
    }

    // Inserção nova com todos os dados
    public Usuario(String nome, String ddd, double rendaFamiliar, int numeroPessoas, String tipoTelhado,
            String email, String senha) {
        validarNome(nome);
        validarDdd(ddd);
        validarRendaFamiliar(rendaFamiliar);
        validarNumeroPessoas(numeroPessoas);
        validarTipoTelhado(tipoTelhado);
        validarEmail(email);
        validarSenha(senha);

        this.nome = nome;
        this.ddd = ddd;
        this.rendaFamiliar = rendaFamiliar;
        this.numeroPessoas = numeroPessoas;
        this.tipoTelhado = tipoTelhado;
        this.email = email;
        this.senha = senha;
    }

    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Nome é obrigatório.");
    }

    private void validarEmail(String email) {
        if (email == null || !email.contains("@") || !email.contains("."))
            throw new IllegalArgumentException("Email inválido.");
    }

    private void validarSenha(String senha) {
        if (senha == null || senha.length() < 8)
            throw new IllegalArgumentException("Senha deve ter pelo menos 8 caracteres.");
        if (!senha.matches(".*[A-Z].*"))
            throw new IllegalArgumentException("Senha deve ter pelo menos uma letra maiúscula.");
        if (!senha.matches(".*[0-9].*"))
            throw new IllegalArgumentException("Senha deve ter pelo menos um número.");
    }

    private void validarDdd(String ddd) {
        if (!dddValidoSP(ddd))
            throw new IllegalArgumentException("DDD inválido. Informe um DDD de SP (11 a 19).");
    }

    private void validarRendaFamiliar(double renda) {
        if (renda < 0)
            throw new IllegalArgumentException("Renda familiar não pode ser negativa.");
    }

    private void validarNumeroPessoas(int pessoas) {
        if (pessoas <= 0)
            throw new IllegalArgumentException("Número de pessoas deve ser maior que zero.");
    }

    private void validarTipoTelhado(String tipo) {
        if (tipo == null || tipo.trim().isEmpty())
            throw new IllegalArgumentException("Tipo de telhado é obrigatório.");
    }

    public static boolean dddValidoSP(String ddd) {
        return DDD_SP.contains(ddd);
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getDdd() {
        return ddd;
    }

    public double getRendaFamiliar() {
        return rendaFamiliar;
    }

    public int getNumeroPessoas() {
        return numeroPessoas;
    }

    public String getTamanhoTelhado() {
        return tipoTelhado;
    }

    public void setDdd(String ddd) {
        validarDdd(ddd);
        this.ddd = ddd;
    }

    public void setRendaFamiliar(double rendaFamiliar) {
        validarRendaFamiliar(rendaFamiliar);
        this.rendaFamiliar = rendaFamiliar;
    }

    public void setNumeroPessoas(int numeroPessoas) {
        validarNumeroPessoas(numeroPessoas);
        this.numeroPessoas = numeroPessoas;
    }

    public void setTipoTelhado(String tipoTelhado) {
        validarTipoTelhado(tipoTelhado);
        this.tipoTelhado = tipoTelhado;
    }

    public void setNome(String nome) {
        validarNome(nome);
        this.nome = nome;
    }

    public void setEmail(String email) {
        validarEmail(email);
        this.email = email;
    }

    public void setSenha(String senha) {
        validarSenha(senha);
        this.senha = senha;
    }
}