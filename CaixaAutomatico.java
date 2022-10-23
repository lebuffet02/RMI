/**
    Caixa eletrônico 
 */

import java.rmi.*;

public interface CaixaAutomatico extends Remote {
    public Double consultaSaldo (int numero_conta) throws RemoteException;
    public Double saque (int numero_conta, double valor) throws RemoteException;
    public Double deposito (int numero_conta, double valor) throws RemoteException;
}