import java.rmi.RemoteException;
import java.util.List;

public interface Agencia {

    Conta abrirConta(Conta conta, String idConta) throws RemoteException;
    List<Conta> fecharConta(String idConta) throws RemoteException;
    List<Conta> verificarContas() throws RemoteException;
    void sacar(double saldo, double limite) throws RemoteException;
    void depositar(double saldo) throws RemoteException;
}
