import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class CaixaEletronico {

    public static void main(String[] args) {

        try {
            Scanner resposta = new Scanner(System.in);
            Agencia agencia = (Agencia) Naming.lookup("rmi://localhost:8080/agencia");
            int numero;
            String entradaIdConta, entradaValor;

            do {
                System.out.println("▸ Digite um número de 1 a 4:\n");
                System.out.println("▸ Digite 1 para depositar e depois informe o número da conta.");
                System.out.println("▸ Digite 2 para sacar e depois informe o número da conta.");
                System.out.println("▸ Digite 3 para verificar a conta com as suas informações.");
                System.out.println("▸ Digite 0 para sair do programa.\n");

                numero = resposta.nextInt();

                switch (numero) {
                    case 1: {
                        System.out.println("Insira o id da conta:");
                        entradaIdConta = resposta.nextLine();
                        System.out.println("Informe o valor a ser depositado.");
                        entradaValor = resposta.nextLine();
                        agencia.depositar(Double.parseDouble(entradaValor), entradaIdConta);
                    }
                    case 2: {
                        System.out.println("Insira o id da conta:");
                        entradaIdConta = resposta.nextLine();
                        System.out.println("Informe o valor a ser sacado.");
                        entradaValor = resposta.nextLine();
                        agencia.depositar(Double.parseDouble(entradaValor), entradaIdConta);
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
        }
        catch (RemoteException | MalformedURLException | NotBoundException e) {
            if () {

            }
        }
    }
}
