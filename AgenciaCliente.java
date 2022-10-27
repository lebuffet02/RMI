import java.rmi.Naming;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AgenciaCliente {
    public static String gerarChaveIdempotencia() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ssZ");
        Date date = new Date(System.currentTimeMillis());
        return String.format("ag-%s", formatter.format(date));
    }
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        try {
            AdministracaoInterface servidor = (AdministracaoInterface) Naming.lookup("rmi://localhost:1099/servidor");
            boolean execucao = true;
            int resposta;
            while(execucao) {
                System.out.println("1 - Abertura de conta");
                System.out.println("2 - Encerramento de conta");
                System.out.println("3 - Verificação de conta");
                System.out.println("4 - Depósito");
                System.out.println("5 - Saque");
                System.out.println("6 - Consultar transações");
                System.out.println("0 - Encerrar operação\n");
                int opcao = entrada.nextInt();
                switch(opcao) {
                    case 1:
                        System.out.println("Digite o número da conta: ");
                        System.out.printf(String.format("%s\n", servidor.abrirConta(entrada.nextInt(), gerarChaveIdempotencia())));
                        break;
                    case 2:
                        System.out.println("Digite o número da conta: ");
                        System.out.printf(String.format("%s\n", servidor.encerrarConta(entrada.nextInt(), gerarChaveIdempotencia())));
                        break;
                    case 3:
                        System.out.println("Digite o número da conta: ");
                        System.out.printf(String.format("%s\n", servidor.verificarConta(entrada.nextInt())));
                        break;
                    case 4:
                        System.out.println("Digite o número da conta: ");
                        resposta = entrada.nextInt();
                        System.out.println("Digite o valor para o depósito: ");
                        System.out.printf(String.format("%s\n", servidor.realizarDeposito(resposta, entrada.nextDouble(), gerarChaveIdempotencia())));
                        break;
                    case 5:
                        System.out.println("Digite o número da conta: ");
                        resposta = entrada.nextInt();
                        System.out.println("Digite o valor para o saque: ");
                        System.out.printf(String.format("%s\n", servidor.realizarSaque(resposta, entrada.nextDouble(), gerarChaveIdempotencia())));
                        break;
                    case 6:
                        System.out.printf(String.format("%s\n", servidor.consultarTransacoes()));
                        break;
                    case 0:
                        execucao = false;
                    default:
                        break;
                }
            }
        } catch(Exception e) {
            System.err.println("Falha ao inicializar o cliente\nMensagem de erro: " + e.getLocalizedMessage());
        }
    }
}
