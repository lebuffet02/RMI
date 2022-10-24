import java.rmi.Naming;

public class ServidorAdministrativo {

    public static void main(String[] args) {

        if(args.length != 1) {
            System.err.println("\n Uso:\t java-Servidor erro, falha ao " +
                    "estabelecer parâmetros.\n");
            System.exit(1);
        }
        try {
            ProcessoAdministracao processoAdministracao = new ProcessoAdministracao();
            String objetoNome = "//localhost/" + args[0];
            System.out.println("Registrando " + objetoNome + " ...");
            Naming.rebind(objetoNome, processoAdministracao);
            System.out.println("\n" + " RODANDO ...");
        } catch (Exception e) {
            System.err.println("Falha ao executar Servidor! " + e.getMessage());
            System.exit(1);
        }
    }
}
