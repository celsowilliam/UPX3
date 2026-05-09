package src.dao;

import conexao.Conexao;
import src.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuarioDAO {

    // Faz o login do usuário, retornando um objeto Usuario completo se as credenciais estiverem corretas, ou null caso contrário
    public Usuario fazerLogin(String email, String senha) {
        String sql = "SELECT id_usuario, nome, ddd, renda_familiar, numero_pessoas, tamanho_telhado, email, senha FROM USUARIOS WHERE email = ? AND senha = ?";
        Usuario usuario = null;

        try (Connection con = Conexao.getConnection()) {
            if (con == null)
                return null;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, senha);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        usuario = new Usuario(
                                rs.getInt("id_usuario"),
                                rs.getString("nome"),
                                rs.getString("ddd"),
                                rs.getDouble("renda_familiar"),
                                rs.getInt("numero_pessoas"),
                                rs.getString("tamanho_telhado"),
                                rs.getString("email"),
                                rs.getString("senha"));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao fazer login: " + e.getMessage());
        }
        return usuario;
    }

    // Busca um usuário pelo ID
    public Usuario buscarPorId(int idUsuario) {
        String sql = "SELECT id_usuario, nome, ddd, renda_familiar, numero_pessoas, tamanho_telhado, email, senha "
                + "FROM USUARIOS WHERE id_usuario = ?";
        Usuario usuario = null;

        try (Connection con = Conexao.getConnection()) {
            if (con == null)
                return null;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idUsuario);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        usuario = new Usuario(
                                rs.getInt("id_usuario"),
                                rs.getString("nome"),
                                rs.getString("ddd"),
                                rs.getDouble("renda_familiar"),
                                rs.getInt("numero_pessoas"),
                                rs.getString("tamanho_telhado"),
                                rs.getString("email"),
                                rs.getString("senha"));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return usuario;
    }

    // Verifica se o email já está cadastrado
    public boolean emailJaCadastrado(String email) {
        String sql = "SELECT COUNT(*) FROM USUARIOS WHERE email = ?";

        try (Connection con = Conexao.getConnection()) {
            if (con == null)
                return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next())
                        return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao verificar email: " + e.getMessage());
        }
        return false;
    }

    // Cadastra um novo usuário e retorna o ID gerado, ou -1 em caso de erro
    public int cadastrarNovoUsuario(Usuario novoUsuario) {

        if (emailJaCadastrado(novoUsuario.getEmail())) {
            System.out.println("ERRO: O email '" + novoUsuario.getEmail() + "' já está cadastrado.");
            return -1;
        }

        String sql = "INSERT INTO USUARIOS (nome, ddd, renda_familiar, numero_pessoas, tamanho_telhado, email, senha) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.getConnection()) {
            if (con == null)
                return -1;

            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, novoUsuario.getNome());
                ps.setString(2, novoUsuario.getDdd());
                ps.setDouble(3, novoUsuario.getRendaFamiliar());
                ps.setInt(4, novoUsuario.getNumeroPessoas());
                ps.setString(5, novoUsuario.getTamanhoTelhado());
                ps.setString(6, novoUsuario.getEmail());
                ps.setString(7, novoUsuario.getSenha());

                int linhasAfetadas = ps.executeUpdate();

                if (linhasAfetadas > 0) {
                    try (ResultSet chaves = ps.getGeneratedKeys()) {
                        if (chaves.next())
                            return chaves.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
        return -1;
    }

}