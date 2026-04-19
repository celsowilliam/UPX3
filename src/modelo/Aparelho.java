package modelo;
import modelo.Consumo;

// ALINHADO COM BANCO DE DADOS DO CELSO KKKK 19/04

public class Aparelho {
    private String nome;
    private double potenciaWatts;
    private int quantidade;
    private double horasUsoDiario;
    private int diasUsoMes;
    private int mes;

    public Aparelho(String nome, double potenciaWatts, int quantidade, double horasUsoDiario, int diasUsoMes) {
        this.nome = nome;
        this.potenciaWatts = potenciaWatts;
        this.quantidade = quantidade;
        this.horasUsoDiario = horasUsoDiario;
        this.diasUsoMes = diasUsoMes;
        this.mes = mes;
    }

    public double getConsumoKwhMes() {
        // Cálculo: (Watts * Horas * Dias * Qtd) / 1000
        return (potenciaWatts * horasUsoDiario * diasUsoMes * quantidade) / 1000;
    } // OK BANCO

    public double getCustoIndividual(double precoKwh) {
        return getConsumoKwhMes() * precoKwh;
    }

    public String getNome() { return nome; }
    public double getPotenciaWatts() { return potenciaWatts; }
    public int getQuantidade() { return quantidade; }
    public int getMes() { return mes; }
}