
//Mergesort where merge creates a temp array to store values, but eventualy everything is copied on the original array.

function mergeSort(A,start,end){

	if (start==end){
		console.log("Reached singleton element: " + A[start])
		return;
	} 

	var midPoint = Math.floor((end+start)/2);
	mergeSort(A,start,midPoint);
	mergeSort(A,(midPoint+1),end);
	merge(A,start,(midPoint),end);
	console.log(A);
	return A
}



function merge(A,start,mid,end){ //Merges A[s....mid] and A[mid+1....e]. 
	var i = start;
	var j = mid+1;
	var finalArray = [];
	while(i <= mid  && j <= end){

		if (A[i] <= A[j]){
			finalArray.push(A[i]);
			i++
		} 

		else {
			finalArray.push(A[j]);
			j++
			
		}

	}

	while (j <= end ){
			finalArray.push(A[j]);
			j++
			
		}
	
	while (i <= mid ){
			finalArray.push(A[i]);
			i++
			
		}	

	

	for(var q = start; q <end+1;q++){
		A[q] = finalArray[q-start]
	}

	console.log("mergedArray: " + finalArray);

}



 A = [2,5,8,4,1, 43,42,3,1,5,2,66,3,43,24,5,3,546];

 mergeSort(A, 0, (A.length-1));
 //merge(A, 0, 2,2) is never called. start = 0, j = 1, end = 2 

//merge([4],[1]);

