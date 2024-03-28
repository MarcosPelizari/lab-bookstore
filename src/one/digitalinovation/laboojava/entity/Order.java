package one.digitalinovation.laboojava.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa a entidade pedido, qual é a compra dos produtos por um cliente.
 * @author thiago leite
 */
public class Order {

    private String code;

    private List<Client> client;

    private List<Product> products;

    private double total;

    public String getCode() {
        return code;
    }

    public Order() {
        this.products = new ArrayList<>();
    }
    public void setCode(String code) {
        this.code = code;
    }

    public List<Client> getClient() {
        return client;
    }

    public void setClient(List<Client> client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    private String getBoughtProducts() {
        StringBuilder products = new StringBuilder();
        products.append("[");
        for (Product product: getProducts()) {
            products.append(product.toString());
            products.append("Qtd:");
            products.append(product.getQuantity());
            products.append(" ");
        }
        products.append("]");

        return products.toString();
    }

    @Override
    public String toString() {
        return "Order{" +
                "code='" + code + '\'' +
                ", client=" + client +
                ", products=" + getBoughtProducts() +
                ", total=" + total +
                '}';
    }
}
