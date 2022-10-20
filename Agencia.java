import java.rmi.RemoteException;
import java.util.List;

public interface Agencia {

    void abrirConta() throws RemoteException;
    List<Conta> fecharConta(String idConta) throws RemoteException;
    void verificarConta(String conta) throws RemoteException;
    void sacar(double saldo) throws RemoteException;
    void depositar(double saldo) throws RemoteException;
}
