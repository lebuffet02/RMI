import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ProcessoAdministracao extends UnicastRemoteObject implements Agencia {

    private double saldo;
    private Conta conta;

    protected ProcessoAdministracao() throws RemoteException {}

    protected ProcessoAdministracao(int port, double saldo, Conta conta) throws RemoteException {
        super(port);
        this.saldo = saldo;
        this.conta = conta;

    }

    @Override
    public Conta abrirConta(String nomeConta, int idConta) throws RemoteException {
        //validar para ver se n existe uma conta que já está aberta
        return new Conta(nomeConta, idConta);
    }

    @Override
    public void fecharConta() throws RemoteException {
        if(conta != null) {
            conta = null;
        }
    }

    @Override
    public void verificarConta(Conta conta) throws RemoteException {

    }

    @Override
    public void sacar(double saldo) throws RemoteException {
        if(this.saldo > 0.0) {
            this.saldo -= saldo;
            System.out.println("Saldo Atual: " + this.saldo);
        } else {
            throw new IllegalArgumentException("Saldo Insuficiente!");
        }
    }

    @Override
    public void depositar(double saldo) throws RemoteException {
        this.saldo += saldo;
        System.out.println("Saldo Atual: " + this.saldo);

    }


}
