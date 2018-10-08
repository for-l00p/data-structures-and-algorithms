

const Stack = require('./StackLinkedlist.js')

function factorial(n){

	if (n == 0) {
		return 1
	} else {
		return n*factorial(n-1)
	}
	
}


function factorialStack(n){
	let stack = new Stack();
	let total = 1

	while(n != 0){
		stack.push(n)
		n--
	}

	while(!stack.isEmpty()){
		x = stack.pop()
		total = total*x
	}

	return total

}


console.log(factorialStack(4))






