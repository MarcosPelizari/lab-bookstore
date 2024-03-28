package one.digitalinovation.laboojava.dba;

import one.digitalinovation.laboojava.entity.Client;
import one.digitalinovation.laboojava.entity.Coupon;
import one.digitalinovation.laboojava.entity.Order;
import one.digitalinovation.laboojava.entity.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por simular um banco de dados. Esta faz as inserções e exclusões da
 * aplicação. Atualizações não são permitidas para facilitar o funcionamento da aplicação.
 * @author thiago leite
 */
public class Bank {

    /**
     * Lista que armazena os produtos(livros e cadernos) cadastrados.
     */
    private List<Product> products;

    /**
     * Lista que armazena os pedidos cadastrados.
     */
    private List<Order> orders;

    /**
     * Lista que armazena os cupons disponíveis.
     */
    private List<Coupon> coupons;

    /**
     * Cliente cadastrado.
     */
    private List<Client> client;

    public Bank() {

        this.products = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.client = new ArrayList<>();

        this.coupons = new ArrayList<>();
        coupons.add(new Coupon("CUPOM2", 2));
        coupons.add(new Coupon("CUPOM5", 5));
        coupons.add(new Coupon("CUPOM7", 7));
    }

    public List<Client> getClientList() {return client;}

    public Client[] getClient() {
        return client.toArray(new Client[client.size()]);
    }

    public Coupon[] getCoupons() {
        return coupons.toArray(new Coupon[coupons.size()]);
    }

    public Order[] getOrder() {
        return orders.toArray(new Order[orders.size()]);
    }

    public Product[] getProducts() {
        return products.toArray(new Product[products.size()]);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(int position) {
        products.remove(position);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removerOrder(int position) {
        orders.remove(position);
    }

    public void addClient(Client clients) {client.add(clients);}

    public void removeClient(int position) {client.remove(position);}
}
