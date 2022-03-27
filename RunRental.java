import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;



public class RunRental {
	
	static Scanner scan = new Scanner(System.in);
    static Manager mgr = new Manager();

    static String baseDir = "c:/tmp-umuc";
    static String directory = baseDir;

    File directoryPath = new File(directory);
    
	public static void writeLine(String prompt) {
		System.out.println(prompt);
	}
	
	public static int readInteger(String prompt) {
		int inInt = 0;
		writeLine(prompt);
		boolean found = false;
		while (!found) {
			if(scan.hasNextInt()){
				inInt = scan.nextInt();
				scan.nextLine();
				writeLine("");
				found = true;
			}else{
				String inString = scan.nextLine();
				writeLine("Sorry [" + inString + "] is not an Integer");
				writeLine(prompt);
			}
		}
		return inInt;
	}

	public static double readDouble(String prompt) {
		writeLine(prompt);
		double inDouble = scan.nextDouble();
		writeLine("");
		
		return inDouble;
	}

	public static String readString(String prompt) {
		writeLine(prompt);
		String inString = scan.nextLine();
		writeLine("");

		return inString;
	}

	public static int getResponse() {
		
		displayMenu();
		int response = readInteger("Enter your selection : ");
		switch (response) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 9:
				break;
			default: {
				break;
			}		
		}
		return response;
	}

	private static void handleResponse(int response) {
		// TODO Auto-generated method stub
		writeLine("");

		switch (response) {
			case 1: {
				writeLine("Load Media objects ");
				loadMediaObject();
				break;
			}
			case 2:{
				writeLine("Find Media object... ");
				findMediaObject();
				break;
			}
			case 3: {
				writeLine("Rent Media object... ");
				rentMediaObject();
				break;
			}
			case 4: {
				writeLine("4... ");
				mgr.displayAllMedia();
				break;
			}
			case 5: {
				writeLine("5... ");
				break;
			}
			case 6: {
				writeLine("6... ");
				break;
			}
			case 9:{
				writeLine("Quit: " + response);
				break;
			}
			default: {
				writeLine(response + " is an Invalid entry, please choose 1, 2, 3, or 9");
			}		
		}
	}

	private static void createMediaFiles() {
		
		// create instances of the ebook and display
		EBook ebook = new EBook(123, "Forever Young", 2018, 0, 20);
		String rentalFee = String.format(" Rental fee=$%.2f", ebook.calculateRentalFee());
		writeLine("EBook " + ebook.toString() + rentalFee );
		writeLine("");

		// create instances of the music cd and display
		MusicCD cd = new MusicCD(124, "Forever Young", 2020, 0, 114);
		rentalFee = String.format(" Rental fee=$%.2f" ,cd.calculateRentalFee());
		writeLine("MusicCD " + cd.toString() + rentalFee );

		// create instances of the music cd and display
		MusicCD cd2 = new MusicCD(125, "Beyond Today", 2020, 0, 114);
		rentalFee = String.format(" Rental fee=$%.2f" ,cd.calculateRentalFee());
		writeLine("MusicCD " + cd.toString() + rentalFee );

		cd.setLength(120);
		writeLine("Changing MusicCD length to 120: ");
		writeLine("MusicCD " + cd.toString() + rentalFee );
		writeLine("");
		
		// create instances of the movie dvd and display
		MovieDVD dvd = new MovieDVD(126, "After Tomorrow", 2020, 0, 120);
		rentalFee = String.format(" Rental fee=$%.2f" ,dvd.calculateRentalFee());
		writeLine("MovieDVD " + dvd.toString() + rentalFee );
		
		ebook.setNumChapters(25);
		writeLine("Changing EBook chapters to 25: ");		
		writeLine("MovieDVD " + dvd.toString() + rentalFee );
		writeLine("");

		mgr.addMedia(ebook);
		mgr.addMedia(cd);
		mgr.addMedia(cd2);
		mgr.addMedia(dvd);
		
        try {
            // create pet files in directory baseDir
        	mgr.createMediaFiles(baseDir);
        } catch (IOException e) {
            e.printStackTrace();  // just print trace if there are issues
        }
	
	}
	
	private static void rentMediaObject() {
		// TODO Auto-generated method stub
		//writeLine("Inside Rent Media object... ");
		
		int id = readInteger("Enter the id: ");
		try {
			boolean checkedOut = mgr.checkOutMedia(id);
			if (checkedOut) {
				mgr.createMediaFiles(directory);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void findMediaObject() {
		// TODO Auto-generated method stub
		//writeLine("Inside Find Media object... ");
		
		try {
			String title = readString("What title are you looking for? : ");
			mgr.findMedia(title);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void loadMediaObject() {
		// TODO Auto-generated method stub
		//writeLine("Inside Load Media object... ");
				
		try {
			String directory = readString("Enter path (directory) where to load from: ");
			mgr.loadMediaList(directory);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void displayMenu() {		
		writeLine("");
		writeLine("Welcome to Media Rental System");
		writeLine("1: Load Media objects...");
		writeLine("2: Find Media object...");
		writeLine("3: Rent Media object...");
		writeLine("9: Quit");
		writeLine("");
	}

	public static void main(String[] args) {
		
		createMediaFiles();
		
		int response = getResponse();
		
		while (response != 9) {
			handleResponse(response);
			response = getResponse();
		}
		writeLine("");
		writeLine("Thank you for using the program. Goodbye! ");

	}
}

