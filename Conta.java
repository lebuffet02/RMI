import java.util.Random;
import java.util.StringJoiner;

public class Conta {

    private double saldo;
    private int idConta;
    private double limite;

    public Conta(int idConta) {
        this.saldo = 0.0;
        this.idConta = idConta;
        this.limite = 1000.0;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getLimite() {
        return this.limite;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "saldo=" + saldo +
                ", idConta='" + idConta + '\'' +
                ", limite=" + limite +
                '}';
    }
}
