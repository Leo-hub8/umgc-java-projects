// Leonard, Caleb CMIS 141/6380 03/09/2021
/*In this program I displayed a menu to input data 
for a up to 100 employees. It prompts the user amount of employees' 
and to add name,ID, and Salary. Then it will display all employees'.
You can retrieve specific employee data by adding ID number. Last
you can enter a salary range that will provide employees' within
that range.*/
import java.util.Scanner;

public class ProjectFinal {
	static Scanner scan = new Scanner(System.in);//globally stated String's and integers.
	static int numberOfEmployees = 0;
	static String name[] = new String [100];
	static int id[] = new int [100];
	static int salary[] = new int[100];
	//Method to print Menu
	public static void printMenu() { // display menu to choose input or output of data
		System.out.println("");
		System.out.println("Menu");
		System.out.println("1: Load employees' data.");
		System.out.println("2: Add new employee.");
		System.out.println("3: Display all employees.");
		System.out.println("4: Retrieve specific employee's Data.");
		System.out.println("5: Retrieve employees with salaries based on range.");
		System.out.println("6: Exit");
	}
	// Method for employee data
	public static int loadEmployees() {
		//prompt question for user to enter data. 
		System.out.println("Number of employees to load: ");
		int emp = scan.nextInt();
		scan.nextLine();
			
		for (int i = 0; i < emp; i++){
			//Data information
			System.out.println("Employee " + (i+1));
			System.out.print("Enter Employees name:  ");
			name[i] = scan.next();
			scan.nextLine();
			System.out.print("Enter ID (5 digit #): ");
			id[i] = scan.nextInt();
			scan.nextLine();
			System.out.print("Enter salary: ");
			salary[i] = scan.nextInt();
			scan.nextLine();
			numberOfEmployees++;
		}
		return emp;
	}
	// Method for adding new employee.
	public static int addEmployee() {
		System.out.println("");
		System.out.println("Enter new employee data.");
		System.out.println("");
		
		System.out.println("Enter Employees name:  ");
		name[numberOfEmployees] = scan.nextLine();
		System.out.println("Enter ID (5 digit #): ");
		id[numberOfEmployees] = scan.nextInt();
		System.out.println("Enter salary: ");
		salary[numberOfEmployees] = scan.nextInt();
		numberOfEmployees++;
		
		return numberOfEmployees;
	}
	// Method to print all data enter for all employees.
	public static void printAllEmployees() {
		for (int i = 0; i < numberOfEmployees; i++){
			System.out.println("Name: " + name[i] + " ID: " + id[i] + " Salary: " + salary[i]);
		}
	}
	// Method to get employee using ID number. 
	public static void printEmployee( ) {
		System.out.println("Enter ID (5 digit #): ");
		int employeeNumber = scan.nextInt();
		boolean found = false; 
		
		for (int i = 0; i < numberOfEmployees; i++){
			if (id[i] == employeeNumber) {
				found = true;
				System.out.println("Name " + name[i] + " ID " + id[i] + " Salary " + salary[i]);
			}
		}
		if (found == false ) {
			System.out.println("No Employee found with ID " + employeeNumber);
		}
	}
	// Method the prompts user to enter a high and low salary.
	// Result is displaying employees' data between those paramitors. 
	public static void printEmployeeRange( ) {
		System.out.println("Enter Salary Low End: ");
		int lowRange = scan.nextInt();
		System.out.println("Enter Salary High End: ");
		int highRange = scan.nextInt();
		boolean found = false; 
		
		for (int i = 0; i < numberOfEmployees; i++){
			if ((salary[i] <= highRange) && (salary[i] >= lowRange)) {
				found = true;
				System.out.println("Name " + name[i] + " ID " + id[i] + " Salary " + salary[i]);
			}
		}
		if (found == false ) {
			System.out.println("No Employees found in range  " + lowRange + " - " + highRange);
		}
	}
	// Method that translates menu selection into program to perform. 
	public static void handleMenuSelection(int choice) {
		
		switch (choice) {
		
		case 1 : 
			loadEmployees();
			break;
		case 2 : 
			addEmployee();
			break;
		case 3 : 
			System.out.println("");
			printAllEmployees();
			System.out.println("");
			break;
		case 4 :
			printEmployee();
			break;
		case 5 :
			printEmployeeRange();
			break;			
		case 6 :	
			System.out.print("Entered 6 ");
			break;
		default: 	
			System.out.println("Not a valid selection");
			System.out.println("");

			break;
		}
	}
	public static int getSelection() {
	int userSelection;
	
	System.out.print("Enter a Selection: ");
	userSelection = scan.nextInt();
	scan.nextLine();
	return userSelection;
}
	public static void main(String[] args) {
	
		int menuSelection;
		
		do {
			printMenu();
			
			menuSelection = getSelection();
			
			handleMenuSelection(menuSelection);
			
			
		} while(menuSelection != 6);
		System.out.print("Execution Complete ");
	}

}
