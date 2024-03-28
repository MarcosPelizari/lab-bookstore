package one.digitalinovation.laboojava.business;

import one.digitalinovation.laboojava.dba.Bank;
import one.digitalinovation.laboojava.entity.Product;

import java.util.Objects;
import java.util.Optional;

/**
 * Classe para manipular a entidade {@link Product}.
 * @author thiago leite
 */
public class ProductBusiness {

    /**
     * {@inheritDoc}.
     */
    private Bank dataBank;

    /**
     * Construtor.
     * @param bank Banco de dados para ter armazenar e ter acesso os produtos
     */
    public ProductBusiness(Bank bank) {
        this.dataBank = bank;
    }

    /**
     * Salva um novo produto(livro ou caderno) na loja.
     * @param newProduct Livro ou caderno que pode ser vendido
     */
    public void save(Product newProduct) {

        String code = "PR%04d";
        code = String.format(code, dataBank.getProducts().length);
        newProduct.setCode(code);

        boolean repeatedProduct = false;
        for (Product product : dataBank.getProducts()) {
            if (product.getCode().equals(newProduct.getCode())) {
                repeatedProduct = true;
                System.out.println("Product already registered.");
                break;
            }
        }

        if (!repeatedProduct) {
            this.dataBank.addProduct(newProduct);
            System.out.println("Product successfully registered.");
        }
    }

    /**
     * Exclui um produto pelo código de cadastro.
     * @param code Código de cadastro do produto
     */
    public void delete(String code) {

        int index = 0;
        for (int i = 0; i < dataBank.getProducts().length; i++) {
            Product product = dataBank.getProducts()[i];
            if (product.getCode().equals(code)){
                dataBank.removeProduct(i);
                System.out.println("Product removed with success");
            }
        }

    }

    /**
     * Obtem um produto a partir de seu código de cadastro.
     * @param code Código de cadastro do produto
     * @return Optional indicando a existência ou não do Produto
     */
    public Optional<Product> inquire(String code) {

        for (Product product : dataBank.getProducts()) {

            if (product.getCode().equalsIgnoreCase(code)) {
                return  Optional.of(product);
            }
        }

        return Optional.empty();
    }

    /**
     * Lista todos os produtos cadastrados.
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
