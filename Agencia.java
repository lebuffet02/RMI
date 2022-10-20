import java.rmi.RemoteException;

public interface Agencia {

    void abrirConta() throws RemoteException;
    void fecharConta() throws RemoteException;
    void verificarConta(String conta) throws RemoteException;
    void sacar(double saldo) throws RemoteException;
    void depositar(double saldo) throws RemoteException;
}
