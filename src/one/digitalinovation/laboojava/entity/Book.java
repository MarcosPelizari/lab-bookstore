package one.digitalinovation.laboojava.entity;

import one.digitalinovation.laboojava.entity.constantes.Genre;

/**
 * Classe que representa um livro, qual é uma especialização de um produto da loja.
 * @author thiago leite
 */
public class Book extends Product {

    /**
     * Nome do livro.
     */
    private String name;

    /**
     * Gênero do livro.
     */
    private Genre genre;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public double calculateShipping() {
        return (getPrice() * getQuantity()) * (1 + genre.getFactor());
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", genre=" + genre + '\'' +
                ", code='" + getCode() + '\'' +
                ", price='" + getPrice() + '\'' +
                '}';
    }
}
