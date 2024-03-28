package one.digitalinovation.laboojava.business;

import one.digitalinovation.laboojava.dba.Bank;
import one.digitalinovation.laboojava.entity.Coupon;
import one.digitalinovation.laboojava.entity.Order;
import one.digitalinovation.laboojava.entity.Product;
import one.digitalinovation.laboojava.entity.Client;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe para manipular a entidade {@link Order}.
 * @author thiago leite
 */
public class OrderBusiness {

    /**
     * {@inheritDoc}.
     */
    private Bank dataBank;

    /**
     * Construtor.
     * @param bank Banco de dados para ter armazenar e ter acesso os pedidos
     */
    public OrderBusiness(Bank bank) {
        this.dataBank = bank;
    }

    private double calculateShipping(List<Product> products, Coupon coupon) {

        double total = 0.0;
        for (Product product : products) {
            total += product.calculateShipping();
        }

        if (coupon != null) {
            return  total * (1 - coupon.getDiscount());
        } else {
            return  total;
        }

    }

    /**
     * Salva um novo pedido sem cupom de desconto.
     * @param newOrder Pedido a ser armazenado
     */
    public void save(Order newOrder) {
        save(newOrder, null);
    }

    /**
     * Salva um novo pedido com cupom de desconto.
     * @param newOrder Pedido a ser armazenado
     * @param coupon Cupom de desconto a ser utilizado
     */
    public void save(Order newOrder, Coupon coupon) {

        //Definir padrão código
        //Pegar data do dia corrente
        //Formatar código
        String code = "PE%4d%2d%04d";
        LocalDate today = LocalDate.now();

        code = String.format(code, today.getYear(), today.getMonthValue(), dataBank.getOrder().length);

        //Setar código no pedido
        newOrder.setCode(code);
        //Setar cliente no pedido
        newOrder.setClient(List.of(dataBank.getClient()));
        //Calcular e set total
        newOrder.setTotal(calculateShipping(newOrder.getProducts(), coupon));
        //Adicionar no banco
        dataBank.addOrder(newOrder);
        //Mensagem
        System.out.println("Done");
    }

    /**
     * Exclui um pedido a partir de seu código de rastreio.
     * @param code Código do pedido
     */
    public void delete(String code) {

        int orderDelete = -1;
        for (int i = 0; i < dataBank.getOrder().length; i++) {

            Order pedido = dataBank.getOrder()[i];
            if (pedido.getCode().equals(code)) {
                orderDelete = i;
                break;
            }
        }

        if (orderDelete != -1) {
            dataBank.removerOrder(orderDelete);
            System.out.println("Pedido excluído com sucesso.");
        } else {
            System.out.println("Pedido inexistente.");
        }
    }

    /**
     * Lista todos os pedidos realizados.
     */
    public void listAll() {

        if (dataBank.getProducts().length == 0) {
            System.out.println("There are no registered products.");
        } else {

            for (Product product : dataBank.getProducts()) {
                System.out.println(product.toString());
            }
        }
    }

}
