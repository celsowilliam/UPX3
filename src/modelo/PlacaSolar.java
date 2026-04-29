package src.modelo;

public class PlacaSolar {

    private static final double eficienciaSistema = 0.75;
    private static final double eficienciaPainel = 0.20;
    private static final double areaPainel = 1.6;
    private static final double custoWp = 4.50;
    private static final double wattsPorPainel = 400.0;

    // Retorna irradiação solar anual em kWh/m²/ano por DDD
    public static double getIrradiacao(int ddd) {
        switch (ddd) {
            case 11: return 1500;
            case 12: return 1550;
            case 13: return 1600;
            case 14: return 1600;
            case 15: return 1550;
            case 16: return 1650;
            case 17: return 1650;
            case 18: return 1600;
            case 19: return 1550;
            default: return 0;
        }
    }

    // kWh gerado por m² de painel por ano
    public static double getGeracaoPorM2Ano(int ddd) {
        return getIrradiacao(ddd) * eficienciaSistema * eficienciaPainel;
    }

    // kWh gerado por painel por ano
    public static double getGeracaoPorPainelAno(int ddd) {
        return getGeracaoPorM2Ano(ddd) * areaPainel;
    }

    // kWh gerado por painel por mês
    public static double getGeracaoPorPainelMes(int ddd) {
        return getGeracaoPorPainelAno(ddd) / 12;
    } 

    // m² mínimo para cobrir 100% do consumo anual
    public static double getM2Necessarios(double consumoAnualKwh, int ddd) {
        double geracaoPorM2 = getGeracaoPorM2Ano(ddd);
        if (geracaoPorM2 == 0) return 0;
        return consumoAnualKwh / geracaoPorM2;
    }

    // Número de painéis que cabem no m² disponível do telhado
    public static int getPaineisPorM2(double m2Disponivel) {
        return (int) Math.floor(m2Disponivel / areaPainel);
    }

    // Número mínimo de painéis para cobrir 100% do consumo
    public static int getPaineisNecessarios(double consumoAnualKwh, int ddd) {
        double m2 = getM2Necessarios(consumoAnualKwh, ddd);
        return (int) Math.ceil(m2 / areaPainel);
    }

    // kWh total gerado pelos painéis escolhidos por ano
    public static double getGeracaoTotalAno(int numPaineis, int ddd) {
        return numPaineis * getGeracaoPorPainelAno(ddd);
    }

    // kWh total gerado pelos painéis escolhidos por mês
    public static double getGeracaoTotalMes(int numPaineis, int ddd) {
        return getGeracaoTotalAno(numPaineis, ddd) / 12;
    }

    // Retorna custo do inversor baseado na potência total do sistema
    public static double getCustoInversor(int numPaineis) {
        double potenciaKwp = numPaineis * wattsPorPainel / 1000.0;
        if (potenciaKwp <= 2.0) return 2000.0;
        if (potenciaKwp <= 5.0) return 3000.0;
        return 4500.0;
    }

    // Retorna investimento total: painéis + inversor instalados
    public static double getInvestimentoTotal(int numPaineis) {
        double custoPaineis = numPaineis * wattsPorPainel * custoWp;
        return custoPaineis + getCustoInversor(numPaineis);
    }

    public static double getAreaPainel() { return areaPainel; }
    public static double getWattsPorPainel() { return wattsPorPainel; }
}