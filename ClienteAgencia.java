import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;

public class ClienteAgencia {

    protected static String idempotencia() {
        String agenciaIdempotencia = "agencia101";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy ' no horário: ' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return agenciaIdempotencia + "" + formatter.format(date);
    }

    public static void main(String[] args) {

        Scanner resposta = new Scanner(System.in);
        boolean finalizar = true;
        int numero;
        int val1;
        int val2;
        String chave;

        try {
            Agencia agencia = (Agencia) Naming.lookup("rmi://localhost:1099/processoAdministracao");

            System.out.println("▸ Digite 1 para abrir uma conta.");
            System.out.println("▸ Digite 2 para fechar uma conta.");
            System.out.println("▸ Digite 3 para verificar a conta com as suas informações.");
            System.out.println("▸ Digite 4 para sacar.");
            System.out.println("▸ Digite 5 para depositar.");
            System.out.println("▸ Digite 6 para ver o total de contas.");
            System.out.println("▸ Digite 0 para sair do programa.\n");

            while (finalizar) {

                numero = resposta.nextInt();
                switch (numero) {
                    case 1: {
                        System.out.println("Abrir Conta - Insira um id para essa conta.");
                        val1 = resposta.nextInt();
                        chave = idempotencia();
                        agencia.abrirConta(chave, val1);
                        break;
                    }
                    case 2: {
                        System.out.println("Fechar Conta - Insira o id dessa conta para fechá-la.");
                        val1 = resposta.nextInt();
                        agencia.fecharConta(val1);
                        break;
                    }
                    case 3: {
                        System.out.println("Verificar - Insira o id da conta:");
                        val1 = resposta.nextInt();
                        agencia.verificarConta(val1);
                        break;
                    }
                    case 4: {
                        System.out.println("Sacar - Insira o id da conta:");
                        val1 = resposta.nextInt();
                        System.out.println("Sacar - Informe o valor a ser sacado.");
                        val2 = resposta.nextInt();
                        chave = idempotencia();
                        if (agencia.verificarConta(val1) != null) {
                            agencia.sacar(val1, val2, chave);
                            break;
                        }
                    }
                    case 5: {
                        System.out.println("Depositar - Insira o id da conta:");
                        val1 = resposta.nextInt();
                        System.out.println("Depositar - Informe o valor a ser depositado.");
                        val2 = resposta.nextInt();
                        chave = idempotencia();
                        if (agencia.verificarConta(val1) != null) {
                            agencia.depositar(val1, val2, chave);
                            break;
                        }
                    }
                    case 6: {
                        System.out.println("Número de contas abertas: " + agencia.totalContasAtualmente() + "\n");
                        finalizar = false;
                        break;
                    }
                    case 0: {
                        System.out.println("Programa Encerrado.");
                        finalizar = false;
                    }
                    default: {
                        System.out.println("Valor Inválido!");
                        break;
                    }
                }
            }
            System.out.println("Programa encerrado!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}