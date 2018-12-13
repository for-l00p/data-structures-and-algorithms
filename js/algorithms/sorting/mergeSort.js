
// Mergesort where every mergeSort creates two new arrays, and merge creates 1 new sorted array.  
function merge(A, B){
	var i = 0;
	var j = 0;
	var finalArray = [];
	total++
	while(i < A.length && j < B.length){
		//console.log("Comparing: " + A[i], B[j])
		if (A[i] <= B[j]){
			//console.log("Putting " + A[i] + " from array A at index " + (i+j));
			finalArray[i+j] = A[i];
			i++
		} else if (B[j] < A[i]){
			//console.log("Putting " + B[j] + " from array B at index " + (i+j))
			finalArray[i+j] = B[j];
			j++
			
		}

	}

	if (i == A.length){
		while (j < B.length ){
			//console.log("A is done. Putting " + B[j] + " from array B at index " + (i+j))
			finalArray[i+j] = B[j];
			j++
			
		}
	}
	if (j == B.length){
		while (i < A.length ){
			//console.log("B is done. Putting " + A[i] + " from array A at index " + (i+j))
			finalArray[i+j] = A[i];
			i++
			
		}	

		}

	//console.log("finalArray: " + finalArray);
	return finalArray;
}

function mergeSort(A){
	//console.log("Array given: " + A  + " with length " + A.length);
	if (A.length == 1){
		//console.log("Reached singleton element: " + A)
		return A
	} 
	j = Math.floor(A.length/2);
	//console.log("j is: " + j)

	var array1 = A.slice(0,j);
	total++
	var array2 = A.slice(j);
	total++
	//console.log("Merging: " + array1 + "and " +  array2)
	return merge(mergeSort(array1), mergeSort(array2));

}

 A = [2,5,8,4,1, 43,42,3,1,5,2,66,3,43,24,5,3,546];
 mergeSort(A);

//merge([4],[1]);

