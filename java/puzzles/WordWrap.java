
package puzzles;

/**
 
 http://blog.code-cop.org/2011/08/word-wrap-kata-variants.html

Questions to ask:

How to treat empty space? 
E.g.  "aa_b, 2" -> "aa\nc" or "aa\n_c" (Rule 1: Do we get rid of empty space at line start?) 
"aa_b, 3" -> "aa_\nb" or "aa\nb" (Rule 2: Do we get rid of empty space at line end?)

How do we treat multiple spaces? Treat the whole as a one single space, or Treat the first occurence as word separator and the second occurance as start of a new word.

Example: "aa__, 3" 

Case: Treat the first occurence as word separator and the second occurance as start of a new word.
Apply Rule 1 and not Rule 2:
"aa_\n" 
Apply Rule 2 and not Rule 1:
"aa\n_"
Apply Rule 1 and Rule 2:
"aa\n"
Apply !Rule 1 and !Rule 2:
"aa_\n_"

Case: Treat the whole as a one single space.
"aa__, 3"  becomes "aa_, 3". Then it reduces to one of the previous cases before
Appy Rule 2:
"aa" 
Do not apply rule 2:
"aa_"

Case: Treat it as a single space for word separation, but add the new line seeing it as an indication of exceeding the width.
"aa_\n"

 
Can length of word increase column width? (This would make the code easier - otherwise whether to insert line break or not depends on length of word) If yes, are we required to break words at line end?
Iterative or Recursive?

Handy assumption 1: word.length <= column width
Approach:
Work before recursion: Find first line break
Subproblem: text remaining after first line break. 
Ques: How to find where to end the first line?  
Try to insert line break at index text[width]. Does this always work. When is this illegal?
Algorithm: Break the line at the index nearest to width (including it) corresponding to a word end. Call it j. return text(0, j) + recurse(text(j+offset, end))


In the following we assume the following:

- trailing empty spaces at the end of line are not removed
- empty spaces at the beginning of the line are removed
- if a word length is bigger than the width, even then we are using line breaks 
- multiple spaces can be broken whereever.

 */


public class WordWrap {

    public static String wrapIterative (String text, int columnWidth, StringBuilder newText) {
        String[] words = text.split(" ");
        int spaceLeft = columnWidth;
        newText = new StringBuilder();
        String word;

        for (int j = 0; j < words.length; j++){
            word = words[j];
            if (j != words.length - 1){
                word = word + " ";
            }
            if (spaceLeft == 0){
                newText.append("\n");
                spaceLeft = columnWidth;
            }
            
            if (word.length() < spaceLeft){
               newText.append(word);
               spaceLeft = columnWidth - word.length();
               continue;
            }

            if (word.length() == spaceLeft){
                newText.append(word + "\n");
                spaceLeft = columnWidth;
                continue;
            }
            
            if (word.length() > spaceLeft && word.length() > columnWidth){
                int i = 0;
                while (i < word.length()){
                    if (spaceLeft == 0){
                        newText.append("\n");
                        spaceLeft = columnWidth;
                    }
                    newText.append(word.charAt(i));
                    spaceLeft--;
                    i++;                    
                }               
            } else {
                newText.append("\n");
                spaceLeft = columnWidth;
                newText.append(word);
                spaceLeft = spaceLeft - word.length();
            }
        }
        return String.valueOf(newText);
    }




    public static String wrap (String text, int width){

        if (width <= 0) throw new AssertionError("Width is less than zero");
        // what to do and the end
        StringBuilder newText = new StringBuilder();
        wrapRecursive2(text, width, newText);
        return String.valueOf(newText);
        
    }

    private static void wrapRecursive (String text, int width, StringBuilder newText) {
        if (text.length() <= width){
            newText.append(text);
            return;
        }

        int indexOfBlank = text.lastIndexOf(" ", width);

        int split = width;
        int offset = 0;
        if (indexOfBlank > -1){
            split =  indexOfBlank;
            offset = 1;
        } else {
            split = width;
            offset = 0;
        }
        newText.append(text.substring(0, split));
        newText.append("\n");
        wrapRecursive(text.substring(split + offset), width, newText);

    }

    private static void wrapRecursive1 (String text, int width, StringBuilder newText) {
        //System.out.println("Text:" + text + " Width: " + width);
        int n = text.length();
        if (n <= width){
            newText.append(text);
        } else {
            int j = findIndexOfLineEnd(text, width);
            newText.append(text.substring(0, j));
            newText.append('\n');
            wrapRecursive1(text.substring(j), width, newText);
        }
    }

    private static int findIndexOfLineEnd(String text, int width){
        // If empty space after first width letters, then we can break. Else if there is a word, then find the word boundaries (delimtted by ' ');
        //System.out.println("findEnd " + "text:" + text);
        int j = width;
        char currentChar = text.charAt(j);
        if (currentChar != ' '){
            int start = findWordStart(text, j);
            int end = findWordEnd(text, j);
            if (end - start < width ){
                j =  start; 
            }           
        }
        return j;   
    }

    private static int findWordStart(String text, int j){
        if (text.charAt(j) == ' ') throw new AssertionError("No word to find start of");
        int indexOfBlank = text.lastIndexOf(' ',j);
        if (indexOfBlank == -1){
            return 0;
        } 
        
        return indexOfBlank + 1;
    }

    private static int findWordEnd(String text, int j){
        if (text.charAt(j) == ' ') throw new AssertionError("No word to find end of");  
        int indexOfBlank = text.indexOf(' ',j);
        if (indexOfBlank == -1){
            return text.length() -1;
        }
        return indexOfBlank - 1;
    }


/**
 * Recursion, wiith the subproblem being defined by deleting the last word. The after-recursion work is inserting the last word. 
 */

    private static int wrapRecursive2 (String text, int width, StringBuilder newText){

        if (width <= 0) throw new AssertionError("no width");

        int n = text.length();
        int spaceLeft = width;
        if (n == 0){
            return spaceLeft;
        }

        if (n <= spaceLeft){
            newText.append(text);
            spaceLeft = spaceLeft - n;
            return spaceLeft;
        }

        String lastWord;
        int j = text.lastIndexOf(' ');
        if (j != -1){
            spaceLeft = wrapRecursive2(text.substring(0, j), width, newText);
            lastWord = text.substring(j+1);
             // j points to the space corresponding to end of last word. We solve the subproblem after removing it. After that we need to add it in all but one case: when the lastWord is the starting word.
            if (spaceLeft == 0){
                newText.append('\n');
                newText.append(' ');
                spaceLeft = width -1;
            } else {
                newText.append(" ");
                spaceLeft--;
            }
        }  else {
            lastWord = text.substring(0);
        }
       


        System.out.println("Word: " + lastWord + " spaceLeft: " + spaceLeft);
        if (spaceLeft >= lastWord.length()){
            newText.append(lastWord);
            spaceLeft = spaceLeft - lastWord.length();
            return spaceLeft;
            
        }
        if (spaceLeft <= lastWord.length()){
            if (lastWord.length() <= width){
                newText.append('\n');
                spaceLeft = width; 
            }

            for (int k = 0; k < lastWord.length(); k ++){
                if(spaceLeft == 0){
                    newText.append('\n');
                    spaceLeft = width;
                }

                //spaceLeft > 0
                newText.append(lastWord.charAt(k));
                spaceLeft--;
            }
        }
        return spaceLeft;
    }

  


    

    public static void main (String[] args){
        String input = "my name is Piyush. I am a student, and I want to see this text as being in a with a maximum column width of 9";
        String output = wrap(input, 9);
        System.out.println(output);


    }
}
