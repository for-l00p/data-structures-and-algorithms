
package puzzles;

public interface RandomObject {
    
    int giveInt(int min, int max);

    public class MyRandom implements RandomObject {    
    	@Override
        public int giveInt(int min, int max){
            int random = new java.util.Random().nextInt(max - min);
            random = min + random;
            return random;
        }
   	}
 }
    