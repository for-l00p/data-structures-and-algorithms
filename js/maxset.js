// //ind out the maximum sub-array of non negative numbers from an array.
// The sub-array should be continuous. That is, a sub-array created by choosing the second and fourth element and skipping the third element is invalid.

// Maximum sub-array is defined in terms of the sum of the elements in the sub-array. Sub-array A is greater than sub-array B if sum(A) > sum(B).

// Example:

// A : [1, 2, 5, -7, 2, 3]
// The two sub-arrays are [1, 2, 5] [2, 3].
// The answer is [1, 2, 5] as its sum is larger than [2, 3]
// NOTE: If there is a tie, then compare with segment's length and return segment which has maximum length
// NOTE 2: If there is still a tie, then return the segment with minimum starting index



module.exports = { 
 //param A : array of integers
 //return a array of integers
	maxset : function(A){
        var solutionArray = [];
        var globalSum = 0;
        var i = 0;
        
        while(i < A.length) {
            //console.log("i is now: "+ i );
// To find: j, the index of the next element after the maxset starting at i;
            if (A[i] >=0){
                var j = i;
                var temp = 0;
                while(A[j]>=0){
                    temp = A[j] + temp;
                    j++; 
                }
                //console.log("The maxset for " + i + " ends at " + (j-1));
                if (temp > globalSum){
                    globalSum = temp;
                    solutionArray = A.slice(i,j);
                } else if (temp === globalSum){
                        array = (solutionArray.length >= (j-i)) ? solutionArray: A.slice(i,j);
                        globalSum = (solutionArray.length >= (j-i)) ? globalSum: temp;
                        }
            i = j;
            //console.log("Global sum is now: " + globalSum + ". Next i: " + i);
            } else {
                 //console.log("Found negative at index: " + i);
                i++;
               
            }
        }
        return solutionArray;
     
    }

}