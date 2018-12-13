/** https://www.programering.com/a/MTM0IjNwATM.html
 Wrong practice: Putting sets into an array indexed by the type's ordinal
 Here we put the set {Marked, Unmarked} into an array indexed by the typ's ordinal
 Should use: private enum Marker{
        CROSSED, UNCROSSED;
    }

    And then Marker[] crossedOut

    and if (crossedOut[i] == Marker.CROSSED)
    **/


public class Eratosthenes{
	

	public static void printAllPrimes(int n){

		boolean[] isVisited= new boolean[n];

		for(int i = 2; i < n; i++){
			if(isVisited[i] == false){
				System.out.println(i);
				int k = 1;
				while(i*k < n){
					isVisited[i*k] = true;
					k++;
				}
			}
		}

	}


	public static void main(String[] args){
		printAllPrimes(21);
	}
}