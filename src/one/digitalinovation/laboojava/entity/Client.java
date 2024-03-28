package one.digitalinovation.laboojava.entity;

/**
 * Classe que representa a entidade cliente. Este pode fazer um pedido.
 * @author thiago leite
 */
public class Client {

    /**
     * Nome completo do cliente.
     */
    private String name;

    /**
     * Número de CPF(Cadastro de Pessoa Física) do cliente.
     */
    private String id;

//    public Client() {
//        this.name = "Fulano";
//        this.id = "123456789011";
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Client{ name='" + name + "\'" +
                ", id='" + id + "'}";
    }
}
