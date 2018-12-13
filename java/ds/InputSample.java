import java.io.*;

class InputSample
{
	public static void main(String [] args) throws IOException		//throws IOException should be there in EVERY function that has this "BufferedReader" object and which uses readLine() function. Also, any function that calls such a function should also have "throws IOException"
	{
		BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));	//System.in is the keyboard. InputStreamReader creates an input-stream reader from the keyboard, and the buffered reader creates a 'buffered' reader of that input-stream reader.
		//Note: Preferably create ONLY ONE BufferedReader object. I've had experience of getting weird shit when you create more than one (like in diff funcs). If you need to get input in more than one func, i suggest you create this globally. I believe it does some weird reinitializing shit when you create a second such object, but not sure. Its happened only in programming contests with me.
		String instr;
		int inttobeinput;
		float floattobeinput;
		
		System.out.println("Enter an integer:");	//prompt for input
		instr = keyin.readLine();			//read string input from keyboard till enter is pressed
		inttobeinput = Integer.parseInt(instr);		//convert string to integer. Very useful function in general.
		
		System.out.print("Enter a float: ");		//you could have done this expt yourself. have ".print" instead of ".println"
		instr = keyin.readLine();			//and see that readLine() does not read the "Enter a float:" even though its on the same line as the inputted float. Enjoy!
		floattobeinput = Float.parseFloat(instr);	//convert string to float...
		
		System.out.println (inttobeinput + "/2 = " + (inttobeinput/2));		//to show its stored as integer
		System.out.println (floattobeinput + "/2 = " + (floattobeinput/2));	//to show its stored as float
	}
}
