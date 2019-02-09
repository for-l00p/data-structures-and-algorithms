public class MultiplicationTable{
	

	public static void printSquareTable(int n){

		for (int i = 1; i <= n; i++){
			for (int j = 1, j <=n; j++){
				System.out.printf(i*j);
			}
		}


	}

	public static void main(String args []){
		int n;
		n = 12;
		printSquareTable(n);
	}	
}