
/**
 *  Cliente da Adminitração como caixca automatico
 */

import java.rmi.Naming;
import java.util.Scanner;

public class CaixaAutomaticoCliente {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            //Procura pelo servico da calculadora no IP e porta definidos
            CaixaAutomatico caixa = (CaixaAutomatico) Naming.lookup("rmi://localhost:1099/CaixaAutomatico");
   
            System.out.println("1 - saque");
            System.out.println("2 - deposito");
            System.out.println("3 - Extrato");
            System.out.println("0 - sair");
            boolean exec = true;
            double result;
            while (exec) {

                int key = in.nextInt();
                switch (key) {
                    case 1:
                        result = caixa.saque(in.nextInt(), in.nextDouble());
                        System.out.printf("Result: %.2f\n", result);
                        break;
                    case 2:
                        result = caixa.deposito(in.nextInt(), in.nextDouble());
                        System.out.printf("Result: %.2f\n", result);
                        break;
                    case 3:
                        result = caixa.consultaSaldo(in.nextInt());
                        System.out.printf("Result: %.2f\n", result);
                        break;
                    case 0:
                        exec = false;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
