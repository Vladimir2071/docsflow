package lva.docsflow;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import lva.docsflow.catalogs.*;
import lva.docsflow.parsingXML.*;
import lva.docsflow.catalogsDB.*;

import java.util.Scanner;
import java.util.ArrayList;

public class App {
	public static void main(String[] args) {

		ArrayList<Products> listProducts = new ArrayList<Products>(200);
		int num = 0;

		Scanner in = new Scanner(System.in);

		while (true) {
			System.out.println("1. Загрузить");
			System.out.println("2. Изменить");
			System.out.println("3. Сохранить");
			System.out.println("9. Завершить работу");
			System.out.print("");
			System.out.print("Input a item menu: ");

			try {
				num = in.nextInt();
			} catch (Exception e) {
				e.printStackTrace();
			}

			switch (num) {
			case 1:
				listProducts = getProduct();
				break;
			case 2:

				break;
			case 3:
				saveProduct(listProducts);
				break;
			case 9:
				System.out.println("В списке " + listProducts.size());
				System.exit(0);
			default:
				System.out.println("Выберите корректный пункт меню");
			}
		}
	}
	
	public static void saveProduct(ArrayList<Products> arrProducts) {

		ConnectDB connDB = new ConnectDB("127.0.0.1", "1433", "docflow", "sa", "Rjnjcnhjaf2050");
		Connection conn = connDB.connectDB();
		if (conn == null) {
			System.out.println("Error connecting!");
			return;
		} else {
			System.out.println("Success connected!");
		}

		try {
			LoadProductsToDB loadProductsToDB = new LoadProductsToDB(conn);
			int result = loadProductsToDB.insertArrProducts(arrProducts);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Success connected!");

	}

	public static ArrayList<Products> getProduct() {

		ArrayList<Products> arrProducts = new ArrayList<Products>();
		LoadProductsFromXML loadProductsFromXML = new LoadProductsFromXML("D://XML/Products.xml");
				 
		Scanner in = new Scanner(System.in);

		System.out.println("1. из XML");
		System.out.println("2. из DB");
		System.out.println("3. из консоли");

		System.out.println("выбкрите пункт меню");
		int num = in.nextInt();

		switch (num) {
		case 1:
			// read XML ArrayList<Product> catalogProducts = new ArrayList<Product>();
			arrProducts = loadProductsFromXML.ReadProductsFromXML();
			break;
		case 2:
			break;
		case 3:
			try {
				loadFromConsole(arrProducts);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		default:
			System.out.println("Вы выбрали не корректный пункт");
		}
		System.out.println("В списке элементов" + arrProducts.size());
		return arrProducts;
	}

	public static void loadFromConsole(ArrayList<Products> arrProducts) {

		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.println("1. добавить");
			System.out.println("9. выход");

			System.out.println("Введите id");
			int id = in.nextInt();
			String whitespace = in.nextLine();

			System.out.println("Введите code");
			String code = in.nextLine();

			System.out.println("Введите name");
			String name = in.nextLine();

			System.out.println("Введите qty");
			byte qty = in.nextByte();

			System.out.println("Введите volume");
			float volume = in.nextFloat();

			System.out.println("Введите weight");
			float weight = in.nextFloat();

			Products product = new Products(id, code, name, qty, volume, weight);
			arrProducts.add(product);
		}
	}

	public static void editProduct() {

	}

	public static void putProductd(ArrayList<Products> arrGoods) {

	}

	public static int deleteProduct() {

		return 1;
	}

	public static int findOne(int id) {

		return 1;
	}
}
