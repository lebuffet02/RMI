import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Agencia extends Remote {

    String abrirConta(String chave, int id) throws RemoteException;
    String fecharConta(int id) throws RemoteException;
    Conta verificarConta(int id) throws RemoteException;
    String sacar(int id, double valor, String chave) throws RemoteException;
    String depositar(int id, double valor, String chave) throws RemoteException;
    int totalContasAtualmente() throws RemoteException;
}
