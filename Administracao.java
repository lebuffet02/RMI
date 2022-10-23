/**
 *  Servidor calculadora
 */

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Administracao {
    public Administracao(){ //Administração
        try {
            //Definicao do ip onde o servico ira funcionar
            System.setProperty("java.rmi.server.hostname", "localhost");
            //Registro do servico em uma porta
            LocateRegistry.createRegistry(1099);
            //Cria o objeto que implementa os metodos que serao servidos
            CaixaAutomatico caixa = new CaixaAutomaticoImp();
            //Agencia a = new agenciaImp();
            //Caixa c = new caixaImp();
            //Coloca na porta registrada o servico da calculadora
            Naming.bind("CaixaAutomatico", (Remote) caixa);
            //Naming.bind(name:"AgenciaService", (Remote) a);
            //Naming.bind(name:"CaixaService", (Remote) c);
            System.out.println("Conexao estabelecida.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Administracao();
    }
}