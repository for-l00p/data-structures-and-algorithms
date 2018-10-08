function fibonacci(n){
	if (n == 1 ){
		return 1
	}

	if(n == 2){
		return 1
	}
	//console.log("Running fibonacci for: " + n)
	return (fibonacci(n-1) + fibonacci (n-2))
}




function fibTailRecursive(n){
 
	let fibArray = [1,1]   // 1 step
	for (i = 2; i < n; i++){
		fibArray.push((fibArray[i-2] + fibArray[i-1])) //2 operations per step. 
	}
	return fibArray[n-1]

}


/*

 the recursive fib algorithm requires Θ(n) space as well, because it often has n subroutine calls currently pending, and the computer has to
remember the state at each level.

*/

function iterativeFib(n){

	let firstStep = 1
	let secondStep = 1
	let result = firstStep + secondStep
	let moves = 3
	for (i = 3; i< n; i++){
		firstStep = secondStep
		secondStep = result 
		result = firstStep + secondStep
		moves = moves + 3
		//console.log("Fibonacci " + (i+1) + " is " + result)
	}
	return result

}



function memoize(f){
	let storedValues = []

	function doIt(arg){
		if (!storedValues.hasOwnProperty(arg)) {	
			storedValues[arg] = f(arg)	
			return storedValues[arg]
		} else {
			return storedValues[arg]
		}


	}
	return doIt
}

fibonacci = memoize(fibonacci)


function fibRecursive(n){
	if (n == 1 ){
		return 1
	}

	if(n == 2){
		return 1
	}
	//console.log("Running fibRecursive for: " + n)
	return (fibRecursive(n-1) + fibRecursive (n-2))
}


//For later reading: https://blog.paulhankin.net/fibonacci/

console.time("Iterative Fib")
console.log(iterativeFib(200))
console.timeEnd("Iterative Fib")


console.time("Memoize Fib")
console.log(fibonacci(200))
console.timeEnd("Memoize Fib")


console.time("TailRecursive Fib")
console.log(fibTailRecursive(200))
console.timeEnd("TailRecursive Fib")




// console.time("Recursive Fib")
// console.log(fibRecursive(50))
// console.timeEnd("Recursive Fib")


// for (i = 0; i < 11; i ++){
// 	console.log(iterativeFib(i))

// }
