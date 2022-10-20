import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClienteAgencia {

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {

        if (args.length != 2) {
            System.err.println("\n Usage:\t java-Cliente\n");
            System.exit(1);
        }

        try {
            Agencia agencia = (Agencia) Naming.lookup("rmi://localhost:2048/teste");


            //blabla



        } catch (Exception e) {
            System.err.println("Erro no cliente! " + e.getMessage());
        }
    }
}