package one.digitalinovation.laboojava.entity.constantes;

/**
 * Gêneros dos livros vendidos.
 * @author thiago leite
 */
public enum Genre {

    DRAMA(15),

    SUSPENSE(10),

    ROMANCE(5);

    private double factor;

    /**
     * Construtor.
     * @param factor Valor por tipo que influencia no cálculo do frete.
     */
    Genre(double factor) {
        this.factor = factor / 100;
    }

    public double getFactor() {
        return factor;
    }
}
