

/**
 * https://www.tomdalling.com/blog/software-design/fizzbuzz-in-too-much-detail/
 * Write a program that prints the numbers from 1 to 100. But for multiples of three print "Fizz" instead of the number and for the multiples of five print "Buzz". For numbers which are multiples of both three and five print "FizzBuzz".
 *
 * Code readability tips:
 * Tips: Do the computation (i % 5, i%3) once and store it for reuse (Don't Repeat Yourself)
 * Tip: Stor FIZZ and Buzz them as parameters. (Decouple program logic from parameters)
 * Tip: Define isDivisibleBy method instead of direct computation (might need to change)
 Tip: Define a more generric satisfiesCriteria method, and pass it a function from the input paramets to set criteria. 
 
 * if(i % (5*3) == 0){Most people would have used 15 which I believe to be wrong. The way you've done it, it's clear that the first case is a multiple of the other two cases. 
 */

public class FizzBuzz{


	private static final String DIVISIBLE_BY_FIFTEEN = "FizzBuzz";
	private static final String DIVISIBLE_BY_THREE = "Fizz";
	private static final String DIVISIBLE_BY_FIVE = "Buzz";

	public static void run(){

		int n = 0;
		
		while(n < 101){

			if(n % 3 == 0){
				if (n % 5 == 0){
					System.out.println(DIVISIBLE_BY_FIFTEEN);
				} else {
					System.out.println(DIVISIBLE_BY_THREE); 
				}
			} else if (n % 5 == 0){
				System.out.println(DIVISIBLE_BY_FIVE); 
			} else {
				System.out.println(n);
			}
			n++;
		}
		

	}



	public static void main(String args[]){
		run();
	}
}