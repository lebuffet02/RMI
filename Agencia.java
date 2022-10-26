import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Agencia extends Remote {

    void abrirConta(String idConta, String chave) throws RemoteException;
    void fecharConta(String idConta, String chave) throws RemoteException;
    Conta verificarConta(String idConta) throws RemoteException;
    void sacar(double valor, String idConta, String chave) throws RemoteException;
    void depositar(double valor, String idConta, String chave) throws RemoteException;
    int totalContasAtualmente() throws RemoteException;
}
