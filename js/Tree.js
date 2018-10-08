
const Queue = require('./QueueLinkedList.js')
const LinkedListLibrary = require('./SingleLinkedList.js');


function TreeNode(element){
	LinkedListLibrary.ListNode.call(this, element);
	this.parent = null;
	this.children = new LinkedListLibrary.SingleLinkedList() // or new LinkedListLibrary()
}

TreeNode.prototype = Object.create(LinkedListLibrary.ListNode.prototype)

function Tree(data){
	this._root = new TreeNode(data);		
	this.size = 1;
};



Tree.prototype = {

	isEmpty: function() {
		return (this.size == 0)
	//Returns true if the tree is empty. True only initially
	},

	getRoot: function(){
		//console.log("Entered getRoot function");
		return this._root
	},
	
	traverseDF: function(callback){

	// This method traverses a tree with depth-first search.
		(function recurse(currentNode){ //Step 2
			//console.log("Traversing the children of: " + JSON.stringify(currentNode.getElement()));
			for (var i = 0; i < currentNode.children.length; i++){
				//console.log("Entered the children linked list for: " + JSON.stringify(currentNode.getElement()) + ". Traversing to index: " + i);
				recurse(currentNode.children.getNode(i)) //Step 3. When currentNode = leaf, length = 0
			};
			//console.log("Traversed the children of: " + JSON.stringify(currentNode.getElement()));
			callback(currentNode) //Step 4
			//console.log("Ending for loop for " + currentNode.getElement())
		})(this.getRoot()) //Step 1. Immediate Invoke Recurse with the root node as argument

	},

	traverseBF: function(callback){
		var bfQueue = new Queue();
		bfQueue.enqueue(this.getRoot());
		while(!bfQueue.isEmpty()){
			//var nextTreeNode = bfQueue._head.data
			//console.log("Next element to dequeue:" + bfQueue._head.data.getElement())
			var currentTreeNode = bfQueue.dequeue();
			//console.log("currentTreeNode contains " + currentTreeNode.getElement())
			// console.log("Calling callback function on: " + JSON.stringify(currentTreeNode.getElement()))
			callback(currentTreeNode)
			for (var i = 0; i < currentTreeNode.children.length; i++){
				var temp = currentTreeNode.children.getNode(i);
				//console.log("Enqueing: " + JSON.stringify(temp.getElement()))
				bfQueue.enqueue(temp);
				//console.log("Is queue empty? : " + JSON.stringify(bfQueue.isEmpty()))
			}
			//console.log("Next element to deQueue is now: " + console.dir(bfQueue._head.data))
		}
		
	},

	

	insert: function(element, toElement, traversal) {

		//var child = new TreeNode(data)
		console.log("Inserting element: " + element)
		var parent = null
		//Adds a node to a tree.
		var callback = function(node){
			//console.log("Now comparing: " + node.getElement())
			if (node.getElement() === toElement ){
				//console.log(JSON.stringify("Found parent: " + node.getElement())   );
				parent = node;
			}
		}

		traversal.call(this, callback);
		
		if(parent){
			//console.log("Adding to tail: " + JSON.stringify(data));
			var childrenList = parent.children
			childrenList.addToTail(element);
			//console.log("Element " + data + " added to tail; now fetching node" )
			//console.log("List: " + console.dir(childrenList))
			var newChild = childrenList.getNode(childrenList.length-1)
			//console.log("Fetched: " + newChild.getElement() +  ". Now setting parent" )
			newChild.parent = parent;
			newChild.children = new LinkedListLibrary.SingleLinkedList();
			//console.log(JSON.stringify("Added new child to " + parent.getElement() + " with element " + newChild.getElement())  );
		} else {
			throw new Error('cannot add node to a non-existent parent')
		}

	},


    contains: function(element,traversal){
    	var candidateNode;
		var callback =  function(node){
			if (node.getElement() === element ){
				candidateNode = node;
			}
		}
		traversal.call(this, callback)
		if (candidateNode){
			console.log("Found!: " + JSON.stringify(candidateNode.getElement()));
			return candidateNode
		} else {
			console.log(element + " Not found!")
		}
	//searches for a node in a tree via the traversal inputted = BFS or DFS
	},


	remove: function(element, traversal){
		//Problem: This removes the whole subtree of the node. How to resolve?
		
		var node = this.contains(element,traversal)
		if (node) {
			parent = node.parent,
			parent.children.removeNode(element);
		}

	}
}

// var tree = new Tree(10);
// tree.insert(12,10, tree.traverseDF)
// tree.insert(5, 12, tree.traverseDF)
// tree.insert(20, 10, tree.traverseDF)
// tree.insert(15,20,tree.traverseDF)
// tree.insert(16,20, tree.traverseDF)
// tree.insert(30,20, tree.traverseDF)
// tree.insert(6,12, tree.traverseDF)
// tree.insert(9,6, tree.traverseDF)
// console.log(tree.contains(16, tree.traverseBF))
// console.log(tree.contains(30, tree.traverseDF))
// console.log(tree.contains(31, tree.traverseDF))
// console.log(tree.contains(32, tree.traverseBF))
// console.log(tree.remove(30, tree.traverseBF))
// console.log(tree.remove(12, tree.traverseBF))
// console.log(tree.contains(30, tree.traverseBF))
// console.log(tree.traverseBF(node => console.log("BFS Printing: " + node.getElement())))
var x = new TreeNode(5)
console.log(x)








