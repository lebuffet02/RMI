import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ProcessoAdministracao extends UnicastRemoteObject implements Agencia {

    private List<Conta> contas;
    static int NUM_CONTAS = 0;

    protected ProcessoAdministracao() throws RemoteException {}

    protected ProcessoAdministracao(Conta conta) throws RemoteException {
        super();
    }

    public boolean abrirConta(Conta conta) throws RemoteException {
        boolean contaExistente = false;
        if(conta != null) {
           for (Conta c : contas) {
               if (c.getIdConta().equals(conta.getIdConta())) {
                   contaExistente = true;
                  break;
               }
           }
           if (!contaExistente) {
              this.contas.add(conta);
              NUM_CONTAS++;
           }
       }
        return true;
    }

    public List<Conta> verificarContas() throws RemoteException {
        return contas;
    }

    public List<Conta> fecharConta(Conta conta) throws RemoteException {
        if (conta != null) {
            for (Conta c : contas) {
                if (c.getIdConta().equals(conta.getIdConta())) {
                    if (conta.getSaldo() > 0.0) {
                        System.out.println("Conta não pode ser encerrada pois existe saldo positivo: " + conta.getSaldo());
                    }
                    else if (conta.getSaldo() < 0.0) {
                        System.out.println("Conta não pode ser encerrada pois existe saldo negativo: " + conta.getSaldo());
                    }
                    else {
                        contas.remove(c);
                        NUM_CONTAS--;
                        System.out.println("Conta Fechada!");
                    }

                }
            }
        }
        return contas;
    }

    public void sacar(double valor, Conta conta) throws RemoteException {
        if(conta.getSaldo() > 0.0 && valor > 0.0 && valor <= conta.getLimite() && valor <= conta.getSaldo()) {
            conta.setSaldo(conta.getSaldo() - valor);
            System.out.println("Saldo Atual: " + conta.getSaldo());
        } else {
            System.out.println("Saldo Insuficiente: " + conta.getSaldo() + ", é o saldo atual.");
        }
    }

    public void depositar(double valor, Conta conta) throws RemoteException {
        conta.setSaldo(conta.getSaldo() + valor);
        System.out.println("Saldo Atual: " + conta.getSaldo());
    }

    public boolean totalContasAtualmente() {
        System.out.println("Atualmente existem " + NUM_CONTAS + " cadastradas no momento.");
        return true;
    }
}
