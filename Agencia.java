import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Agencia extends UnicastRemoteObject implements ProcessoAdministracao {


    protected Agencia() throws RemoteException {
    }

    protected Agencia(int port) throws RemoteException {
        super(port);
    }


    public void abrirConta() throws RemoteException {

    }


    public void fecharConta() throws RemoteException {

    }


    public String verificarConta(String conta) throws RemoteException {
        return null;
    }


    public void sacar() {

    }


    public void depositar() {

    }

}
