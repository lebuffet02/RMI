import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClienteAgencia {

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {

        if (args.length != 2) {
            System.err.println("\n Usage:\t java-Cliente\n");
            System.exit(1);
        }
        Agencia agencia = (Agencia) Naming.lookup("rmi://localhost:8080/teste");

        Conta backupConta = null;
        int backupNumero = 0;

        try {
            Scanner resposta = new Scanner(System.in);
            int numero;
            String entradaIdConta, entradaValor;


            do {
                System.out.println("▸ Digite um número de 1 a 4:\n");
                System.out.println("▸ Digite 1 para abrir uma conta.");
                System.out.println("▸ Digite 2 para fechar uma conta.");
                System.out.println("▸ Digite 3 para verificar a conta com as suas informações.");
                System.out.println("▸ Digite 4 para sacar.");
                System.out.println("▸ Digite 5 para depositar.");
                System.out.println("▸ Digite 0 para sair do programa.\n");

                numero = resposta.nextInt();
                switch (numero) {
                    case 1: {
                        backupNumero = numero;
                        System.out.println("Insira os dados para abrir a conta.");
                        System.out.println("Insira um id para essa conta.");
                        entradaIdConta = resposta.nextLine();
                        agencia.abrirConta(entradaIdConta);
                    }
                    case 2: {
                        backupNumero = numero;
                        System.out.println("Insira os dados para fechar a conta.");
                        System.out.println("Insira o id dessa conta para fechá-la.");
                        entradaIdConta = resposta.nextLine();
                        agencia.fecharConta(entradaIdConta);
                    }
                    case 3: {
                        backupNumero = numero;
                        System.out.println("Insira o id da conta:");
                        entradaIdConta = resposta.nextLine();
                        System.out.println(agencia.verificarConta(entradaIdConta).toString());
                        break;
                    }
                    case 4: {
                        backupNumero = numero;
                        System.out.println("Insira o id da conta:");
                        entradaIdConta = resposta.nextLine();
                        System.out.println("Informe o valor a ser sacado.");
                        entradaValor = resposta.nextLine();
                        backupConta = agencia.verificarConta(entradaIdConta);
                        if(backupConta != null) {
                            agencia.depositar(Double.parseDouble(entradaValor), entradaIdConta);
                        }
                    }
                    case 5: {
                        backupNumero = numero;
                        System.out.println("Insira o id da conta:");
                        entradaIdConta = resposta.nextLine();
                        System.out.println("Informe o valor a ser depositado.");
                        entradaValor = resposta.nextLine();
                        backupConta = agencia.verificarConta(entradaIdConta);
                        if(backupConta != null) {
                            agencia.depositar(Double.parseDouble(entradaValor), entradaIdConta);
                        }
                    }
                    default: {
                        System.out.println("Valor Inválido!");
                        break;
                    }
                }
            } while (numero != 0);
            System.out.println("Programa encerrado!");
            resposta.close();

        } catch (RemoteException e) {
            if (backupNumero == 4) {


            } else if (backupNumero == 5) {

            }
        }
    }
}