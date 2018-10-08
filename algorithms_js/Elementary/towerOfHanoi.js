
/* Tower of Hanoi. We have a stack of n disks on a rod. We have to move it to another rod, keeping the following rules in mind:

- We can only move one disk at a time. 
- We can only move the uppermost disk from one stack to another stack at any time, which is placed on top of the other stack. 
- At no point could a larger disk be placed on top of a smaller disk.

Input: Three Stacks. Two are empty.

*/

const Stack = require('./StackLinkedlist.js')

function towerOfHanoi(stack){
	let firstPeg = stack;
	let secondPeg = new Stack();
	let thirdPeg = new Stack();
	// let moves  = 0
	function recurse (initial, temp, dest, n){

		if(n == 1){
			dest.push(src.pop())
		} else {
				recurse(src, dest, aux, n-1)
				dest.push(src.pop())
				recurse(aux, src, dest, n-1)	
		}
	}

	recurse(firstPeg,secondPeg,thirdPeg, firstPeg.length())
	return thirdPeg
}





function iterativeHanoi(source, auxiliary, destination){

/*   For the optimal series of moves:
	 Move count is 2^n - 1 (Prove it)
	 Insight/Pattern: The smallest disk moves every odd move and it moves in the same direction always(Prove it)
	 Insight/Pattern: Every even move is made by a non-small move, and only  one such legal move is possible. (Prove it)

	 Proof:
	 
	 
	 To move the pile of n disks from tower 1 to tower 3 (which we shall call the "counterclockwise" direction), you must move the top n-1 disks from 1 to 2 (which we shall call "clockwise"), then move the bottom disk from 1 to 3 (counterclockwise), then the top pile from 2 to 3 (clockwise). Therefore, in moving a pile of n disks counterclockwise, the bottom disk will move counterclockwise and the remaining n-1 disk pile will move clockwise.Therefore, the bottom disk of that remaining n-1 disk pile (which is the second from the bottom in the original pile) will move clockwise, and the remaining n-2 disk pile will move counterclockwise. Therefore, the bottom disk of that remaining n-2 disk pile (the third from the bottom in the original pile) will move counterclockwise, and the remaining n-3 disk pile will move clockwise.
	 The optimal set of moves involve 2^n - 1 steps. 
	 In the optimal set of moves, no disk moves two consecutive steps. (Otherwise one of the step is redudant)
	 Every odd step is moved by the smallest disk (No other legal move after a non-small disk has moved). There is only one move where the smallest disk does not retrace its step. 
	 There is only one legal move that does not involve the smallest disk after every time the smallest disk moves. 

		


	Algorithm:
	Move a disk 2^n - 1 times: 
  	  if i is odd:
  	  	move the smallest disk 
  	  		Note: If n is even, the smallest peg moves forward (auxiliary first). if n is odd the smallest peg moves backwards (destination first). [Alternatively, the smallest peg always moves foward (destination first) - just if n is even, we swap the auxiliary and destination pegs at the beginning.]
  	  if i is even:
  	  		find the largest disk (or non-small disk)
  	  		find the only legal move.
*/



// Returns the peg which has the smallest disk. 
	
	function _canMove(src, dest){
		if (src.isEmpty()){
			return false
		}
		if (dest.isEmpty() || dest.peek() > src.peek()){
			return true
		}
		return false

	}

 	function _moveDisk(src, dest){

		if (src.isEmpty()){
			throw new Error("No disk to move")
		}
		if (dest.isEmpty() || dest.peek() > src.peek()){
			dest.push(src.pop())
		} else {
			throw new Error ("Invalid move")
		}
		
	}


	function _moveNonSmall(array, smallIndex){

		let src = array[(smallIndex + 1)% 3]
		let dest = array[(smallIndex + 2)% 3]
		if (_canMove(src,dest)){
			_moveDisk(src,dest)
		} else if(_canMove(dest, src)){
			_moveDisk(dest, src)

		} else {
			throw new Error ("No legit move. Something is wrong")
		}

	}

	function _moveSmall(array,smallIndex){
		let src = array[smallIndex]
		let dest = array[(smallIndex + 1 ) % 3]
		_moveDisk(src,dest)

	}



	var n = source.length();
	let arrayOfPegs = []

	if (n % 2 === 0) {
		arrayOfPegs = [source, auxiliary, destination];
	} else {
		arrayOfPegs= [source, destination, auxiliary];
	}

	let smallIndex = 0


	for (i = 1; i < Math.pow(2,n); i++){
		if ((i % 2)){
			_moveSmall(arrayOfPegs, smallIndex)
			smallIndex = (smallIndex + 1) % 3
		} else {
			_moveNonSmall(arrayOfPegs, smallIndex)		
		  }
	}
	
	console.log("Source:  " +  source.print())
	console.log("Auxiliary: "+ auxiliary.print())
	console.log("Destination:  " + destination.print())

}



function iterativeHanoi2(source, auxiliary, destination){
	/*

	Insight: Every three consecutive moves involves all 3 pairs of disks.


		For an even number of disks:

		make the legal move between pegs A and B (in either direction),
		make the legal move between pegs A and C (in either direction),
		the legal move between pegs B and C (in either direction),
		repeat until complete.

		For an odd number of disks:
		make the legal move between pegs A and C (in either direction),
		make the legal move between pegs A and B (in either direction),
		make the legal move between pegs B and C (in either direction),
		repeat until complete.
*/


	function _canMove(src, dest){
		if (src.isEmpty()){
			return false
		}
		if (dest.isEmpty() || dest.peek() > src.peek()){
			return true
		}
		return false
	}

 	function _moveDisk(src, dest){

		if (src.isEmpty()){
			throw new Error("No disk to move")
		}
		if (dest.isEmpty() || dest.peek() > src.peek()){
			dest.push(src.pop())
		} else {
			throw new Error ("Invalid move")
		}
		
	}


	let n = source.length();
	// If n is odd 
	if (n % 2 === 1) {
		var [left,middle,right] = [source, destination, auxiliary]
	}  else {
		var [left,middle,right] = [source, auxiliary, destination]
	}

	for (i = 1; i < Math.pow(2, n);i++){


		if (i%3 == 1) {
			if(_canMove(left, middle)){
				_moveDisk(left, middle)
			} else if (_canMove(middle, source)){
				_moveDisk (middle, source)
			}
		}

		if (i%3 == 2) {
			if(_canMove(left, right)){
				_moveDisk(left, right)
			} else if (_canMove(right, left)){
				_moveDisk (right, left)
			}
		}

		if (i%3 == 0) {
			if(_canMove(middle, right)){
				_moveDisk(middle, right)
			} else if (_canMove(right, middle)){
				_moveDisk (right, middle)
			}
		}

	}

	console.log("Source:  " +  source.print())
	console.log("Auxiliary: "+ auxiliary.print())
	console.log("Destination:  " + destination.print())

	

}
	

/*

There is a direct correspondence between Hanoi moves and bit counting.
The recursive structure of Hanoi algorithm (how the algorithm could be defined in terms of itself) is mirrored by the algorithm to construct a bit representation of a number. Solving Hanoi for n disks is isomorphic to counting up to 2^n - 1. https://www.youtube.com/watch?v=2SUvWfNJSsM
the i-th smallest disk moves every 2^i steps, while the largest disk moves only
once. (Prove. Look at recursive solution)

The successive moves studied in the previous section may
be coded using a binary representation. Let m be the move index. The natural
way is to associate a binary digit to each disk where the most significant
(leftmost) bit represents the largest disk as shown in Figure 8.

http://moais.imag.fr/membres/denis.trystram/SupportsDeCours/2017chapter2Coding.pdf
http://hanoitower.mkolar.org/algo.html
http://hanoitower.mkolar.org/moves.html
https://www.quora.com/In-computer-programming-what-are-some-tasks-for-which-recursion-must-be-used-and-that-cannot-be-accomplished-by-loops
http://tjd.phlegethon.org/blog/hanoi.c





-

*/


function bitHanoi(source, destination, auxiliary){
	let n = source.length();
	let arrayOfPegs = []
	if (n % 2 === 1) {
		 arrayOfPegs = [source, auxiliary, destination];
	} else {
		 arrayOfPegs= [source, destination, auxiliary];
	}

	for (i = 1; i < Math.pow(2,n); i++){

		let srcIndex = (i & (i-1)) % 3
		let destIndex = ((i | i-1) + 1) % 3
		//console.log("Move " + i + ". Moving disk from " + srcIndex + " to " + destIndex)
		_moveDisk(arrayOfPegs[srcIndex], arrayOfPegs[destIndex])

	}

	console.log("Source:  " +  source.print())
	console.log("Auxiliary: "+ auxiliary.print())
	console.log("Destination:  " + destination.print())

 	function _moveDisk(src, dest){

		if (src.isEmpty()){
			throw new Error("No disk to move")
		}
		if (dest.isEmpty() || dest.peek() > src.peek()){
			dest.push(src.pop())
		} else {
			throw new Error ("Invalid move")
		}
		
	}



}


let test = new Stack();
let secondPeg = new Stack();
let thirdPeg = new Stack();


//test.push(18)
//test.push(17)
// test.push(16)
// test.push(15)
// test.push(14)
// test.push(13)
// test.push(12)
// test.push(11)
// test.push(10)
// test.push(9)
// test.push(8)
// test.push(7)
// test.push(6)
test.push(5)
test.push(4)
test.push(3)
test.push(1)
test.push(0)

test.print()
// towerOfHanoi(test)

bitHanoi(test, secondPeg, thirdPeg)

