import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Cliente {

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {

        if(args.length < 1) {
            System.out.println("Parâmetros: " + args.length);
            return;
        }

        Servidor servidor = (Servidor) Naming.lookup("rmi://localhost:2048/teste");




    }
}

