import java.rmi.Naming;
import java.util.Scanner;

public class CaixaEletronico {

    public static void main(String[] args) throws Exception {

        Scanner resposta = new Scanner(System.in);
        Agencia agencia = (Agencia) Naming.lookup("rmi://localhost:8080/agencia");
        ProcessoAdministracao processoAdministracao = new ProcessoAdministracao();
        Conta conta = new Conta();
        String numero;
        String entrada1, entrada2;

        do {
            System.out.println("▸ Digite um número de 1 a 4:\n");
            System.out.println("▸ Digite 1 para depositar e depois informe o número da conta.");
            System.out.println("▸ Digite 2 para sacar e depois informe o número da conta.");
            System.out.println("▸ Digite 3 para verificar as contas com as suas informações.");
            System.out.println("▸ Digite 4 para saber o número de contas existentes.");
            System.out.println("▸ Digite 0 para sair do programa.\n");

            numero = resposta.nextLine();

            switch (numero) {
                case "1": {
                    System.out.println("Insira o id da conta a ser depositada:");
                    entrada1 = resposta.nextLine();
                    if(!entrada1.trim().isEmpty()) {
                        if(entrada1.equals(conta.getIdConta())) {
                            System.out.println("Conta com este id existe, prossiga para os próximos passos.");
                            System.out.println("Informe o valor a ser depositado.");
                            entrada2 = resposta.nextLine();
                            agencia.depositar(Double.parseDouble(entrada2), conta);
                            break;
                        }
                        else {
                            System.out.println("Não existe conta vinculada com este id informado.");
                            break;
                        }
                    }
                }
                case "2": {
                    System.out.println("Insira o id da conta a ser realizado o saque:");
                    entrada1 = resposta.nextLine();
                    if(!entrada1.trim().isEmpty()) {
                        if(entrada1.equals(conta.getIdConta())) {
                            System.out.println("Conta com este id existe, prossiga para os próximos passos.");
                            System.out.println("Informe o valor a ser sacado.");
                            entrada2 = resposta.nextLine();
                            agencia.sacar(Double.parseDouble(entrada2), conta);
                            break;
                        }
                        else {
                            System.out.println("Não existe conta vinculada com este id informado.");
                            break;
                        }
                    }
                }
                case "3": {
                    System.out.println(agencia.verificarContas());
                    break;
                }
                case "4": {
                    System.out.println(processoAdministracao.totalContasAtualmente());
                }
                case "0": {
                    System.out.println("Programa encerrado!");
                    System.exit(1);
                }
                default: {
                    System.out.println("Valor Inválido!");
                    break;
                }
            }
        } while (!numero.equalsIgnoreCase("0"));
        resposta.close();
    }
}
