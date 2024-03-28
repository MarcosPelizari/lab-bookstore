package one.digitalinovation.laboojava.entity;

/**
 * Classe que representa a abstração dos produtos que podem ser vendidos pela loja.
 * @author thiago leite
 */
public abstract class Product {

    /**
     * Código de identiticação do produto.
     */
    private String code;

    /**
     * Valor unitário do produto.
     */
    private double price;

    /**
     * Quantidade comprada do produto.
     */
    private int quantity;

    public Product() {}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAmount(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Calcula o preço do frete para os produtos comprados. Este cálculo pode
     * variar de acordo com o produto
     * @return valor do frete para o determinado produto
     */
    public abstract double calculateShipping();
}
