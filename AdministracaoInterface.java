import java.rmi.*;
import java.util.List;

public interface AdministracaoInterface extends Remote {
    String abrirConta(int numeroConta, String chaveIdempotencia) throws RemoteException;
	String encerrarConta(int numeroConta, String chaveIdempotencia) throws RemoteException;
	String verificarConta(int numeroConta) throws RemoteException;
	String realizarDeposito(int numeroConta, double valor, String chaveIdempotencia) throws RemoteException;
	String realizarSaque(int numeroConta, double valor, String chaveIdempotencia) throws RemoteException;
	List<String> consultarTransacoes() throws RemoteException;
}