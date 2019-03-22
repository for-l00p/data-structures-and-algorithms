

// Constructor for an iterator (an object which can track where we are on the iteration, start and end, has a next() method.)
function countdownIterator(count){
	this.next = function(){
		count--;
		return { value: count, done: count === 0}
	};
}

let tenIterator = new countdownIterator(10); // Returns iterator object.

console.log(tenIterator.next());
console.log(tenIterator.next());
console.log(tenIterator.next()); 


let countdownIterable = {
	[Symbol.iterator]: function(){return Object.assign({}, new countdownIterator(10))}
}



console.log([...countdownIterable]);



let x = ['a','b','v',1,4];

let y = x.entries();


for (let element of y){
	console.log(element);
}

