

public class PrintOddUpto{
	

// Write a function that sums up integers from a text file, one int per line.
	public static void print(int n){
		int i = 0;
		while(i <= n){
			if( i % 2 == 1){
				System.out.printf("%4d", i);
			}
			i++;
		}
	}

	public static void printBetter(int n){

		for (int i = 1; i <= n; i +=2){
			System.out.printf("%4d", i);
		}
	}		

	


	
	public static void main(String args []){

		printBetter(99);
		
	}	
}