package src.modelo;

import java.util.ArrayList;
import java.util.List;

public class Simulacao {
    private List<Aparelho> inventario;
    private double valorKwh;
    private int idUsuario;
    private final double taxa_minima_solar = 60.00; // Valor fixo que pagará com solar

    public Simulacao(int idUsuario, double precoKwhRegiao) {
        this.idUsuario = idUsuario;
        this.valorKwh = precoKwhRegiao;
        this.inventario = new ArrayList<>();
    }

    public void adicionarAparelho(Aparelho a) {
        this.inventario.add(a);
    }


    // --- Calculos sem energia Solar ---

    public double getConsumoTotalKwh() {
        double total = 0;
        for (Aparelho a : inventario) {
            total += a.getConsumoKwhMes();
        }
        return total;
    }
    public double getValorContaSemSolar() {
        return getConsumoTotalKwh() * valorKwh;
    }


    // -- Calculos com energia Solar ---

    public double getValorContaComSolar() {
        // Gasta o mesmo kWh, mas só paga a taxa
        return taxa_minima_solar;
    }

    public double getEconomiaMensal() {
        return getValorContaSemSolar() - taxa_minima_solar;
    }

    // --- Investimento e Retorno ---
    public double estimarInvestimento() {
        double consumo = getConsumoTotalKwh();
        if (consumo <= 200) return 8000.00;   
        if (consumo <= 500) return 15000.00;  
        return 25000.00;                      
    }

    public String calcularTempoRetorno() {
        double investimento = estimarInvestimento();
        double economia = getEconomiaMensal();

        if (economia <= 0) return "N/A (Consumo muito baixo)";

        int totalMeses = (int) Math.ceil(investimento / economia);
        int anos = totalMeses / 12;
        int mesesRestantes = totalMeses % 12;

        return anos + " anos e " + mesesRestantes + " meses";
    }

    public double getCo2EvitadoAno() {
        return (getConsumoTotalKwh() * 0.084) * 12;
    }

    public int getIdUsuario() { return idUsuario; }
    public List<Aparelho> getInventario() { return inventario; }
}