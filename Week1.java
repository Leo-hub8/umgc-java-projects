//CMSC 350 Data Structures and Analysis
//Week 1
//Name: Leonard, Caleb
//January 18, 2022

public class Week1 {
	//First function that represents f(n)
	public static int fn1(int n) {
		int x = (int) (100*(Math.pow(2, n)+5*n));
		return x;
	}
	//Second function that represents g(n)
	public static int fn2(int n) {
		int x = (int) (Math.pow(2, n));
		return x;
	}

	public static void main(String[] args) {
		
		int n = 0;
		// this will print out the table of how the items are set up.
		System.out.println("\nn \tf(n) = 100*2^n+5*n\tg(n) = 2^n");
		System.out.println("--------------------------------------------");
		
		 //While loop will continue to solve the functions until fn2(n)/g(n) becomes larger than fn1(n)/ f(n)
		while (fn1(n)>fn2(n)) {
			
			if(n%2 == 0)
				System.out.println(n + "        " + fn1(n) + "                  " + fn2(n));
			n++;	
			
			}
       //This will print out the last numbers when g(n) overtakes f(n)and will state the number of n that g(n) overtook f(n).
		System.out.println(n + "        " + fn1(n) + "                  " + fn2(n));
		System.out.println("\tg(n) overtakes f(n) at n = " + n);
		
		}
	}