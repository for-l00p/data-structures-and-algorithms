import java.util.*;

class BowlingGame {

    public static int findTotalScore(String frames){
        List<Character> characters = new ArrayList<>();
        for (Character c: frames.toCharArray()){
            characters.add(c);
        }
        ListIterator<Character> iterator = characters.listIterator();
        
        int totalScore = 0;
        int frameNumber = 1;
        boolean isPrevX = false;
        boolean isPrevPrevX = false;
        int multiple = 1;
        Character letter;
        int currentCharValue = 0;
        
        while(iterator.hasNext()){
            letter = iterator.next();

            multiple = 1;
            System.out.println("At start Multiple:" + multiple + " Frame number:" + frameNumber + " Letter:" + letter);

            if (letter.equals('-')){
                continue;
            }
            
            if (letter.equals('|')){
                frameNumber++;
                continue;
            }
            
            if (letter.equals('X')){
                currentCharValue = 10;   
            } 
            
            if (letter.equals('/')){
                Character previousLetter = iterator.previous();
                currentCharValue = 10 - (int) previousLetter; 
                iterator.next();
            }
            
            if (Character.isDigit(letter)){                             
               currentCharValue = Character.getNumericValue(letter);     
            }
            
                                 
            if (isPrevPrevX && isPrevX){
                //make multuple 3
                if (frameNumber <= 10){
                    multiple = 3; 
                } else {
                    multiple = 2;
                }

                if (letter.equals('X') && frameNumber <= 10){
                    isPrevX = true;
                } else {
                    isPrevX = false;
                }
                
             }


             if (isPrevPrevX && !isPrevX){
                if (frameNumber <= 10){
                    multiple = 2;
                }
                 
                isPrevPrevX = false;
                 if (letter.equals('X') && frameNumber <= 10){
                    isPrevX = true;
                }
             }

             if (!isPrevPrevX && isPrevX){
                if (frameNumber <= 10){
                    multiple = 2;
                }
                isPrevPrevX = true;
                if (letter.equals('X') && frameNumber <= 10){
                    isPrevX = true;
                } else {
                    isPrevX = false;
                }
            }
             
             
            currentCharValue = multiple*currentCharValue;
             
          
             
        System.out.println("Multiple:" + multiple + " Frame number:" + frameNumber + " Letter:" + letter + " adding: " + currentCharValue  );
            totalScore = totalScore +  currentCharValue;
        } 
            
      
        
        return totalScore;
         
      }

    public static void main(String[] args){

        System.out.println(findTotalScore("X|X|X|X|X|X|X|X|X|X||XX"));
    }
    
    
    

}