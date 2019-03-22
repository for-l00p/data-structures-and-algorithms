// This is Lomuto's partitionaing scheme. https://cs.stackexchange.com/questions/11458/quicksort-partitioning-hoare-vs-lomuto
function partitionL(A,s,e){ 
	var j = s;
	//var p = Math.floor(s + (Math.random()*(e-s+1)));
	var p = s;
	//console.log("p is : " + p);
	var x = A[p];
	//xconsole.log("p is : " + p);
	//console.log("pivot is : " + x);
	for (i=s; i< e+1; i++){
		//console.log("i is now: " + i);
		if(A[i]< x){
			var temp = A[j];
			A[j] = A[i];
			A[i] = temp;
			if(i == p){
				p = j;
			}
			j++;
			//console.log("j is now " + j)
		} 
		else {//console.log("No Swap. Element at " + i + "'th position: " + A[i] + " is greater than or equal to " + x);
		}
	}


	// 	This is required else quickSort might get stuck in an infinite loop, where the output of partition is the same as input. If, for example, if we use rightmost element as pivot we use < to flip, then [5,4,3] would remain the same after partition (return j as 0 hence right partition gets suck), and if we use <= to flip, then [1,2,3] would return the same after partition (return j = 2 and hence left partition gets stuck). The way to get unstuck is to get the pivot out of the iteration, and for which we need to put it as the jth element else we get incorrect results (if we dont place the pivot as jth element,   we do not use the property which connects the subproblems: every element of 1 subproblem is less than the pivot, every element of the 2 subproblem is greater than the pivot. The subproblems still have the property that every element of 1 subproblem is less than every element of the 2nd subproblem, but this happens: the subproblems on the right might yield a sorted array, but they the jth element might be the biggerthan it.)

	temp = A[j-1];
	A[j-1] = A[p];
	A[p] = temp;

	
	// console.log ("Index of partition: " + j);
	// console.log("Partitioned Array: " + A)
	return j-1;
}




 function partitionH(A,s,e){
 	console.log("Initial array: " + A);
 	var i = s;
 	var j = e;
 	var x = A[e];
 	console.log("pivot is : " + x);
 	while(true){
 		while(A[i] <= x){
 			console.log("Incrementing i")
 			i++;
 		}

 		while(A[j] > x){
 			console.log("Decrementing j")
 			j--
 		}

 		if(i<j){
 			console.log("Found a pair to exchange: " + i + " and " + j)
 			temp = A[i];
 			A[i] = A[j];
 			A[j] = temp;
 		} else {console.log("Final: " + A);
 			return j;}
 		
 		 		

 	}
 }



 function quickSort(A, s, e){
 	if (s < e){
 		//console.log("Array to sort: " + A + " between indexes " + s + " and " + e);
 		j = partitionL(A,s,e);
 		//j = partitionL(A,s,e);
 		quickSort(A,s,j-1); // Leave j in the next iteration. 
 		quickSort(A,j+1,e);
 	}
 	
   

 }


A = [2,3,6,4,2,10, 11, 1, 5, 2,3,4,5,2,10,3,56,3, 1,424,1212,423,2,4,6,12,5,12,1243,5,12,53,23,52,3,3];
B = [1,2,3,4];
C = []

//partitionH(A, 0,A.length-1);
quickSort(A,0,A.length-1);
console.log("final array: " + A);
//quickSort1(B,0,B.length-1)





