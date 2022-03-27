//Leonard, Caleb CMIS 242/ 7380, 03/17/2021
/*In this program I used an object (dog) with four attributes
and a instance method in my class file. In my main method
I created a instance of the class and assigned a value.*/
public class TestDogs {

	public static void main(String[] args) {
		
		MyDogs.DogsOwne = 2; //calls my class and class variable with a provied number of two.
		
		MyDogs dog1 = new MyDogs("Bear", "Chocolate Lab", 105, 2);
		dog1.display();// calls method in class file and prints out information
		// on dog1.
		
		System.out.println();
		
		MyDogs dog2 = new MyDogs("Cookie (Woo)", "Husky / Poodle", 20, 1);
		dog2.display(); // calls method in class file and prints out information
		// on dog2. 
		
	}

}
  