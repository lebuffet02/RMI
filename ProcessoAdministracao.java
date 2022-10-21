import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProcessoAdministracao extends UnicastRemoteObject implements Agencia {

    private double saldo;
    private List<Conta> contas;

    protected ProcessoAdministracao() throws RemoteException {}

    protected ProcessoAdministracao(int port, double saldo, Conta conta) throws RemoteException {
        super(port);
        this.saldo = saldo;
        this.contas = new ArrayList<>();
    }

    @Override
    public Conta abrirConta(Conta conta, String idConta) throws RemoteException {
        //validar para ver se n existe uma conta que já está aberta
        //return new Conta(nomeConta, idConta);
        if (!conta.getIdConta().equals(idConta)) {
            this.contas.add(conta);
            return (Conta) this.contas;
        }
        return null;
    }

    @Override
    public List<Conta> verificarContas() throws RemoteException {
        return contas;
    }

    @Override
    public List<Conta> fecharConta(String idConta) throws RemoteException {
        for (Conta c : this.contas) {
            if(idConta != null && !idConta.isEmpty()) {
                if (c.getIdConta().equals(idConta)) {
                    this.contas.remove(c);
                    System.out.println("Conta Fechada!");
                    return contas;
                }
            }
        }
        return contas;
    }

    @Override
    public void sacar(double saldo, double limite) throws RemoteException {
        if(this.saldo > 0.0 && this.saldo >= limite) {
            this.saldo -= saldo;
            System.out.println("Saldo Atual: " + this.saldo);
        } else {
            throw new IllegalArgumentException("Saldo Insuficiente!" + this.saldo + " : é o saldo atual!");
        }
    }

    @Override
    public void depositar(double saldo) throws RemoteException {
        this.saldo += saldo;
        System.out.println("Saldo Atual: " + this.saldo);
    }
}
