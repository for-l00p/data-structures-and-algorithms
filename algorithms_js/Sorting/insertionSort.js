

function insertionSort(A){
	
	for(var i = 0; i < A.length; i++){
		var valueBeingSorted = A[i];
		var candidatePosition = i;
		while(A[candidatePosition-1]> valueBeingSorted && candidatePosition !=0){
			A[candidatePosition] = A[candidatePosition-1];
			candidatePosition--;
		}
		A[candidatePosition] = valueBeingSorted;
	}
	console.log(A);
}

insertionSort([2,4,1,2,5,3,1,3,5]);