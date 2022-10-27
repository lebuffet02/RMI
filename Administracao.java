import java.util.ArrayList;
import java.util.List;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Administracao extends UnicastRemoteObject implements AdministracaoInterface {
	public Administracao() throws RemoteException {
		super();
	}
	private List<Conta> contas = new ArrayList<>();
	private List<String> chavesIdempotencia = new ArrayList<>();
	protected class Conta {
		public int numero;
		public double saldo;
		public double limite;
		public Conta(int numero, double saldo, double limite) {
			this.numero = numero;
			this.saldo = saldo;
			this.limite = limite;
		}
		public String sacar(double valor) {
			if(valor > this.limite) {
				return "limite";
			}
			else if(valor < 0.0) {
				return "valor negativo";
			}
			else if(valor == 0.0) {
				return "valor zero";
			}
			else if(this.saldo - valor < 0.0) {
				return "saldo negativo";
			}
			this.saldo -= valor;
			return "concluido";
		}
		public boolean depositar(double valor) {
			if(valor <= 0.0) {
				return false;
			}
			this.saldo += valor;
			return true;
		}
		@Override
		public String toString() {
			return String.format("Numero: %d\nSaldo: %.2f\nLimite: %.2f", this.numero, this.saldo, this.limite);
		}
	}
	private Boolean validarIdempotencia(String chave) {
		for(String chaveIdempotencia : this.chavesIdempotencia) {
			if(chaveIdempotencia.equals(chave)) {
				return false;
			}
		}
		this.chavesIdempotencia.add(chave);
		return true;
	}
	public String abrirConta(int numeroConta, String chaveIdempotencia) throws RemoteException {
		if(validarIdempotencia(chaveIdempotencia)) {
			Conta conta = new Conta(numeroConta, 0.0, 1000.0);
			this.contas.add(conta);
			return String.format("Conta aberta.\n%s", conta.toString());
		}
		return "Operação já realizada.";
	}
	public String encerrarConta(int numeroConta, String chaveIdempotencia) throws RemoteException {
		if(validarIdempotencia(chaveIdempotencia)) {
			for (Conta conta : this.contas) {
				if (conta.numero == numeroConta) {
					if (conta.saldo > 0.0) {
						return "Conta não pode ser encerrado pois possui saldo positivo.";
					}
					else if (conta.saldo < 0.0) {
						return "Conta não pode ser encerrado pois possui saldo negativo.";
					}
					else {
						this.contas.remove(conta);
						return "Conta encerrada.";
					}
				}
			}
			return "Conta não existente.";
		}
		return "Operação já realizada.";
	}
	public String verificarConta(int numeroConta) throws RemoteException {
		for(Conta conta: this.contas) {
			if(conta.numero == numeroConta) {
				return conta.toString();
			}
		}
		return "Conta não existente.";
	}
	public String realizarDeposito(int numeroConta, double valor, String chaveIdempotencia) throws RemoteException {
		if(validarIdempotencia(chaveIdempotencia)) {
			for(Conta conta : this.contas) {
				if(conta.numero == numeroConta) {
					if(!conta.depositar(valor)) {
						return "Erro ao depositar.\nValores negativos não são permitidos.";
					}
					return String.format("Deposito realizado.\n%s", conta.toString());
				}
			}
			return "Conta não existente.";
		}
		return "Operação já realizada.";
	}
	public String realizarSaque(int numeroConta, double valor, String chaveIdempotencia) throws RemoteException {
		if(validarIdempotencia(chaveIdempotencia)) {
			for(Conta conta : this.contas) {
				if(conta.numero == numeroConta) {
					if(conta.sacar(valor).equals("limite")) {
						return "Erro: valor para saque superior ao limite da conta.";
					}
					else if(conta.sacar(valor).equals("valor negativo")) {
						return "Erro: valor negativo para saque.";
					}
					else if(conta.sacar(valor).equals("valor zero")) {
						return "Erro: sem saldo para saque.";
					}
					else if(conta.sacar(valor).equals("saldo negativo")) {
						return "Erro: valor superior ao disponível para saque.";
					}
					return String.format("Saque realizado.\n%s", conta.toString());
				}
			}
			return "Conta não existente.";
		}
		return "Operação já realizada.";
	}
	public List<String> consultarTransacoes() throws RemoteException {
		return chavesIdempotencia;
	}
}