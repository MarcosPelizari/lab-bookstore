package one.digitalinovation.laboojava.business;

import one.digitalinovation.laboojava.dba.Bank;
import one.digitalinovation.laboojava.entity.Client;
import one.digitalinovation.laboojava.entity.Product;

import java.util.Optional;

/**
 * Classe para manipular a entidade {@link Client}.
 * @author thiago leite
 */
public class ClientBusiness {

    /**
     * {@inheritDoc}.
     */
    private Bank dataBank;

    /**
     * Construtor.
     * @param bank Banco de dados para ter acesso aos clientes cadastrados
     */
    public ClientBusiness(Bank bank) {
        this.dataBank = bank;
    }

    /**
     * Consulta o cliente pelo seu CPF.
     * @param id CPF de um cliente
     * @return O cliente que possuir o CPF passado.
     */
    public Optional<Client> consult(String id) {

        for (Client clients: dataBank.getClient()) {
            if (clients.getId().equals(id)) {
                return Optional.of(clients);
            }
        }
        return Optional.empty();

    }

    /**
     * Cadastra um novo cliente.
     * @param cliente Novo cliente que terá acesso a aplicação
     */
    public void save(Client newClient) {

        boolean repeteadClient = false;
        for (Client clients: dataBank.getClient()) {
            if (clients.getId().equals(newClient.getId())) {
                repeteadClient = true;
                System.out.println("Client already registered.");
                break;
            }
        }

        if (!repeteadClient) {
            this.dataBank.addClient(newClient);
            System.out.println("Client successfully registered.");
        }
    }

    /**
     * Exclui um cliente específico.
     * @param id CPF do cliente
     */
    public void delete(String code) {

        int index = 0;
        for (int i = 0; i < dataBank.getClient().length; i++) {
            Client client = dataBank.getClient()[i];
            if (client.getId().equals(code)){
                dataBank.removeClient(i);
                System.out.println("Client removed with success");
            }
        }

    }

    public void listAll() {

        if (dataBank.getClient().length == 0) {
            System.out.println("There are no registered products.");
        } else {
            for (Client clients : dataBank.getClient()) {
                System.out.println(clients.toString());
            }
        }
    }

}
