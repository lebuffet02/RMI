import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.Instant;
import java.util.Scanner;

public class ClienteAgencia {

    protected static String idempotencia() {
        String agenciaIdempotencia = "agencia101";
        Instant instant = Instant.now();
        return agenciaIdempotencia + "" + instant;
    }

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {

        if (args.length == 1) {
            System.err.println("\n Usage:\t java-Cliente\n");
            System.exit(1);
        }
        Agencia agencia = (Agencia) Naming.lookup("rmi://localhost:1099/processoAdministracao");
        String entradaIdConta = "";
        String entradaValor = "";
        String chave;
        int numero = -1;

        try {
            Scanner resposta = new Scanner(System.in);

            do {
                System.out.println("▸ Digite um número de 1 a 4:\n");
                System.out.println("▸ Digite 1 para abrir uma conta.");
                System.out.println("▸ Digite 2 para fechar uma conta.");
                System.out.println("▸ Digite 3 para verificar a conta com as suas informações.");
                System.out.println("▸ Digite 4 para sacar.");
                System.out.println("▸ Digite 5 para depositar.");
                System.out.println("▸ Digite 6 para ver o total de contas.");
                System.out.println("▸ Digite 0 para sair do programa.\n");

                numero = resposta.nextInt();
                switch (numero) {
                    case 1: {
                        chave = idempotencia();
                        System.out.println("Insira os dados para abrir a conta.");
                        System.out.println("Insira um id para essa conta.");
                        entradaIdConta = resposta.nextLine();
                        agencia.abrirConta(entradaIdConta, chave);
                    }
                    case 2: {
                        chave = idempotencia();
                        System.out.println("Insira os dados para fechar a conta.");
                        System.out.println("Insira o id dessa conta para fechá-la.");
                        entradaIdConta = resposta.nextLine();
                        agencia.fecharConta(entradaIdConta, chave);
                    }
                    case 3: {
                        System.out.println("Insira o id da conta:");
                        entradaIdConta = resposta.nextLine();
                        System.out.println(agencia.verificarConta(entradaIdConta).toString());
                        break;
                    }
                    case 4: {
                        chave = idempotencia();
                        System.out.println("Insira o id da conta:");
                        entradaIdConta = resposta.nextLine();
                        System.out.println("Informe o valor a ser sacado.");
                        entradaValor = resposta.nextLine();
                        if(agencia.verificarConta(entradaIdConta) != null) {
                            agencia.sacar(Double.parseDouble(entradaValor), entradaIdConta, chave);
                        }
                    }
                    case 5: {
                        chave = idempotencia();
                        System.out.println("Insira o id da conta:");
                        entradaIdConta = resposta.nextLine();
                        System.out.println("Informe o valor a ser depositado.");
                        entradaValor = resposta.nextLine();
                        if(agencia.verificarConta(entradaIdConta) != null) {
                            agencia.depositar(Double.parseDouble(entradaValor), entradaIdConta, chave);
                        }
                    }
                    case 6: {
                        System.out.println("Número de contas abertas: " + agencia.totalContasAtualmente());
                    }
                    default: {
                        System.out.println("Valor Inválido!");
                        break;
                    }
                }
            } while (numero != 0);
            System.out.println("Programa encerrado!");
            resposta.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}