package one.digitalinovation.laboojava.utility;

import one.digitalinovation.laboojava.dba.Bank;
import one.digitalinovation.laboojava.entity.*;
import one.digitalinovation.laboojava.entity.constantes.Genre;
import one.digitalinovation.laboojava.business.ProductBusiness;
import one.digitalinovation.laboojava.entity.Notebook.NoteBook;
import one.digitalinovation.laboojava.entity.Client;

import java.util.Optional;
import java.util.Scanner;

/**
 * Classe utilitária para auxiliar na leitura de entradas de dados via teclado.
 * @author thiago leite
 */
public final class DataReader {

	/**
	 * Classe do Java para manipular entradas via teclado.
	 */
	private static Scanner scanner;
	
	static {
		scanner = new Scanner(System.in);
	}

	/**
	 * Ler um dado específico
	 * @return Dado lido
	 */
	public static String readData() {
		
		String text = scanner.nextLine();
		
		return text;
	}

	/**
	 * Ler os dados do livro a ser cadastrado.
	 * @return Um livro a partir dos dados de entrada
	 */
	public static Book readBook() {

		System.out.println("Registering book...");
		Book book = new Book();

		System.out.println("Type the name");
		String name = readData();
		book.setName(name);

		System.out.println("Type the genre: DRAMA, SUSPENSE, ROMANCE");
		String genre = readData();
		book.setGenre(Genre.valueOf(genre.toUpperCase()));

		System.out.println("Type the price(default 0.0)");
		String price = readData();
		book.setPrice(Double.parseDouble(price));

		return book;
	}

	/**
	 * Ler os dados do caderno a ser cadastrado.
	 * @return Um caderno a partir dos dados de entrada
	 */
	public static Notebook readNotebook() {
		System.out.println("Registering notebook....");
		Notebook notebook = new Notebook();

		System.out.println("Type the quantiy of material: M2, M5, M10");
		String name = readData();
		try {
			NoteBook type = NoteBook.valueOf(name.toUpperCase());
			notebook.setNotebookType(type);
		} catch (Exception e) {
			System.out.println("Notebook invalid");
		}

		System.out.println("Type the price(default 0.0):");
		String price = readData();
		notebook.setPrice(Double.parseDouble(price));

		return notebook;
	}

	/**
	 * Ler os dados do pedido e retorna um objeto a partir destes.
	 * @return Um pedido a partir dos dados de entrada
	 */
	public static Order readOrder(Bank bank) {

		ProductBusiness productBusiness = new ProductBusiness(bank);

		System.out.println("Registering order...");
		Order order = new Order();

		String option = "s";
		do {

			System.out.println("Type the code of the product(book/notebook)");
			String code = readData();

			Optional<Product> result = productBusiness.inquire(code);
			if (result.isPresent()) {

				Product product = result.get();

				System.out.println("How many? ");
				String amount = readData();
				product.setAmount(Integer.parseInt(amount));

				order.getProducts().add(product);
			} else {
				System.out.println("Produto inexistente. Escolha um produto válido");
			}

			System.out.println("Deseja selecionar mais um produto? s/n");
			option = readData();
		} while("s".equals(option));

		return order;
	}

	/**
	 * Ler os dados do cupom e retorna um objeto a partir destes.
	 * @return O cupom a partir dos dados de entrada
	 */
	public static Optional<Coupon> readCoupon(Bank bank) {

		System.out.println("Caso queira utilizar algum cupom escolha entre: CUPOM2, CUPOM5, CUPOM7. Se não desejar, deixe em branco.");

		String discount = readData();

		for (Coupon coupon : bank.getCoupons()) {
			if (coupon.getCode().equalsIgnoreCase(discount)) {
				return Optional.of(coupon);
			}
		}

		return Optional.empty();
	}

	public static Client readClient() {

		System.out.println("Registering book...");
		Client client = new Client();

		System.out.println("What's your name?");
		String name = readData();
		client.setName(name);

		System.out.println("Type your ID");
		String id = readData();
		client.setId(id);

		return client;
	}
}
