package src.dao;

import conexao.Conexao;
import src.modelo.Aparelho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AparelhoDAO {

    // Salva um aparelho no banco e retorna o ID gerado, ou -1 em caso de erro
    public int salvar(Aparelho aparelho, int idUsuario) {
        String sql = "INSERT INTO APARELHOS (id_usuario, nome_aparelho, potencia_watts, horas_uso_diario) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection con = Conexao.getConnection()) {
            if (con == null) return -1;

            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt   (1, idUsuario);
                ps.setString(2, aparelho.getNome());
                ps.setDouble(3, aparelho.getPotenciaWatts());
                ps.setDouble(4, aparelho.getHorasUsoDiario());

                int linhasAfetadas = ps.executeUpdate();

                if (linhasAfetadas > 0) {
                    try (ResultSet chaves = ps.getGeneratedKeys()) {
                        if (chaves.next()) return chaves.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar aparelho: " + e.getMessage());
        }
        return -1;
    }

    // Busca todos os aparelhos de um usuário
    public List<Aparelho> buscarPorUsuario(int idUsuario) {
        String sql = "SELECT nome_aparelho, potencia_watts, horas_uso_diario "
                   + "FROM APARELHOS WHERE id_usuario = ?";

        List<Aparelho> lista = new ArrayList<>();

        try (Connection con = Conexao.getConnection()) {
            if (con == null) return lista;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idUsuario);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Aparelho aparelho = new Aparelho(
                            rs.getString("nome_aparelho"),
                            rs.getDouble("potencia_watts"),
                            1,
                            rs.getDouble("horas_uso_diario"),
                            30
                        );
                        lista.add(aparelho);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar aparelhos: " + e.getMessage());
        }
        return lista;
    }
}