
TASK: Please write for me a function in a C-like programming language (C, C++, C#, Java, Rust) to reverse a string.

Sample inputs and outputs:

“Booooooom!” => “!moooooooB”
“Hello world!” => “!dlrow olleH”
“Other way up!” => “!pu yaw rethO”



public static String reverseString(String inputString){
 
    int n = inputString.length(); 
    char[] inputArray = inputString.toCharArray();
    char[] resultArray = new char[n]; 
    for (int i = n - 1; i >= 0; i--){ 
        int j = n - 1 - i; 
        resultArray[j] = inputArray[i]; 
    }
    return String.valueOf(resultArray);

}



TASK: Please write for me a function in a C-like programming language (C, C++, C#, Java, Rust) to reverse a string.

Sample inputs and outputs:

“Booooooom!” => “!moooooooB”
“Hello world!” => “!dlrow olleH”
“Other way up!” => “!pu yaw rethO”



public static String reverseString(String inputString){
    //input = “Booooooom!”
    int n = inputString.length(); // n = 10
    char[] inputArray = inputString.toCharArray();// [B,o,o,....]
    char[] resultArray = new char[n]; // n = 10 array of size n 
    //Reverse elements of this array 
    for (int i = n - 1; i >= 0; i--){ // i = 9, 8, 7 // inputArray.lenth = 10, i = 0
        int j = n - 1 - i; // j = 0, 1, 2...j = 10-1-0 = 9
        resultArray[j] = inputArray[i]; // result[0] = input[9], result[1] = input[8];...result[9] = input[0]
    }

    return String.valueOf(resultArray);

}


