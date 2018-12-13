
//raising a base 'number' to a non-negative integer exponent 'power'
function exponent(number, power){

	if (power == 0){
		return 1
	}

	else return number* exponent(number,power-1)

}

function iterativeExponent(number, power){

	let value = 1

	while(power>0){
		value = value*number
		power = power - 1
	}
	
	return value

}

//  the algorithm has time complexity of Θ(log e).
function fastExp( number, power){

	if (power == 0){
		return 1
	} else if (power % 2 == 1){
		return number*fastExp(number, power-1)
	} else {return Math.pow(fastExp(number, power/2), 2)}

}

console.time("Fast Exp")
console.log("Fast Exp:" + fastExp (6,80))
console.timeEnd("Fast Exp")

console.time("iterative Exp")
console.log("iterative Exp: " + iterativeExponent (6,80))
console.timeEnd("iterative Exp")

console.time("Normal")
console.log("Normal: " + exponent (6,80))
console.timeEnd("Normal")

