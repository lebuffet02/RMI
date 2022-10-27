import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class AdministracaoServidor {
    public AdministracaoServidor() {
        try {
            System.setProperty("java.rmi.server.hostname", "localhost");
            LocateRegistry.createRegistry(1099);
            Naming.bind("servidor", new Administracao());
            System.out.println("Servidor inicializado...");
        } catch(Exception e) {
            System.err.println("Falha ao inicializar o servidor\nMensagem de erro: " + e.getLocalizedMessage());
        }
    }
    public static void main(String[] args) {
        new AdministracaoServidor();
    }
}