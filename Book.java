
import java.util.Scanner;

public class Book {
 static Scanner scan = new Scanner(System.in);
 
	//attributes
	static private int id;
	static private String title;
	static private double price;
	
	//constructor
	public Book(int id, String title, double price) {
		
	}
	
	public void displayMenu() {
		System.out.println("");
		System.out.println("Menu");
		System.out.println("1: Add book");
		System.out.println("2: Remove book");
		System.out.println("3: Find book");
		System.out.println("4: Display all books");
		System.out.println("9: Exit program");
	}
	
	public static int addBook(int id, double price, String title) {
		System.out.println("What is the book id (integer value)? : ");
		id = scan.nextInt();
		System.out.println("");
		System.out.println("What is the book price (double value)? : ");
		price = scan.nextDouble();
		System.out.println("");
		System.out.println("What is the book title? : ");
		title = scan.nextLine();
		
		
		return id;
	}
	public static void handleMenuSelection(int choice) {
		
		switch (choice) {
		
		case 1 :
			
		case 2 :
			
		case 3 :
			
		case 4 :
			
		case 9 :
			
		default:
			System.out.println("Not a valid selection");
			System.out.println("");
			
			break;
		
		}
	}
}
