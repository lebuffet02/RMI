import java.rmi.Naming;
import java.time.Instant;
import java.util.Scanner;

public class CaixaEletronico {

    protected static String idempotencia() {
        String agenciaIdempotencia = "agencia252";
        Instant instant = Instant.now();
        return agenciaIdempotencia + "" + instant;
    }

    public static void main(String[] args) {

        String entradaIdConta = "";
        String entradaValor = "";
        String chave;
        Scanner resposta = new Scanner(System.in);

        try {
            Agencia agencia = (Agencia) Naming.lookup("rmi://localhost:1099/processoAdministracao");

            int numero;

            do {
                System.out.println("▸ Digite um número de 1 a 4:\n");
                System.out.println("▸ Digite 1 para depositar e depois informe o número da conta.");
                System.out.println("▸ Digite 2 para sacar e depois informe o número da conta.");
                System.out.println("▸ Digite 3 para verificar a conta com as suas informações.");
                System.out.println("▸ Digite 0 para sair do programa.\n");

                numero = resposta.nextInt();

                switch (numero) {
                    case 1: {
                        chave = idempotencia();
                        System.out.println("Insira o id da conta:");
                        entradaIdConta = resposta.nextLine();
                        System.out.println("Informe o valor a ser depositado.");
                        entradaValor = resposta.nextLine();
                        agencia.depositar(Double.parseDouble(entradaValor), entradaIdConta, chave);
                    }
                    case 2: {
                        chave = idempotencia();
                        System.out.println("Insira o id da conta:");
                        entradaIdConta = resposta.nextLine();
                        System.out.println("Informe o valor a ser sacado.");
                        entradaValor = resposta.nextLine();
                        agencia.sacar(Double.parseDouble(entradaValor), entradaIdConta, chave);
                    }
                    case 3: {
                        System.out.println("Insira o id da conta:");
                        entradaIdConta = resposta.nextLine();
                        System.out.println(agencia.verificarConta(entradaIdConta).toString());
                        break;
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
