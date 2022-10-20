import java.util.Random;

public class Conta {

    private String nomeConta;
    private String idConta;
    private Random random;

    public Conta(String nomeConta, String idConta) {
        if(!nomeConta.isEmpty() && idConta != null && !idConta.isEmpty()) {
            if(this.idConta.trim().equals(idConta)) {
                setIdConta(idConta);
            }
            this.nomeConta = nomeConta;
            this.idConta = idConta;
        }
    }

    public void setIdConta(String idConta) {
        if(idConta.equals(this.idConta)) {
            System.out.println("Conta de número " + idConta + " já existente!");
            System.out.println("Consulte a nova conta gerada.");
            this.idConta = Integer.toString(random.nextInt(999));
        }
        this.idConta = idConta;
    }

    public String getIdConta() {
        return idConta;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }

    public String getNomeConta() {
        return nomeConta;
    }

    public String consultarNovaConta(String contaAntiga) {
        return contaAntiga = this.idConta;
    }
}
