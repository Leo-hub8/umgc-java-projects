//Leonard, Caleb CMIS 242/ 7380, 03/17/2021
//Class called MyDogs
public class MyDogs {

	static int DogsOwne; // Class Variable
	
	// attribute
	String name;
	String breed;
	int weight;
	int age;
	
	// constructor
	public MyDogs(String name, String breed, int weight, int age) {
		this.name = name;
		this.breed = breed;
		this.weight = weight;
		this.age = age;
	}

	// method
		public void display() {
		System.out.println( name + " is a " + breed + " who weighs " + weight + "lbs, and is " + age + " years old.");
	}
}