import java.rmi.RemoteException;

public interface ProcessoAdministracao {

    void abrirConta() throws RemoteException;
    void fecharConta() throws RemoteException;
    String verificarConta(String conta) throws RemoteException;
    void sacar();
    void depositar();
}
