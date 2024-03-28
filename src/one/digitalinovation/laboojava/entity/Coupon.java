package one.digitalinovation.laboojava.entity;

/**
 * Classe que representa a entidade cupom. Este pode ser utilizado no ato do fechamento do pedido
 * para obter um desconto, caso desejado.
 * @author thiago leite
 */
public class Coupon {

    /**
     * Código descritivo do cupom.
     */
    private String code;

    /**
     * Valor em porcentagem do desconto.
     */
    private int discount;

    /**
     * Construtor do cupom
     * @param code Código do cupom
     * @param discount Porcentagem de desconto
     */
    public Coupon(String code, int discount) {
        this.code = code;
        this.discount = discount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

}
