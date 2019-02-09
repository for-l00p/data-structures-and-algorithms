import java.io.*;

public class Sum{
	

// Write a function that sums up integers from a text file, one int per line.
	public static void sumFile(String fileName){

		try {

			int total = 0;
			BufferedReader in = new BufferedReader (new FileReader(fileName));
			for (String s = in.readLine(); s != null; s = in.readLine()){
				total += Integer.parseInt(s);
			}

			System.out.println(total);

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	


	}

	public static void main(String args []){

		sumFile("data.txt");
		
	}	
}