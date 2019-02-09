

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;



class Problem {

	private static List<Card> convertLineToCards(String line){
		String[] cardStrings = line.split(" ");
		List<Card> cardList = new ArrayList<>();
		for (String s: cardStrings){
			Card card = new Card(s);
			cardList.add(card);
		}
		return cardList;
	}
		
	public static void main(String[] args){
		int count = 0;
		
		try {
			Path path = FileSystems.getDefault().getPath("poker.txt");
			List<String> lines = Files.readAllLines(path); // default: StandardCharsets.UTF_8
			List<Card> cardList, firstPlayerCards, secondPlayerCards;
			Hand playerOneHand, playerTwoHand;
			for (String line : lines){
				cardList = convertLineToCards(line);
				firstPlayerCards = cardList.subList(0, 5);
        		playerOneHand = new Hand(firstPlayerCards);

       			secondPlayerCards = cardList.subList(5, 10);
       			playerTwoHand = new Hand(secondPlayerCards);

       			if (playerOneHand.compareTo(playerTwoHand) > 0){
       				count++;
       			}
			}
			System.out.println("Player 1 wins " + count + " times");
		} catch (FileNotFoundException e){
			System.err.println("cannot open file");
		} catch (IOException e){
			System.err.println(e);
		}
		

	}
}





		/**
		 
		Java views each file as a sequential stream of bytes. When a file is opened, an object is created and a stream is associated with the object. The streams provide communication channels between a program and a particular file or device.

		BufferedReader file = null;// A text file can also be read using the Scanner class. Using the Scanner offers the advantage for text processing of various data formats.. Scanner has a lot of other features, with support for regular expressions, delimiter definitions, skipping input, searching in a line, reading from different inputs and others..

		try {
			file = new BufferedReader(new FileReader("poker.txt"));//The FileReader class represents an input file that contains character data. The class throws FileNotFoundException (checked exception), so you have to wrap try and catch block around each time you open a file. 
			//For the efficient reading, we use the BufferedReader class. It provides the method readLine() that allows to read the entire text line (a line is a sequence of characters terminated either by '\n' or '\r' or their combination). The method returns a String, or null if the end of the stream has been reached. The readLine() method throws IOException, so you have to handle it.
			//String line = file.readLine()

		} catch (FileNotFoundException e){
			System.err.println("cannot open file");
			// Object System.in (standard input stream object) enables a program to input bytes from the keyboard, (standard input stream object not to be confused with: "the standard input stream" The string of characters that you type in the terminal window: after the command line.)  we can redirect standard input so that a program reads data from a file instead of the terminal application. For example, java Average 4 < input.txt
	
			// object System.out (standard output stream object) enables a program to output data to the screen, and object System.err (standard error stream object) enables a program to output error messages to the screen. System.out can be redirected to send its output to a file. By convention, System.err stream is used to display error messages.
		} catch (IOException e ){
			System.exit(0)
		} finally {
			try {
				if (file != null){
					file.close();
				}
			} catch (IOException e){
				System.err.println(e);
			}
		}

		
		  Alternative approach
		  Scanner scanner = null;
		  try {
		  	scanner = new Scanner(new File("poker.txt"));
		  } catch (FileNotFoundException e) {
		  		System.err.println("cannot open file");
		  } finally {
		  }
		 
		 
		  Or
		 
		  InputStreamReader in = new InputStreamReader(new FileInputStream ("poker.txt"));
		  BufferedReader reader = new BufferedReader(in);
		 
		* 
		 */
	
