package src.dao;

import conexao.Conexao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class EconomiaSolarDAO {

    // Salva um registro de economia solar no banco
    public boolean salvar(int idUsuario, double consumoKwh, double custoTradicional,
            double custoSolar, double economiaMensal, double economiaAnual) {
        String sql = "INSERT INTO ECONOMIA_SOLAR "
                + "(id_usuario, mes_referencia, consumo_kwh, custo_tradicional, custo_solar, economia_mensal, economia_anual) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.getConnection()) {
            if (con == null)
                return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idUsuario);
                ps.setDate(2, Date.valueOf(LocalDate.now().withDayOfMonth(1)));
                ps.setDouble(3, consumoKwh);
                ps.setDouble(4, custoTradicional);
                ps.setDouble(5, custoSolar);
                ps.setDouble(6, economiaMensal);
                ps.setDouble(7, economiaAnual);

                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar economia solar: " + e.getMessage());
        }
        return false;
    }

    // Busca o histórico de economia solar de um usuário e exibe no console
    public void exibirHistorico(int idUsuario) {
        String sql = "SELECT mes_referencia, consumo_kwh, custo_tradicional, custo_solar, economia_mensal, economia_anual "
                + "FROM ECONOMIA_SOLAR WHERE id_usuario = ? ORDER BY mes_referencia";

        try (Connection con = Conexao.getConnection()) {
            if (con == null)
                return;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idUsuario);

                try (ResultSet rs = ps.executeQuery()) {
                    System.out.println("\n --- Histórico de Economia Solar --- ");

                    boolean temRegistros = false;
                    while (rs.next()) {
                        temRegistros = true;
                        System.out.println("\nMês:                " + rs.getDate("mes_referencia"));
                        System.out.println("Consumo:            " + rs.getDouble("consumo_kwh") + " kWh");
                        System.out.println("Custo tradicional:  R$ " + rs.getDouble("custo_tradicional"));
                        System.out.println("Custo solar:        R$ " + rs.getDouble("custo_solar"));
                        System.out.println("Economia mensal:    R$ " + rs.getDouble("economia_mensal"));
                        System.out.println("Economia anual:     R$ " + rs.getDouble("economia_anual"));
                        System.out.println("-----------------------------------");
                    }

                    if (!temRegistros) {
                        System.out.println("Nenhuma simulação salva ainda.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar histórico: " + e.getMessage());
        }
    }
}