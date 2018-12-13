
import java.util.*;
public class Exponent{

	public static int calculate(int num, int power){

		if(power == 0){
			return 1;
		} else {
			return num*calculate(num, (power - 1));
		}

	}

	public static int iterativeCalc(int num, int power){

		int answer = 1;
		while(power > 0){
			answer = answer*num;
			power--;
		}

		return answer;
	}


	public static int fastExp(int num, int power, int temp){

		//System.out.println("called on num: " + num + " and power: " + power);
		//
		if(power == 1){
			temp = num;
			
		} else if (power % 2 == 1) {
			//System.out.println("multiplying by: " + num);
			temp =  num*fastExp(num, (power - 1), temp);
			
		} else {
			temp =  (int)Math.pow((fastExp(num, (power/2), temp)), 2);
		}
		//System.out.println("temp is now: " + temp);
		return temp;
	}
	
	public static void main(String[] args){

		long startTime = System.nanoTime();
		System.out.println(calculate(3, 10));
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("Time taken by recursive: " + totalTime);
		long startTime2 = System.nanoTime();
		System.out.println(iterativeCalc(3, 10));
		long endTime2 = System.nanoTime();
		long totalTime2 = endTime2 - startTime2;
		System.out.println("Time taken by iterative: " + totalTime2);
		long startTime3 = System.nanoTime();
		System.out.println(fastExp(3, 10, 0));
		long endTime3 = System.nanoTime();
		long totalTime3 = endTime3 - startTime3;
		System.out.println("Time taken by fastExp: " + totalTime3);







	}


}