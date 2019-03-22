
package puzzles;


import java.util.Arrays;

public class Shuffle {

    public static int[] answer(int[] A, RandomObject random) {
        int n = A.length;
        int[] answer = new int[n];
        
        for (int i = 0; i < n; i ++){
           answer[i] = A[i];
        }
        
        int indexToSwap;
        for (int i = 0; i < n; i ++){
            indexToSwap = random.giveInt(i,n);
            int temp =  answer[i];
            answer[i] = answer[indexToSwap];
            answer[indexToSwap] = temp;
        }
        return answer;
    }
}
