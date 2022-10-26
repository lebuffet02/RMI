import java.rmi.Naming;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;

public class CaixaEletronico {

    protected static String idempotencia() {
        String agenciaIdempotencia = "caixa252";
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

            System.out.println("▸ Digite um número de 1 a 4:\n");
            System.out.println("▸ Digite 1 para depositar e depois informe o número da conta.");
            System.out.println("▸ Digite 2 para sacar e depois informe o número da conta.");
            System.out.println("▸ Digite 3 para verificar a conta com as suas informações.");
            System.out.println("▸ Digite 0 para sair do programa.\n");

            while (finalizar) {

                numero = resposta.nextInt();

                switch (numero) {
                    case 1: {
                        System.out.println("Insira o id da conta:");
                        val1 = resposta.nextInt();
                        System.out.println("Informe o valor a ser depositado.");
                        val2 = resposta.nextInt();
                        chave = idempotencia();
                        if (agencia.verificarConta(val1) != null) {
                            agencia.depositar(val1, val2, chave);
                            break;
                        }
                    }
                    case 2: {
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
                    case 3: {
                        System.out.println("Verificar - Insira o id da conta:");
                        val1 = resposta.nextInt();
                        agencia.verificarConta(val1);
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
                System.out.println("Programa encerrado!");
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
