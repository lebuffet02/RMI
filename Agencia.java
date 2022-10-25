import java.rmi.RemoteException;
import java.util.List;

public interface Agencia {

    void abrirConta(String idConta) throws RemoteException;
    void fecharConta(String idConta) throws RemoteException;
    Conta verificarConta(String idConta) throws RemoteException;
    void sacar(double valor, String idConta) throws RemoteException;
    void depositar(double valor, String idConta) throws RemoteException;
}
