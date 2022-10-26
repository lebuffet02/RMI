import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ProcessoAdministracao extends UnicastRemoteObject implements Agencia {

    private List<Conta> contas = new ArrayList<>();
    private List<String> idempotencia = new ArrayList<>();

    protected ProcessoAdministracao() throws RemoteException {
        super();
    }

    public String checarIdempotencia(String chave) {
        for (String c : this.idempotencia) {
            if (c.equals(chave)) {
                return "chaveExistente";
            }
        }
        idempotencia.add(chave);
        return chave + " adicionada.";
    }

    public Conta verificarConta(int id) throws RemoteException {
        for (Conta c : contas) {
            if(c.getIdConta() == id) {
                return c;
            }
        }
        System.out.println("Conta não existe ainda.");
        return null;
    }

    public String abrirConta(String chave, int id) throws RemoteException {
        if (verificarConta(id) == null) {
            if (!checarIdempotencia(chave).equals("chaveExistente")) {
                this.contas.add(new Conta(id));
                System.out.println("Conta Aberta com Sucesso");
            }
        }
        return "Conta já existe.";
    }

    public String fecharConta(int id) throws RemoteException {
        Conta conta = verificarConta(id);
        if (conta != null) {
            if (conta.getSaldo() > 0.0) {
                System.out.println("Conta não pode ser encerrada pois existe saldo positivo: " + conta.getSaldo());
            } else if (conta.getSaldo() < 0.0) {
                System.out.println("Conta não pode ser encerrada pois existe saldo negativo: " + conta.getSaldo());
            } else {
                contas.remove(conta);
                System.out.println("Conta Fechada!");
            }
        }
        return "Falha ao fechar a conta.";
    }

    public String sacar(int id, double valor, String chave) throws RemoteException {
        if (!checarIdempotencia(chave).equals("chaveExistente")) {
            Conta conta = verificarConta(id);
            if (conta != null) {
                if (conta.getSaldo() > 0.0 && valor > 0.0 && valor <= conta.getLimite() && valor <= conta.getSaldo()) {
                    conta.setSaldo(conta.getSaldo() - valor);
                    System.out.println("Saldo Atual: " + conta.getSaldo());
                } else {
                    System.out.println("Saldo Insuficiente: " + conta.getSaldo() + ", é o saldo atual.");
                }
            }
        }
        return "Problema ao sacar.";
    }

    public String depositar(int id, double valor, String chave) throws RemoteException {
        if (!checarIdempotencia(chave).equals("chaveExistente")) {
            Conta conta = verificarConta(id);
            if (conta != null) {
                conta.setSaldo(conta.getSaldo() + valor);
                System.out.println("Saldo Atual: " + conta.getSaldo());
            }
        }
        return "problema ao depositar.";
    }

    public int totalContasAtualmente() {
        return contas.size();
    }
}
