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

    public String checarIdConta(String idConta) throws RemoteException {
        if (idConta != null) {
            idConta = idConta.trim();
            if (!idConta.isEmpty()) {
                return idConta;
            }
        }
        System.out.println("Valor do IdConta está errado.");
        return null;
    }

    public void abrirConta(String idConta, String chave) throws RemoteException {
        if (!checarIdempotencia(chave).equals("chaveExistente")) {
            if (checarIdConta(idConta) != null) {
                if (verificarConta(idConta) == null) {
                    this.contas.add(new Conta(idConta));
                }
            }
        }
    }

    public void fecharConta(String idConta, String chave) throws RemoteException {
        if (!checarIdempotencia(chave).equals("chaveExistente")) {
            if (checarIdConta(idConta) != null) {
                Conta conta = verificarConta(idConta);
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
            }
        }
    }

    public void sacar(double valor, String idConta, String chave) throws RemoteException {
        if (!checarIdempotencia(chave).equals("chaveExistente")) {
            if (checarIdConta(idConta) != null) {
                Conta conta = verificarConta(idConta);
                if (conta != null) {
                    if (conta.getSaldo() > 0.0 && valor > 0.0 && valor <= conta.getLimite() && valor <= conta.getSaldo()) {
                        conta.setSaldo(conta.getSaldo() - valor);
                        System.out.println("Saldo Atual: " + conta.getSaldo());
                    } else {
                        System.out.println("Saldo Insuficiente: " + conta.getSaldo() + ", é o saldo atual.");
                    }
                }
            }
        }
    }

    public void depositar(double valor, String idConta, String chave) throws RemoteException {
        if (!checarIdempotencia(chave).equals("chaveExistente")) {
            if (checarIdConta(idConta) != null) {
                Conta conta = verificarConta(idConta);
                if (conta != null) {
                    conta.setSaldo(conta.getSaldo() + valor);
                    System.out.println("Saldo Atual: " + conta.getSaldo());
                }
            }
        }
    }

    public Conta verificarConta(String idConta) throws RemoteException {
        if (checarIdConta(idConta) != null) {
            for (Conta c : contas) {
                if(c.getIdConta().equals(idConta)) {
                    return c;
                }
            }
        }
        System.out.println("Conta Inexistente.");
        return null;
    }

    public int totalContasAtualmente() {
        return contas.size();
    }
}
