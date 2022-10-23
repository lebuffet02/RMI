import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class CaixaAutomaticoImp extends UnicastRemoteObject implements CaixaAutomatico{
    public CaixaAutomaticoImp() throws RemoteException{
        super();
    }
    //saque, deposito consulta saldo
    @Override
    public Double consultaSaldo(int numero_conta) throws RemoteException {
        double saldo = 0.0;
        if(numero_conta != 0) {
            //verifica algo

        }
        //verificar com o Lucas como buscar esse dado

        return saldo;
    }

    @Override
    public Double saque(int numero_conta, double valor) throws RemoteException {
        //ver com o Lucas esses calculos ded reduzir do valor da conta
        return 1.1;
    }

    @Override
    public Double deposito(int numero_conta, double valor)  throws RemoteException {
        //A mesma coisa
        return 1.1;
    }
}
