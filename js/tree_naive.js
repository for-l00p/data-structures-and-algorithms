//  An ADT is a class of objects whose behavior (e.g. whether they satisfy certain axioms, or invariants etc. ) defined with a set of values and a set of  operations and behavior of these operations. The specification can be axiomatic (Which axioms they satisfy) or via a mathematical model (what operations are valid). e.g. The set of integers is an ADT (with the Peano axioms being their axiomatic specification), while their implementation as fixed-width 32-bit bnary numbers in an array is a data structure. The computational costs (time for operations and space for representing values) differ from ADT to ADT and are useful in deciding which ADT is useful for which problem. 

// Tree is an Abstract Data Type. As a data structure, it is implemented as linked group of nodes, where each node has a value and a list of references to other nodes (its children). There is also the requirement that no two "downward" references point to the same node. Nodes in a tree could have next/previous references or references to their parent nodes. 

const Queue = require('./queue.js')
var LinkedListLibrary = require('./test.js');


function TreeNode(element){
	LinkedListLibrary.ListNode.call(this, element);
	this.parent = null;
	this.children = new LinkedListLibrary.SingleLinkedList() // or new SingleLinkedList()
}

TreeNode.prototype = Object.create(LinkedListLibrary.ListNode.prototype)

function Tree(data){

	
	// Node class is enough simulate hierarchical data via children and parents. We can add nodes as children of _root and also assign _root as the parents of those nodes.

	// Helper function
	// this.createTreeNode = function(data){
	// 	// Node factory function which  can also be implemented as a separate node class or constructor
	// 	return {
	// 		data: data,
	// 		parent: null,
	// 		children: new SingleLinkedList()
	// 		 //Should we initialize this to an empty array or a blank node??
	// 	};
	// }




	//Initialize Private  attributes 	

	// Root is public, but should be treated like a private variable so that it is encapsulated, otherweise other outside programs could tamper it. Here we are compelled to make it public so that the protype methods could access it.  
	this._root = new TreeNode(data);		
	this._size = 1;
	//console.log("Created root: " + JSON.stringify(this._root))
};




Tree.prototype = {
	// Global Accessor Methods to the tree 
	isEmpty: function() {
		return (this._size == 0)
	//Returns true if the tree is empty. True only initially
	},

	size: function() {
		return this._size
	// Returns the number of nodes in the tree. 
	},

	getRoot: function(){
		//console.log("Entered getRoot function");
		return this._root
	},

	// elements: function(){
	
	// //Collect all elements in a linear container (list or array); returns an iterator over that container. Running times: O(n). Iterable<Position<E>> (e.g. LinkedList<Node> (implementation of position interface))

	// },	

	traverseDF: function(callback){

	// This method traverses a tree with depth-first search.
		(function recurse(currentNode){ //Step 2
			//console.log("Traversing the children of: " + console.dir(currentNode));
			for (i = 0; i < currentNode.children.length; i++){
				//console.log("Entered the children array for: " + console.dir(currentNode) + " at " + i);
				recurse(currentNode.children.getNode(i)) //Step 3. When currentNode = leaf, length = 0
			}
			//console.log("Traversed the children of: " + console.dir(currentNode));
		callback(currentNode) //Step 4
		
		})(this.getRoot()) //Step 1. Immediate Invoke Recurse with the root node as argument

	},

	traverseBF: function(callback){
		var bfQueue = new Queue();
		bf.enqueue(this.getRoot());
		while(!bf.isEmpty){
			currentNode = bfQueue.dequeue();
			callback(currentNode)
			for (i = 0; i < currentNode.children.length; i++){
				temp = currentNode.children.getNode(i);
				bfQueue.enqueue(temp);
			}
		}
	},

	contains: function(data,traversal){
		var callback =  function(node){
			if (node.getElement === data ){
				//console.log("Found!: " + JSON.stringify(node));
				return node;
			}
		}
		traversal.call(this, callback)
	//searches for a node in a tree via the traversal inputted = BFS or DFS
	},

	insert: function(data, toData, traversal) {

		//var child = new TreeNode(data)
		//console.log("Inserting: " + JSON.stringify(child))
		var parent = null
		//Adds a node to a tree.
		var callback = function(node){
			if (node.getElement() === toData ){
				//console.log("Found!: " + JSON.stringify(node));
				parent = node;
			}
		}

		traversal.call(this, callback);
		if(parent){
			console.log("Adding to head: " + JSON.stringify(child));
			parent.children.addToTail(data);
			var child = parent.children.head
			child.parent = parent;
			child.children = new LinkedListLibrary.SingleLinkedList();
			console.log("New Look Parent: " + console.dir(parent));
		} else {
			throw new Error('cannot add node to a non-existent parent')
		}

	},

	remove: function(child, parent){
		//Removes the node of a tree

	},
}
	//Local Query methods for querying information about a node

	// isRoot: function(node){
	// 	return (this.getRoot === node)
	// },


	// isInternal: function(node){
	// 	return (node.children === null)? false: true
	// },

	// isExternal: function(node){
	//   return (node.children === null)? true: false
	// },
	// // Local Accessor Methods for accessing other nodes from a given node

	// parent: function(node){
	// 	return node.parent
	// },

	// children: function(node){
	// 	return node.children
	// }

	



//Optional:  iterator(); return an iterator of all the elements stored in the tree. Iterator<E>*/


//Positional Container Methods

// Tree.prototype.swapElements(firstNode, secondNode){
// 		temp = firstNode.data;
// 		firstNode.data = secondNode.data;
// 	    secondNode.data = temp;

// }


// Tree.prototype.replaceElement (node, data){

// 		node.data = data

// }


//Operations:

//Enumerating all the items
// Enumerating a section of a tree
// Searching for an item
// Adding a new item at a certain position on the tree
// Deleting an item
// Pruning: Removing a whole section of a tree
// Grafting: Adding a whole section to a tree
// Finding the root for any node
// Finding the lowest common ancestor of two nodes

var tree = new Tree('CEO');
//console.log(tree.isEmpty());
//console.log(tree.size());
//console.log(tree.getRoot())
tree.insert(5,'CEO', tree.traverseDF)
tree.insert(10,'CEO', tree.traverseDF)
tree.insert("me", 10, tree.traverseDF)

//console.log(tree.getRoot)
console.log(tree.traverseDF(node => console.log(node.getElement())))



