/**
 * 

 
 Tips: 
 Use long where Integer Overflow could be a problem.   
 Sort the denominations in decreasing order of value

Reference:
https://leetcode.com/problems/coin-change/solution/
 */

package puzzles.dp;

import java.util.*;

public class CoinCount {

    int[] coinCombination;
    int minCoins;

  // Partion: all solutions where atleast coin 1 is present f(amount - coin1, denominations), all solutions for coin 1 is definitely not present.  f(amount, denominations[2....m])
   public static int makeChangeRecursive1  (int amount, int[] denominations) {

      int m = denominations.length;
      return makeChangeRecursive1(amount, denominations, 0);
   }

   private static int makeChangeRecursive1 (int amount, int[] denominations, int useCoinsStartingFrom){

        //String space = String.format("%%" + useCoinsStartingFrom + "s" );
        
       // System.out.println(amount + "," + useCoinsStartingFrom);
        if (useCoinsStartingFrom >= denominations.length) return 0;
        if (amount < 0) return 0;
        if (amount == 0) return 1;
        // amount > 0, 0 <= useCoinsStartingFrom < n;
        
          int result = makeChangeRecursive1(amount - denominations[useCoinsStartingFrom], denominations, useCoinsStartingFrom) + makeChangeRecursive1(amount, denominations, useCoinsStartingFrom + 1);
           //System.out.println("Result: " + result);
           return result;
   }

  
  // Partion: all solutions where atleast coin 1 is present f(amount - coin1, denominations), all solutions for coin 1 is definitely not present and atleast 1 coin 2 is present, all solutions....  f(amount- coin1, denominations[1....m]) + f(amount- coin2, denominations[2....m]) + ....

   //  This just expands the right side of the previous recursive tree and calls some of the functions directly. https://stackoverflow.com/questions/38427665/recursive-algorithm-time-complexity-coin-change#_=_
    public static int makeChangeRecursive2  (int amount, int[] denominations) {

      int m = denominations.length;
      return makeChangeRecursive2(amount, denominations, 0);
   }


   private static int makeChangeRecursive2 (int amount, int[] denominations, int useCoinsStartingFrom){

        int total = 0;
        if (useCoinsStartingFrom >= denominations.length) return 0;
        if (amount < 0) return 0;
        if (amount == 0) return 1;
        // amount > 0, 0 <= useCoinsStartingFrom < n;
        for (int i = useCoinsStartingFrom; i < denominations.length; i++){
          total = total + makeChangeRecursive2(amount - denominations[i], denominations, i);
        }
        return total;
        
   }

     // Partion: all solutions where atleast coin k coin1 is present f(amount - coin1, denominations), k = 0,1,2....n/k. This just expands the left side of the previous recursive tree and calls some of the functions directly. https://stackoverflow.com/questions/38427665/recursive-algorithm-time-complexity-coin-change#_=_
    public static int makeChangeRecursive3  (int amount, int[] denominations) {

      int m = denominations.length;
      return makeChangeRecursive3(amount, denominations, 0);
   }


   private static int makeChangeRecursive3 (int amount, int[] denominations, int useCoinsStartingFrom){

        int totalNumWays = 0;
     
        //if (amount < 0) return 0; not needed. the code ensures we never go there
        if (amount == 0) return 1;
        if (useCoinsStartingFrom >= denominations.length) return 0;
        // amount > 0, 0 <= useCoinsStartingFrom < n;

       
        int pickedCoin = denominations[useCoinsStartingFrom];
      
        int c = amount/pickedCoin;

        for (int i = 0; i <= c; i++){
          int k = makeChangeRecursive3(amount - (i*pickedCoin), denominations, useCoinsStartingFrom + 1);
            totalNumWays = totalNumWays + k;
        }
     
        
        /**
         
        Alternatve:
        int amountWithCoin = 0;
        while(amountWithCoin <= amount){
          int remaining = amount - amountWithCoin;
          totalNumWays = totalNumWays + makeChangeRecursive3(remaining, denominations, useCoinsStartingFrom + 1);
           amountWithCoin = amountWithCoin + pickedCoin;
        }
         */
        return totalNumWays;
        
      
        
   }
    // memoization with a table representing the subproblems 
    public static int makeChangeMemoizedRecursive1 (int amount, int[] denominations) {

      int m = denominations.length;
      int[][] cache = new int[amount + 1][m+1]; 
      for (int i = 0; i <= amount; i++){
        for(int j = 0; j <= m; j++){
          cache[i][j] = -1;
        }
      }   
      fillCacheWithNumWays(amount, denominations, 0, cache);
      return cache[amount][0];
   }

   private static int fillCacheWithNumWays(int amount, int[] denominations, int useCoinsStartingFrom, int[][] cache){

        //Let cache[i][j] store the number of ways of forming i using coins in denominations[j....m-1]. Here cache[0][j] = 1 for all j (since there is 1 ways of forming 0 using the coins). cache[i][m] = 0.

        if (amount < 0) return 0;

        if (cache[amount][useCoinsStartingFrom] != -1){
          return cache[amount][useCoinsStartingFrom];
        }

        if (useCoinsStartingFrom == denominations.length) {
           cache[amount][useCoinsStartingFrom] = 0;
           return cache[amount][useCoinsStartingFrom];
        }
     
        if (amount == 0 && useCoinsStartingFrom < denominations.length) {
          cache[amount][useCoinsStartingFrom] = 1;
        }

       
        // amount > 0, 0 <= useCoinsStartingFrom < n;

        if (cache[amount][useCoinsStartingFrom] == -1){
            cache[amount][useCoinsStartingFrom] = fillCacheWithNumWays(amount - denominations[useCoinsStartingFrom], denominations, useCoinsStartingFrom, cache) + fillCacheWithNumWays(amount, denominations, useCoinsStartingFrom + 1, cache);
            ;
        } 

        return cache[amount][useCoinsStartingFrom];
      
   }

   // memoization with a Map representing the subproblems 
    public static int makeChangeMemoizedRecursive2 (int amount, int[] denominations) {


      int m = denominations.length;
      Map <String, Integer> cache = new HashMap<>(); 

      return fillCacheWithNumWays2(amount, denominations, 0, cache);
   }

   private static int fillCacheWithNumWays2(int amount, int[] denominations, int useCoinsStartingFrom, Map<String, Integer> cache){

        //Let cache[i][j] store the number of ways of forming i using coins in denominations[j....m-1]. Here cache[0][j] = 1 for all j (since there is 1 ways of forming 0 using the coins). cache[i][m] = 0.
       

        if (amount < 0) return 0;
        if (useCoinsStartingFrom == denominations.length) {
           return 0;
        }
     
        if (amount == 0 && useCoinsStartingFrom < denominations.length) {
            return 1;
        }

        String key = amount + "-" + useCoinsStartingFrom;
        if (cache.containsKey(key)){
          return cache.get(key);
        }
       int result;
        // amount > 0, 0 <= useCoinsStartingFrom < n;

        int resulWhenCoinIsUsed =  fillCacheWithNumWays2(amount - denominations[useCoinsStartingFrom], denominations, useCoinsStartingFrom, cache);
        int resultWhenCoinIsNotUsed = fillCacheWithNumWays2(amount, denominations, useCoinsStartingFrom + 1, cache);  

        result = resulWhenCoinIsUsed + resultWhenCoinIsNotUsed;

        cache.put(key, result); 

        return result;
      
   }


     // Here  j = useCoinsStartingFrom. Same as given here: https://www.algorithmist.com/index.php/Coin_Change
   public static int makeChangeIterative (int amount, int[] denominations){

        int m = denominations.length;
        int[][] cache = new int[amount+1][m+1];

        for (int j=0; j <= m-1; j++){
            cache[0][j] = 1;
        }

        for (int i = 0; i <= amount; i++){
          cache[i][m] = 0;
        }

        for (int i = 1; i <= amount; i++){
          for (int j = m-1; j>= 0; j--){
            if (i - denominations[j] >= 0){
              cache[i][j] = cache[i-denominations[j]][j] + cache[i][j+1];
            } else {
              cache[i][j] = cache[i][j+1];
            }
          }
        }

        return cache[amount][0];

   }




   // Here  j = useCoinsStartingBefore. Space complexty: O(Nm).
      public static int makeChangeIterative2 (int amount, int[] denominations){

        int m = denominations.length;
        int[][] cache = new int[amount+1][m];

    
     
        for (int i = 0; i <= amount; i++){
          if (i% denominations[0] ==0){
            cache[i][0] = 1;
          } else {
            cache[i][0] = 0;
          }
        }


        for (int j = 1; j < m; j++){
          for (int i = 0; i <= amount; i++){
              int remaining = i;
              cache[i][j] = 0;         
              while(remaining >= 0){
                cache[i][j] = cache[i][j] + cache[remaining][j-1];
                remaining = remaining - denominations[j];
              }
            }
          }

        return cache[amount][m-1];
      }


       // Here we only store variables needed to calculate next stage of subproblems and discard the rest. Space complexty O(N), where N = amount.
      public static int makeChangeIterative3(int amount, int[] denominations){

        int m = denominations.length;
        int[] combinations = new int[amount+1]; 

        for (int i = 0; i <= amount; i++){
          if (i% denominations[0] ==0){
            combinations[i] = 1;
          } else {
            combinations[i] = 0;
          }
        }

        // for each j, 
        for (int j = 1; j < m; j++){
          for (int i = amount; i >= 0; i--){
              int remaining = i - denominations[j];
              while(remaining >= 0){
                combinations[i] = combinations[i] + combinations[remaining];
                remaining = remaining - denominations[j];
              }
            }
          }
     
        return combinations[amount];
      }

      // Partion Used: all solutions where atleast coin 1 is present f(amount - coin1, denominations), all solutions for coin 1 is definitely not present.  f(amount, denominations[2....m]) 
    public static int makeChangeIterative4(int amount, int[] denominations){

        // n,j = n, j-1, n-coin[j], j  
        int m = denominations.length;
        int[] combinations = new int[amount+1]; 

        for (int money = 0; money <= amount; money++){
          if (money % denominations[0] ==0){
            combinations[money] = 1;
          } else {
            combinations[money] = 0;
          }
        }

        // for each useCoinsUpto, we cha
        for (int useCoinsUpto = 1; useCoinsUpto < m; useCoinsUpto++){
          int latestCoinAmount = denominations[useCoinsUpto];
          for (int money = latestCoinAmount; money <= amount; money++){    
                combinations[money] = combinations[money] + combinations[money-latestCoinAmount];   
          }
          }    
        return combinations[amount];
    }


    public static int findMinCoinsChange(int amount, int[] denominations) {


      int m = denominations.length;
      if (amount == 0) return 0;
      Map <String, Integer> cache = new HashMap<>(); 
      Map <String, Integer> coinCombination = new HashMap<>(); 

      int result = findMinCoinsChange(amount, denominations, 0, cache, coinCombination); 
      //System.out.println("Result: " + result);
      if (result != Integer.MAX_VALUE){
         System.out.println("Optimal solution reconstructed with decision map:");
        printCoinCombination1(amount, denominations, coinCombination);
        System.out.println("Optimal solution reconstructed from cache");
        printCoinCombination2(amount, denominations, cache);
      }  else {
        System.out.println("No coinCombination possible");
      }
      
      return result;
   }




   private static int findMinCoinsChange(int amount, int[] denominations, int useCoinsStartingFrom, Map<String, Integer> cache, Map<String, Integer> coinCombination){

        //Let cache[i][j] store the number of ways of forming i using coins in denominations[j....m-1]. Here cache[0][j] = 1 for all j (since there is 1 ways of forming 0 using the coins). cache[i][m] = 0.
       

        
  
        String key = amount + "-" + useCoinsStartingFrom;
        int result = Integer.MAX_VALUE;

        if (cache.containsKey(key)){
          result =  cache.get(key);
          return result;
        }

         if (useCoinsStartingFrom >= denominations.length || amount < 0) {       
           cache.put(key, result); 
           return result;
        }

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




   


   

   public static void main(String[] args){

        long startTime, endTime, totalTime;
        totalTime = 0;
        int amount = 100 ;
        int[] denominations = new int[]{1,5,10, 25};
    
        // startTime = System.nanoTime();
        // System.out.println(makeChangeRecursive1(amount, denominations));
        // endTime = System.nanoTime();
        // totalTime = (totalTime + endTime - startTime);
        // System.out.println("Total time:"  + totalTime + ". Partion: Solution with atleast 1 first coin, or none ");
        // totalTime = 0;
        // startTime = System.nanoTime();
        // System.out.println(makeChangeRecursive2(amount, denominations));
        // endTime = System.nanoTime();
        // totalTime = (totalTime + endTime - startTime);
        // System.out.println("Total time:"  + totalTime + ". Partition: Solution with atleast 1 first coin, solution with 0 first and atleast 1 second coin, ...   ");
        // totalTime = 0;
        // startTime = System.nanoTime();
        // System.out.println(makeChangeRecursive3(amount, denominations));
        // endTime = System.nanoTime();
        // totalTime = (totalTime + endTime - startTime);
        // System.out.println("Total time:"  + totalTime + ". Partion: Solution with k first Coins, k from 0 to c");

        // amount = 6249;
        // denominations = new int[]{186, 419, 83, 408};
        // System.out.println(findMinCoinsChange(amount, denominations));
        amount = 11;
        denominations = new int[]{9, 6, 5, 1};
        System.out.println(makeChangeIterative(amount, denominations));

      
   }



}