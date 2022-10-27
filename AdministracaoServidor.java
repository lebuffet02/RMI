import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class AdministracaoServidor {
    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "localhost");
            LocateRegistry.createRegistry(1099);
            Naming.bind("servidor", new Administracao());
            System.out.println("RODANDO ...");
        } catch(Exception e) {
            System.err.println("Falha ao inicializar o servidor\nMensagem de erro: " + e.getLocalizedMessage());
        }
    }
}