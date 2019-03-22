

module.exports = BinarySearchTree


const Queue = require('./QueueLinkedList.js')
const LinkedListLibrary = require('./SingleLinkedList.js')
const Stack = require('./StackLinkedList.js')

// Position interface has an implementation getElement function. 
const Position = {
	getElement: function(){
			return this.element
	}
}
	
// Factory function which creates treeNode objects. How the object is created: first an object is created whose prototype is the Position object (so it inherits the getElement method via prototypical inheritence). Then other attributes are copied from a special object passed as a second argument. 
const treeNodeFactory = (element, parent, left, right) => Object.assign(Object.create(Position), {
	element: element,
	parent: parent || null,
	leftChild: left || null,
	rightChild: right || null})


// A Binary search tree is a binary tree T such that each internal node v stores an item (e, value) of a dictionary. Elements stored at nodes in the left subtree of v are less than or equal to e. Elements stores in the right subtree are greater than or equal to e. 
// Searching, inserting, minimum, successor predecessor takes O(h) time too. Finding minimum or maximum takes takes O(h) time, which can be O(n) in worst case.  In case of array, insertion and deletion takes O(n) time, while searching is O(logn). In case of ordered Linkedin List, searching takes O(n), insertion takes O(n), Min, Max, Successor and Predessor takes O(1). In unordered lists, searching, successer, predecessor, min, max takes O(n) and insertion takes O(1). 

//Constructor function for BinarySearchTree (can be used with new KeyWord)
function BinarySearchTree(root){
	this._root = treeNodeFactory(root);
	this.size = (this._root) ? 1:0
	console.log("New Tree. _root: " + this.root() + ". size:  " + this.size)
}

BinarySearchTree.prototype = {


	//Generic container methods 

	isEmpty: function(){
		return (this.size == 0)
	},

	
	iterator: function(traversal, order){
		positions = this.positions(traversal, order)
		elements = new LinkedListLibrary.SingleLinkedList();
		for (let treeNode of positions){
			elements.addToTail(treeNode.getElement())
		}

		return elements[Symbol.iterator]()
	},

	//Positional Container Methods

	positions: function(traversal, order){

		var positions = new LinkedListLibrary.SingleLinkedList();
		var callback = function(node){
			//console.log("Adding node " + JSON.stringify(node))
			positions.addToTail(node)
		}

		traversal.call(this, callback, order)
		return positions

	},

	swapElements: function(){

	},

	replaceElement: function(node, element){

	},

	//Query Methods

	isRoot: function(){

	},

	isInternal: function(node){

		return (!this.isLeaf(node))	
	},

	isLeaf: function(node){

		return ((node.leftChild === null) && (node.rightChild === null))

	},


	//Accessor Methods:

	parent: function(node){

		return node.parent

	},

	root: function(){
		return this._root
	},

	children: function(node){
		//console.log("value of this: " + JSON.stringify(this.))
		if(this.isLeaf(node)){
			console.log("Node is leage. Has No children")
			return
		}
		var children = [];
		if (node.leftChild) {children.push(node.leftChild)};
		if (node.rightChild) {children.push(node.rightChild)};
		return children
	},

	leftChild: function(node){
		return node.leftChild
	},

	rightChild: function(node){
		return node.rightChild
	},

	depth: function(node){
		

		return (function recurse(node, depth){
			if (this.isRoot(node)){
			return depth
			} else {
				return recurse(node.parent, depth + 1)
			   }
	    })(node,0)

	},

	findMin: function(root){
		let currentNode = root || this.root()
		while(currentNode.leftChild){
			currentNode = currentNode.leftChild
		}
		return currentNode

	},

	findMax: function(root){
		let currentNode = root || this.root()
		while(currentNode.rightChild){
			currentNode = currentNode.rightChild
		}

		return currentNode

	},

	search: function(key){
	// to find out an element with key k in a tree T

		function recurse(root,key){
			if (root == null){
				console.log("Element not found")
				return 
			}
			let currentKey = root.getElement()
			console.log("Comparing with: " + currentKey)
			if (currentKey == key){
				return root
			} else if (currentKey > key){
				let leftTree = root.leftChild
				return recurse(leftTree,key)
			} else if (currentKey  <= key){
				let rightTree = root.rightChild
				return recurse(rightTree,key)
			} 

			
		}
		return recurse(this.root(),key)


	},

	searchIterative: function(key){
		currentRoot = tree.root()
		while(currentRoot && (key != currentRoot.getElement())){
			currentKey = currentRoot.getElement()
			if (currentKey > key){
				currentRoot = currentRoot.leftChild
				continue
			}
			if (currentKey <= key){
				currentRoot = currentRoot.rightChild
				continue
			}
		}

		if (currentRoot){
				return currentRoot
			} else {
				console.log("Element not found")
				return
			}


	},

	successor: function(node){

		if(node.rightChild){
			return this.findMin(node.rightChild)
		}
		let currentParent = node.parent
		let currentNode = node
		while(currentParent != null && currentNode == currentParent.rightChild ){
			currentNode = currentParent
			currentParent = currentParent.parent
			
		}

		if(currentParent) {return currentParent} else{console.log("Successor not found")}

	},

	predecessor: function(node){

		if (node.leftChild){
			return this.findMax(node.leftChild)
		}

		let currentParent = node.parent
		let currentNode = node
		while(currentParent != null && currentNode == currentParent.leftChild ){
			currentNode = currentParent
			currentParent = currentParent.parent
			
		}

		if(currentParent) {return currentParent} else{console.log("predecessor not found")}


	},

	insert2: function(element, traversal, order){
		//console.log("Inserting element " + element + " with traversal " + traversal + " and order " + order)
		var inserted = 0
		var callback = function(node) {
			var x = node.getElement()
			if (inserted === 0){
				if(node.leftChild == null){
					node.leftChild = treeNodeFactory(element,node)
					console.log("Left child of " + x + " is absent. Inserting... " + element)
					inserted++
					this.size++
					return
				} 
				if(node.rightChild == null){
					node.rightChild = treeNodeFactory(element,node)
					console.log("Right child of " + x + " is absent. Inserting... " + element)
					inserted++
					this.size++
					return
				}
			}
		}
		traversal.call(this, callback, order)

	},

	insert: function(key){
		let currentParent = null
		let currentNode = this.root()
		while(currentNode){
			if (key < currentNode.getElement()){
				currentParent = currentNode
				currentNode = currentNode.leftChild
			} else {
				currentParent = currentNode
				currentNode = currentNode.rightChild
			}
		}

		let newNode = treeNodeFactory(key,currentParent)
		if (key < currentParent.getElement()){
			currentParent.leftChild = newNode
		} else {currentParent.rightChild = newNode}


	},

	delete: function(node){

		function oneChildFlip(nodeToDelete){
			let parent = nodeToDelete.parent
			if (nodeToDelete.leftChild == null){
				nodeToMove = nodeToDelete.rightChild
			} else {
				nodeToMove = nodeToDelete.leftChild
			}

			if (nodeToMove){
			// nodeToDelete is not a leaf
				nodeToMove.parent = nodeToDelete.parent
			} 
			
			if (parent.leftChild == nodeToDelete){
				parent.leftChild = nodeToMove
			} else {
				parent.rightChild = nodeToMove
				console.log(parent)
			}
		}

		if (node.leftChild == null || node.rightChild == null){
			oneChildFlip(node)
		} else {
			// when node has two children
 
			let nodeToMove = this.successor(node)
			oneChildFlip(nodeToMove)

			//Copy the successor's content in the original node. 	

			if(node.parent){
				if(node == node.parent.leftChild){
					childType = "leftChild"
				} else {
					childType = "rightChild"
				}

				node.parent[childType] = nodeToMove;  
			}

			nodeToMove.parent = node.parent
			nodeToMove.leftChild = node.leftChild
			nodeToMove.rightChild = node.rightChild
			node.leftChild.parent = nodeToMove
			if(node.rightChild){
				node.rightChild.parent = nodeToMove
			}
			console.log(nodeToMove)
			

		}

	},

	height: function(){

		var recurse = function(node){
			if(node === null){
				return 0
			};
			if (this.isLeaf(node)){
				return 1 
			} else {
				let heightLeft = recurse(node.leftChild);
				let heightRight = recurse(node.rightChild);
				return  (heightLeft < heightRight) ? (1 + heightRight): (1 + heightLeft)	
					
			}
		}.bind(this)

		return recurse(this.root())
	},


	height2: function(){
		 var queue = new Queue();
		 queue.enqueue(this.root())
		 var height = 0;
		 while(!queue.isEmpty()){ // Everytime this loop is run, nodesInCurrentLevel = 0
		 	height++
		 	var nodesInCurrentLevel = queue.length() 
		 	while(nodesInCurrentLevel > 0){
		 		var currentNode = queue.dequeue()
		 		if (this.isInternal(currentNode)){
		 			for (let node of this.children(currentNode)){
		 				queue.enqueue(node)
		 			}
		 		}
		 		nodesInCurrentLevel-- 
			}
			// While loop exited when nodesInCurrentLevel = 0
		}
		return height

	},

	traverseBF: function(callback){

		var queue = new Queue();
		queue.enqueue(this.root())
		while(!queue.isEmpty()){
			var currentNode = queue.dequeue();
			if(currentNode.leftChild){
				queue.enqueue(currentNode.leftChild)
			}
			if(currentNode.rightChild){
				queue.enqueue(currentNode.rightChild)
			}
			callback(currentNode)
		}
	},
		
	traverseDF: function(callback, order){
	
		(function recurse(node){
			
			if (node == null){
				return 
			}

			if (order === 'preOrder'){
				callback(node)
				recurse(node.leftChild)
				recurse(node.rightChild)
			}
			

			if (order === 'inOrder'){
				recurse(node.leftChild)
				callback(node)
				recurse(node.rightChild)
			}

			if (order === 'postOrder'){
				recurse(node.leftChild)
				recurse(node.rightChild)
				callback(node)
			}
		
		})(this.root())
	
	},

	traverseDF2: function(callback, order){

		var nodeStack = new Stack();
		nodeStack.push(this.root())

		if (order === 'preOrder'){
			while(!nodeStack.isEmpty()){
				var currentNode = nodeStack.pop();
				callback(currentNode);
				if (currentNode.rightChild){
					nodeStack.push(currentNode.rightChild)
				}

				if (currentNode.leftChild){
					nodeStack.push(currentNode.leftChild)
				}

			}
		}


		if (order === 'inOrder'){

			var leftChildVisited = false
			while(!nodeStack.isEmpty()){
				var checkNode = nodeStack.peek();

				if (leftChildVisited === false) {
					if 	(checkNode.leftChild){
						nodeStack.push(checkNode.leftChild)
					} else {
						leftChildVisited = true
					  }
				}

				if (leftChildVisited === true) {
					currentNode = nodeStack.pop();
					callback(currentNode)
					if (currentNode.rightChild){
						nodeStack.push(currentNode.rightChild)
						leftChildVisited = false
					} 
				}
			}
		}
		//Check this: does this ever terminate? is currentNode null at the end?

		if (order === 'inOrder2'){
			// Here we start with a stack empty (as opposed to the stack containing the root). The currentNode emulates the input to a recursive call, and each loop emulates a recursive call, starting with the first call. This is a neater code.  
			var currentNode = this.root()
			nodeStack.pop()
			while(!nodeStack.isEmpty() || currentNode){

				// If there is a node on which the recursive call is made, we have a subtree to explore. If this is null, we have to backtrack and do a callback. 
				if (currentNode){
					//console.log("Pushing " + currentNode.getElement())
					nodeStack.push(currentNode)
					currentNode = currentNode.leftChild
				} else {
					currentNode = nodeStack.pop()
					callback(currentNode)
					currentNode = currentNode.rightChild		
				}
			}
		}




		if (order === 'postOrder'){
			// Using one stack and two extra variables (one to track previous variable)
			var readyToVisit = false
			var previousNode;
			while(!nodeStack.isEmpty()){
				var currentNode = nodeStack.pop();

				if (!(currentNode.leftChild || currentNode.rightChild)){
					readyToVisit = true
				}

				if (currentNode.rightChild === previousNode){
					readyToVisit = true
				}

				if (currentNode.leftChild === previousNode && currentNode.rightChild === null){
					readyToVisit = true
				}

				if (!readyToVisit) {
					nodeStack.push(currentNode)
					if (currentNode.rightChild){
						nodeStack.push(currentNode.rightChild)
					} 
					if 	(currentNode.leftChild){
						nodeStack.push(currentNode.leftChild)
					} 
				}

				if (readyToVisit){
					callback(currentNode)
					readyToVisit = false
					previousNode = currentNode
					
				}
			}
		}

		if (order == 'postOrder2'){
			// Using one stack and one extra boolean variable (no tracking of previous node visited needed). The purpose is to determine if the traversal is descending/ascending the tree.
			var leftChildVisited = false;
			// If false, we are descending and supposed to go down left.
			// if true, then we check whether rightChild is visited. If the next node in the stack is the right child, then it is unvisited. If it is not, then either it is absent or already visited.
			// rightChild is unvisited: We are ascending from left and supposed visit rightChild.
			// rightChild is already visited or absent: We visit the node itself. 
			while(!nodeStack.isEmpty()){
				currentNode = nodeStack.pop()
			
				if (!leftChildVisited){
					
					if (currentNode.leftChild && currentNode.rightChild){
						nodeStack.push(currentNode.rightChild)
						nodeStack.push(currentNode)
						nodeStack.push(currentNode.leftChild)
					} 


					if (currentNode.leftChild && currentNode.rightChild == null){
						nodeStack.push(currentNode)
						nodeStack.push(currentNode.leftChild)
					}

					if (currentNode.rightChild && currentNode.leftChild == null) {
						nodeStack.push(currentNode.rightChild)
						nodeStack.push(currentNode)
						leftChildVisited = true
						continue
					}

					if (!(currentNode.rightChild || currentNode.leftChild))  {
					  	callback(currentNode)
					  	leftChildVisited = true
					  	continue
					}

				}
				
				if (leftChildVisited){
					if(!nodeStack.isEmpty()){
						if (currentNode.rightChild === nodeStack.peek()){
							temp = nodeStack.pop()
							nodeStack.push(currentNode)
							nodeStack.push(temp)
							leftChildVisited = false
						} else {
						        callback(currentNode)
					      }
				    } else callback(currentNode)
				}
			}
			
		}

		if (order == 'postOrder3'){
			// Using two stacks
			var reverseStack = new Stack();

			while(!nodeStack.isEmpty()){
				var currentNode = nodeStack.pop()
				console.log("Pushing " + currentNode.getElement());
				reverseStack.push(currentNode)
				if (currentNode.leftChild){
					//console.log("Pushing " + currentNode.leftChild.getElement());
					nodeStack.push(currentNode.leftChild)

				}
				if (currentNode.rightChild){
					//console.log("Pushing " + currentNode.rightChild.getElement());
					nodeStack.push(currentNode.rightChild)
				}
			}

			while(!reverseStack.isEmpty()){
				console.log(reverseStack.pop().getElement())
			}
			
		}

		if (order == 'postOrder4'){
			var currentNode = this.root()
			var previousNode = null
			nodeStack.pop()
			while(!nodeStack.isEmpty() || currentNode){

				// If there is a node on which the recursive call is made, we have a left subtree to explore. Non-null currentNode corresponds to an unvisited left subtree.
				if (currentNode){
					//console.log("Pushing " + currentNode.getElement())
					nodeStack.push(currentNode)
					previousNode = currentNode
					currentNode = currentNode.leftChild
				 //If current node == null, we have to investigate the stack to see the next node an unvisited right subtree exists. If not, we visit and set the stage for the next investigation of the stack by setting currentNode == null. Can the next node in the stack have an unvisited left subtree? 
				} else {
					currentNode = nodeStack.peek()
					if (currentNode.rightChild && previousNode != currentNode.rightChild){
						currentNode = currentNode.rightChild	
					} else {
						callback(currentNode)
						currentNode = null
						previousNode = nodeStack.pop()
					}
				 } 




			}
		}

		


		

		
	}

}


var tree = new BinarySearchTree(10);
tree.insert2(5, tree.traverseBF)
tree.insert2(15, tree.traverseBF)
tree.insert2(2, tree.traverseBF)
tree.insert2(8, tree.traverseBF)
tree.insert2(1, tree.traverseDF, 'preOrder')
tree.insert2(12, tree.traverseBF)
 
console.log("Printing inOrder now")
 tree.traverseDF2(function(node){
 	console.log(node.getElement())
 }, 'inOrder')

 tree.delete(tree.search(5))

console.log("Printing postOrder2 now")

 tree.traverseDF2(function(node){
 	console.log(node.getElement())
 }, 'postOrder2')

console.log("Printing postOrder3 now")

  tree.traverseDF2(function(node){
 	console.log(node.getElement())
 }, 'postOrder3')


//console.log("Searching now")
//console.log(tree.searchIterative(20))
//console.log(tree.findMin())

//console.log(tree.successor(x))



// 
// console.log("Iterator now")
// let x = tree.iterator(tree.traverseDF, 'preOrder')
// console.log(x.next())
// console.log(x.next())
// console.log(x.next())
// console.log(tree.children(tree.root()))


//console.log("Height is " + tree.height(tree.root()))




// The callback function within the traversal can be implemented as an object with these methods: method on a leaf, left() if the node is being touched as a leftChild, right() if being touched as a rightChild, or below(). 



