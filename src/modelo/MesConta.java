package src.modelo;

public class MesConta {

    private String nomeMes;
    private double valorConta;
    private double consumoKwh;

    public MesConta(String nomeMes) {
        this.nomeMes = nomeMes;
    }

    public void setDados(double valorConta, double consumoKwh) {
        this.valorConta = valorConta;
        this.consumoKwh = consumoKwh;
    }

    public double getConsumoKwh() {
        return consumoKwh;
    }

    public double getValorConta() {
        return valorConta;
    }

    public String getNomeMes() {
        return nomeMes;
    }
}