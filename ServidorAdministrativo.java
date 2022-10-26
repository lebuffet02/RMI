import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;

public class ServidorAdministrativo {

    public static void main(String[] args) {

        try {
            ProcessoAdministracao processoAdministracao = new ProcessoAdministracao();
            System.setProperty("java.rmi.server.hostname", "localhost");
            LocateRegistry.createRegistry(1099);
            Naming.rebind("processoAdministracao", (Remote) processoAdministracao);
            System.out.println("\n" + " RODANDO ...");

        } catch (Exception e) {
            System.err.println("Falha ao executar Servidor! " + "Tamanho do ARGS: " + args.length + "\n" + e.getLocalizedMessage());
            System.exit(1);
        }
    }
}
