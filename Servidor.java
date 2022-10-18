import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Servidor {

    public static void main(String[] args) throws RemoteException, MalformedURLException {

        if(args.length != 2) {
            System.err.println("\n Usage:\t java-Servidor\n");
            System.exit(1);
        }

        Agencia agencia = new Agencia();
        //agencia.
        //agencia.
        //agencia.
        //agencia.
        //agencia.


        String objetoNome = args[0];
        System.out.println("\t" + objetoNome + " ...");
        Naming.rebind(objetoNome, agencia);
        System.out.println("\n" + "RODANDO ...");



    }

}
