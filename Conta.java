import java.util.Random;
import java.util.StringJoiner;

public class Conta {

    private String numero;
    private String idConta;
    private double limite;

    public Conta(String numero, String idConta, double limite) {
        if(!numero.isEmpty() && idConta != null && !idConta.isEmpty()) {
            if(this.idConta.trim().equals(idConta)) {
                setIdConta(idConta);
            }
            this.numero = numero;
            this.idConta = idConta;
            this.limite = limite;
        }
    }

    public void setIdConta(String idConta) {
        if(idConta.equals(this.idConta)) {
            System.out.println("Conta de número " + idConta + " já existente!");
            System.out.println("Consulte a nova conta gerada.");
            this.idConta = String.valueOf((10 + (new Random().nextInt(999))));
        }
        this.idConta = idConta;
    }

    public String getIdConta() {
        return idConta;
    }

    public void setNumeroConta(String numero) {
        //int converter = Integer.parseInt(numero);
//        StringJoiner concat = new StringJoiner(".");
//        if (!numero.isEmpty()) {
//            for (int i = 0; i < numero.trim().length(); i++) {
//                this.numero = String.valueOf(numero[i])
//            }
//        }
        this.numero = numero;
    }

    public String getNomeConta() {
        return numero;
    }

    public String consultarNovaConta(String contaAntiga) {
        return contaAntiga = this.idConta;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getLimite() {
        return this.limite;
    }
}
