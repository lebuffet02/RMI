import java.rmi.RemoteException;
import java.util.List;

public interface Agencia {

    boolean abrirConta(Conta conta) throws RemoteException;
    List<Conta> fecharConta(Conta conta) throws RemoteException;
    List<Conta> verificarContas() throws RemoteException;
    void sacar(double valor, Conta conta) throws RemoteException;
    void depositar(double valor, Conta conta) throws RemoteException;
}
