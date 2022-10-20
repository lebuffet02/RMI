import java.util.Random;

public class Conta {

    private String nomeConta;
    private int idConta;
    private Random random;

    public Conta(String nomeConta, int idConta) {
        if(!nomeConta.isEmpty()) {
            this.nomeConta = nomeConta;
        }
        this.idConta = idConta;
    }

    public void setIdConta(int idConta) {
        if(idConta == this.idConta) {
            System.out.println("Conta de número " + idConta + " já existente!");
            System.out.println("Consulte a nova conta gerada.");
            this.idConta = random.nextInt(999);
        }
        this.idConta = idConta;
    }

    public int getIdConta() {
        return idConta;
    }

    public int consultarNovaConta(int contaAntiga) {
        return contaAntiga = this.idConta;
    }

}
