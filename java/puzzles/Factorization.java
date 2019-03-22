package puzzles;

import java.util.List;
import java.util.ArrayList;

public class Factorization {
   
    public static int[] getFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        int k = 2;
        int num = n;
        while (k < Math.sqrt(n) || num != 1){

            if (isFactor(num, k)){
                num = num/k;
                factors.add(k);
                continue;
            } else {
                k++;
            }           
        }        
        return listToArray(factors);
    }
    
    private static int[] listToArray(List<Integer> list){
        int n = list.size();
        int[] result = new int[n];
        for (int i = 0; i < n; i++){
            result[i] = list.get(i);
        }
        return result;
             
    }
    
    private static boolean isFactor(int n, int k){
        return (n % k == 0);
    }
}
