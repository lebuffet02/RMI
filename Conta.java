import java.util.Random;
import java.util.StringJoiner;

public class Conta {

    private double saldo;
    private String cpf;
    private String idConta;
    private double limite;

    public Conta(double saldo, String cpf, String idConta, double limite) {
        if(!cpf.isEmpty() && idConta != null && !idConta.isEmpty()) {
            setIdConta(idConta);
        }
        this.saldo = 0.0;
        this.cpf = cpf;
        this.idConta = idConta;
        this.limite = limite;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setIdConta(String idConta) {
        this.idConta = idConta;
    }

    public String getIdConta() {
        return idConta;
    }

    public void setCpf(String numero) {
        this.cpf = numero;
    }

    public String getCpf() {
        return cpf;
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
                ", cpf='" + cpf + '\'' +
                ", idConta='" + idConta + '\'' +
                ", limite=" + limite +
                '}';
    }
}
