package one.digitalinovation.laboojava.console;

import one.digitalinovation.laboojava.dba.Bank;
import one.digitalinovation.laboojava.entity.*;
import one.digitalinovation.laboojava.business.ClientBusiness;
import one.digitalinovation.laboojava.business.OrderBusiness;
import one.digitalinovation.laboojava.business.ProductBusiness;
import one.digitalinovation.laboojava.utility.DataReader;

import java.util.Optional;

/**
 * Classe responsável por controlar a execução da aplicação.
 * @author thiago leite
 */
public class Start {

    private static Client loggedInClient = null;

    private static Bank bank = new Bank();

    private static ClientBusiness clientBusiness = new ClientBusiness(bank);
    private static OrderBusiness orderBusiness = new OrderBusiness(bank);
    private static ProductBusiness productBusiness = new ProductBusiness(bank);

    /**
     * Método utilitário para inicializar a aplicação.
     * @param args Parâmetros que podem ser passados para auxiliar na execução.
     */
    public static void main(String[] args) {

        Client firstClient = new Client();
        firstClient.setName("Fulano");
        firstClient.setId("123456789011");
        bank.addClient(firstClient);

        System.out.println("Bem vindo ao e-Compras");

        String option = "";

        while(true) {

            if (loggedInClient == null) {

                System.out.println("Digite o cpf:");

                String id = "";
                id = DataReader.readData();

                identifyUser(id);
            }

            System.out.println("Select an option:");
            System.out.println("1 - Register Book");
            System.out.println("2 - Delete Book");
            System.out.println("3 - Register Notebook");
            System.out.println("4 - Delete Notebook");
            System.out.println("5 - Make Order");
            System.out.println("6 - Delete Order");
            System.out.println("7 - List Products");
            System.out.println("8 - List Orders");
            System.out.println("9 - Add Client");
            System.out.println("10 - Delete Client");
            System.out.println("11 - List Client");
            System.out.println("12 - Logout");
            System.out.println("13 - Exit");

            option = DataReader.readData();

            switch (option) {
                case "1":
                    Book book = DataReader.readBook();
                    productBusiness.save(book);
                    break;
                case "2":
                    System.out.println("Type the code of the book.");
                    String codeBook = DataReader.readData();
                    productBusiness.delete(codeBook);
                    break;
                case "3":
                    Notebook notebook = DataReader.readNotebook();
                    productBusiness.save(notebook);
                    break;
                case "4":
                    System.out.println("Type the code of the notebook.");
                    String codeNotebook = DataReader.readData();
                    productBusiness.delete(codeNotebook);
                    break;
                case "5":
                    Order order = DataReader.readOrder(bank);
                    Optional<Coupon> coupon = DataReader.readCoupon(bank);

                    if (coupon.isPresent()) {
                        orderBusiness.save(order, coupon.get());
                    } else {
                        orderBusiness.save(order);
                    }
                    break;
                case "6":
                    System.out.println("Type the code of the order.");
                    String codeOrder = DataReader.readData();
                    orderBusiness.delete(codeOrder);
                    break;
                case "7":
                    productBusiness.listAll();
                    break;
                case "8":
                    orderBusiness.listAll();
                    break;
                case "9":
                    Client client = DataReader.readClient();
                    clientBusiness.save(client);
                    break;
                case "10":
                    System.out.println("Type the id of the Client that you want to remove.");
                    String codeClient = DataReader.readData();
                    clientBusiness.delete(codeClient);
                    break;
                case "11":
                    clientBusiness.listAll();
                    break;
                case "12":
                    System.out.println(String.format("Come back soon %s!", loggedInClient.getName()));
                    loggedInClient = null;
                    break;
                case "13":
                    System.out.println("Application closed.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    /**
     * Procura o usuário na base de dados.
     * @param id CPF do usuário que deseja logar na aplicação
     */
    private static void identifyUser(String id) {

        Optional<Client> result = clientBusiness.consult(id);

        if (result.isPresent()) {

            Client client = result.get();
            System.out.println(String.format("Hello %s! You are logged in.", client.getName()));
            loggedInClient = client;
        } else {
            System.out.println("User not found.");
            System.exit(0);
        }
    }
}
