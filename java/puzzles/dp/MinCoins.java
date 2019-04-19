package puzzles.dp;

import java.util.*;

public class MinCoins {

	public static int findMinCoinsChange2(int amount, int[] denominations) {


      Map <String, Integer> cache = new HashMap<>(); 
      Map <String, Integer> coinCombination = new HashMap<>(); 

      // If the amount cannot be made, result returns Integer.MAX_VALUE
      // cache corresponds to memoizing the results (DP table in iterative implementations). 
      //  coinCombination corresponds to storing the decision to reconstrucct the optimal solution
      //  Instead of using Maps with the name of subproblem as key, we could use a DP table with the index representing the subproblem. 
      int result = findMinCoinsChange(amount, denominations, 0, cache, coinCombination); 
      //System.out.println("Result: " + result);
      if (result != Integer.MAX_VALUE){
         System.out.println("Optimal solution reconstructed with decision map:");
        printCoinCombination1(amount, denominations, coinCombination);
        System.out.println("Optimal solution reconstructed from cache");
        printCoinCombination2(amount, denominations, cache);
      }  else {
        System.out.println("No coinCombination possible");
        return -1;
      } 
      return result;
   }




   private static int findMinCoinsChange(int amount, int[] denominations, int useCoinsStartingFrom, Map<String, Integer> cache, Map<String, Integer> coinCombination){

        //Let cache[i][j] store the number of ways of forming i using coins in denominations[j....m-1]. Here cache[0][j] = 1 for all j (since there is 1 ways of forming 0 using the coins). cache[i][m] = 0.
      
        String key = amount + "-" + useCoinsStartingFrom;
        int result = Integer.MAX_VALUE; // We chose this instead of -1. Because in the case when one of the subproblems does not have a solution (goes out of bounds), we want a simple comparison. 

        //Check if memoized
        if (cache.containsKey(key)){
          result =  cache.get(key);
          return result;
        }

        //Not memoized. Check if base case: # of coins needed to make less than zero amount using the coins, or making  an amount using no coins: it's not possible
        if (useCoinsStartingFrom >= denominations.length || amount < 0) {       
           cache.put(key, result); 
           return result;
        }

        //Not memoized. Check if base case: number of coins need to make zero using finite coins: 0
        if (amount == 0 && useCoinsStartingFrom < denominations.length) { 
            result = 0;
            cache.put(key, result);     
            return result;
        }

         // amount >= 0, 0 <= useCoinsStartingFrom < n;

        int resultWhenCoinIsUsed = findMinCoinsChange(amount - denominations[useCoinsStartingFrom], denominations, useCoinsStartingFrom, cache, coinCombination);
        
        int resultWhenCoinIsNotUsed = findMinCoinsChange(amount, denominations, useCoinsStartingFrom + 1, cache, coinCombination);  

        
       
        if (resultWhenCoinIsUsed < resultWhenCoinIsNotUsed){
              result = 1 + resultWhenCoinIsUsed;
              coinCombination.put(key, 1 );
           } else {
              result = resultWhenCoinIsNotUsed;
              coinCombination.put(key, 0 );
        }
        if (result == 0) throw new AssertionError(" ZERO RESULT! " + "Key: " + key + "left :" + resultWhenCoinIsUsed + "right: " + resultWhenCoinIsNotUsed );

        cache.put(key, result); 

        return result;
      
   }

   //coin combination map stores the decisions for each subproblem, needed to recover the optimal solution
   private static void printCoinCombination1(int amount,int[] denominations, Map <String, Integer> coinCombination){

       
        int[] minCoins = new int[denominations.length];


        int remaining = amount;
        int currentIndex = 0;
        int includeCurrentIndexCoin = 0;

        String key = remaining + "-" + String.valueOf(currentIndex);

        while(remaining > 0){
          if (!coinCombination.containsKey(key)){
            throw new AssertionError("Missing:" + key);
          }
          includeCurrentIndexCoin = coinCombination.get(key);
          if (includeCurrentIndexCoin == 1){
             minCoins[currentIndex]++;
             remaining = remaining - denominations[currentIndex];
          } else {
            currentIndex++;
          }
          
          key = remaining + "-" + String.valueOf(currentIndex);
        }

        for (int i = 0; i < denominations.length; i++){
          System.out.println(denominations[i] + ": " + minCoins[i]);
        }        
   }


//We can recover the optimal solution from the solution to the subproblems directly, without the need for a seaprate map. 
      private static void printCoinCombination2(int amount,int[] denominations, Map <String, Integer> cache){

        
        int[] minCoins = new int[denominations.length];
        int remaining = amount;
        int currentIndex = 0;
        int currentMin, minWithCurrentCoin, minWithoutCurrentCoin;

      
          String  key = remaining + "-" + String.valueOf(currentIndex);
          String  keyForWithCoin = (remaining-denominations[currentIndex]) + "-" + currentIndex;
          String  keyForWithoutCoin = remaining + "-" + (currentIndex+1);

        while(remaining > 0){
            
          currentMin = cache.get(key);
          minWithCurrentCoin = cache.get(keyForWithCoin);
          minWithoutCurrentCoin = cache.get(keyForWithoutCoin);

          if (currentMin == 1 + minWithCurrentCoin){
             minCoins[currentIndex]++;
             remaining = remaining - denominations[currentIndex];
          } else if (currentMin == minWithoutCurrentCoin){       
            currentIndex++;
          } else {
            throw new AssertionError("current min inconsistent wwith child min for: " + key +  " " + + currentMin + " " + keyForWithCoin + " " + minWithCurrentCoin + " " + keyForWithoutCoin + " " +  minWithoutCurrentCoin );
          }
            key = remaining + "-" + String.valueOf(currentIndex);
            keyForWithCoin = (remaining-denominations[currentIndex]) + "-" + currentIndex;
            keyForWithoutCoin = remaining + "-" + (currentIndex+1);
         

        }

        for (int i = 0; i < denominations.length; i++){
          System.out.println(denominations[i] + ": " + minCoins[i]);
        }        
   }


   public static int findMinCoinsChange3(int amount, int[] denominations){
   		int result = minCoins(amount, denominations);
   		if (result == Integer.MAX_VALUE){
   			return -1;
   		}
   		return result;
   }

  private static int minCoins(int amount, int[] denominations){

   		if (amount == 0) return 0;

   		int min = Integer.MAX_VALUE;

   		for (int i = 0; i < denominations.length; i++){
   			int previousAmount = amount - denominations[i];
   			if (previousAmount >=0){
   				int previousMin = minCoins(previousAmount, denominations);
   				if (previousMin + 1 < min){
   					min = previousMin + 1;
   				}
   			}	
   		}
   		return min;

   }

     public static int findMinCoinsChange(int amount, int[] denominations){

     	int[] results = new int[amount+1];
   		minCoinsIterative3(amount, denominations, results);
   			System.out.println(Arrays.toString(results));
   		if (results[amount] == Integer.MAX_VALUE){
   			return -1;
   		}

   		return results[amount];
   }


   //Backward DP
   private static void minCoinsIterative(int amount, int[] denominations, int[] results){

   		results[0] = 0;
   		for (int i = 1; i <= amount; i++){
   			results[i] = Integer.MAX_VALUE;
   		}

   		for (int p = 1; p <= amount; p++){
   			int tempMin = Integer.MAX_VALUE;
   			for (int j = 0; j < denominations.length; j++){
   				int previousAmount = p - denominations[j];
   				if(previousAmount >= 0 && results[previousAmount] != Integer.MAX_VALUE){
   					int candidateMin = results[previousAmount] + 1;
   					if (candidateMin < tempMin){
   						tempMin = candidateMin;
					}
   				}
   			}

   			results[p] = tempMin;

   		}	
   }

   //Forward DP.
   
   private static void minCoinsIterative2(int amount, int[] denominations, int[] results){

   		results[0] = 0;
   		for (int i = 1; i <= amount; i++){
   			results[i] = Integer.MAX_VALUE;
   		}

   		//Loop Invariant: At the beginning iteration of p, result[k] contains the minCoins required to make k for k <= p, amd for k > p, it contains an approximation of minCoins required to make k with the restriction that min cannot be less than min for j + 1 for all j < p. 

   		for (int p = 0; p <= amount; p++){
   			for (int j = 0; j < denominations.length; j++){
   				int nextAmount = p + denominations[j];
   				if(nextAmount <= amount && results[p] != Integer.MAX_VALUE){
   					if (results[nextAmount] > results[p] + 1){
   						results[nextAmount] = results[p] + 1;
					}
   				}
   			}
   		}	
   }

 
    private static void minCoinsIterative3(int amount, int[] denominations, int[] results){

   		results[0] = 0;
   		for (int i = 1; i <= amount; i++){
   			results[i] = Integer.MAX_VALUE;
   		}

   		  // Loop invariant: After reach iteration, results[p] contains the minCoins needed to make p using only coins 0...j. 
   		for (int j = 0; j < denominations.length; j++){
   			for (int p = 0; p <= amount; p++){
   				int nextAmount = p + denominations[j];
   				if(nextAmount <= amount && results[p] != Integer.MAX_VALUE){
   					if (results[nextAmount] > results[p] + 1){
   						results[nextAmount] = results[p] + 1;
					}
				}
   			}
		}
   	}
   			
   








   public static void main(String[] args){

        int amount = 3 ;
        int[] denominations = new int[]{1,5,10, 25};
    	System.out.println(findMinCoinsChange(amount, denominations));

        // amount = 6249;
        // denominations = new int[]{186, 419, 83, 408};
        // System.out.println(findMinCoinsChange(amount, denominations));


      
   }





}