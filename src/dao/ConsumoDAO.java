package src.dao;

import conexao.Conexao;
import src.modelo.MesConta;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ConsumoDAO {

    // Salva um MesConta no banco para o usuário informado
    public boolean salvar(MesConta mes, int idUsuario, double precoKwh) {
        String sql = "INSERT INTO CONSUMO (id_usuario, mes_referencia, consumo_kwh, custo_reais) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = Conexao.getConnection()) {
            if (con == null)
                return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idUsuario);
                ps.setDate(2, Date.valueOf(LocalDate.now().withDayOfMonth(1)));
                ps.setDouble(3, mes.getConsumoKwh());
                ps.setDouble(4, mes.getValorConta());

                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar consumo: " + e.getMessage());
        }
        return false;
    }

    // Busca todos os registros de consumo de um usuário
    public List<MesConta> buscarPorUsuario(int idUsuario) {
        String sql = "SELECT mes_referencia, consumo_kwh, custo_reais FROM CONSUMO WHERE id_usuario = ? ORDER BY mes_referencia";

        List<MesConta> lista = new ArrayList<>();

        try (Connection con = Conexao.getConnection()) {
            if (con == null)
                return lista;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idUsuario);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String nomeMes = rs.getDate("mes_referencia")
                                .toLocalDate()
                                .getMonth()
                                .getDisplayName(
                                        java.time.format.TextStyle.FULL,
                                        Locale.of("pt", "BR"));

                        MesConta mes = new MesConta(nomeMes);

                        mes.setDados(
                                rs.getDouble("custo_reais"),
                                rs.getDouble("consumo_kwh"));

                        lista.add(mes);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar consumo: " + e.getMessage());
        }
        return lista;
    }
}