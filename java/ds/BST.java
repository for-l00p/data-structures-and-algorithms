
/******************************************************************************
Compilation: javac BST.java
Execution: java BST
Dependencies: 
Data files: 


/** A Binary Search Tree (BST) implements the abstract data structure of ordered dictionary (also called associative array or symbol table) of generic key-value pairs.  

It supports the operations(methods):  
- insert/put, search/get/contains, delete  (Generic dictionary methods. Time Complexity: O(h). O(n) worst case if the tree is unbalanced)
- successor, predecessor, minimum and maximum (Special methods related to ordering. Time Complexity: O(h), O(n) worst case if the tree is unbalanced)
- size, isEmpty (Container methods. Time complexity O(1): if size field is maintained)
- iterator (returns iterator for iterating over the keys)

Still to implement: rank, size and select methods.

Convention: (Unlike the {@link java.util.Map} implementation) Values cannot be null. Setting the Value corresponding to a key to null is equivalent to deleting the key. 


This class requires that the Key type implements the Comparable interface and calls the compareTo() method to compare two keys. It does not call the methods equal() or hashCode() (as is the case in hash table implementation of dictionaries to compare two keys). 

For additional understanding, see:
{@link https://en.wikipedia.org/wiki/Associative_array}
{@link https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/BST.java.html}


Questions one can ask to the interviewer: 
- Can keys be equal? No, because it is a dictionary. If a new insert calls a key which already exists, it replaces the old value. 
- Shall we keep a parent pointer in the tree node structure? Keeping one makes things easier, but comes at the cost of memory. Also, some extra work to update parent pointers whenever we delete. 
- Exception handling: throw exceptions in the case of illegal arguments, or when an element is not found in methods like predecessor, successor, search, min, max etc.
- Recursive implementation of methods or iterative?



@author Piyush Ahuja
**/

// package name:


import java.util.*; 

public class BST<Key extends Comparable<Key>, Value>{


	private TreeNode root; 


	private class TreeNode{

		private Key key;   
		private Value value;  //satellite data
		private TreeNode parent, leftChild, rightChild; //One can do without storing a pointer to parent, but it makes certain functions simpler to write. 

		// private int size;

 /** Constructor for the node **/

		public TreeNode(Key key, Value value){
			System.out.println("Creating new node with key: " + key + " and value: " + value);
			this.key = key;
			this.value = value;
		// this.size = size;
		}

	}

/**
 * Initializes an empty Binary Search Tree
**/ 

	public BST(){

	}



	private boolean isEmpty(){
		return this.root == null;
	}


  /*************************************************************************
      Insertion
   ***************************************************************************/


/**
 * Inserts the key-value pair in the dictionary, replacing the old value with the new value if the dictionary already contains the specified key. If the speficied value is null, then it deletes the key. 
 * Observation: New Insertion is always at a leaf position. 
 * 
 * @param key the key
 * @param value the value
 * @return not needed
 * @throws IllegalArgumentException
 */

	public void insert(Key key, Value value){

		if (key == null) throw new IllegalArgumentException("calls insert() with a null key");
		if (value == null){
			delete(key);
			return;
		}
		TreeNode newNode = new TreeNode(key, value);
		insertIterative(this.root, newNode);
		//this.root = insertRecursive(this.root, newNode);

	}

	private void insertIterative(TreeNode rootNode, TreeNode newNode){
		if (rootNode == null){
			//assert rootNode != null
			System.out.println("Called insert helper on empty subtree.");
			this.root = newNode;
			newNode.parent = null;
			return;
		} else {
			TreeNode parent = null;
			TreeNode currentNode = rootNode;
			int cmp = newNode.key.compareTo(currentNode.key);

			while(currentNode != null & cmp != 0){
				parent = currentNode;
				if(cmp < 0){
					currentNode = currentNode.leftChild;
				} else {
					currentNode = currentNode.rightChild;
				}
				if (currentNode != null){
					cmp = newNode.key.compareTo(currentNode.key);
				}
			}
			newNode.parent = parent;
			if (currentNode == null){
				if(cmp < 0){
					parent.leftChild = newNode;
				} else {
					parent.rightChild = newNode;
				}
			} else {
				//assert currentNode.key == newNode.key : "Keys are not equal";
				currentNode.value = newNode.value;
		 	 }
		}  

	}

// returntype is needed for recursive methods because we need to know where to insert: as a leftChild or a rightChild of the parent. We are not sending parent pointers as parameters to a recursive call, so the function must return something so the additinal work can be done. 
	private TreeNode insertRecursive(TreeNode rootNode, TreeNode newNode){

		if (rootNode == null){
			rootNode = newNode;
		} else {
			int cmp = newNode.key.compareTo(rootNode.key);
			if (cmp < 0){
				rootNode = insertRecursive(rootNode.leftChild, newNode);
			} else if (cmp > 0){
				rootNode = insertRecursive(rootNode.rightChild, newNode);
			} else {
				rootNode.value = newNode.value;
			}	
		}

		return rootNode;

	}



  /*************************************************************************
     Deletion
   ***************************************************************************/


/**
 * @param key to be deleted
 * @return null if key is not present, the node containing the key if it is
 * @throws IllegalArgumentException
 */
	public void delete(Key key){

		if (key == null) throw new IllegalArgumentException("called delete() with null key");
		TreeNode nodeToDelete = getIterative(this.root, key);
		if (nodeToDelete == null){
			System.out.println("Key to be deleted is not present");
			return;
		}
		//deleteIterative(nodeToDelete);
		this.root = deleteRecursive(this.root, key);
	}

/**
 * Same as the algorithm in CLRS. oneChilDToParent() takes care of the case when the node to be deleted has atmost one child. Since it is not a recursive method, we do not need to pass @param of the root of the subtree: we maintain all the pointers, and we do not need to return anything. 
 * 
 * @param nodeToDelete the node to be deleted
 * @return nothing
 */

	private void deleteIterative(TreeNode nodeToDelete){

		if (nodeToDelete.leftChild != null && nodeToDelete.rightChild != null){
			TreeNode successor =  successor(nodeToDelete); //successor cannot be null since both children present
		 	nodeToDelete.key = successor.key;
		 	nodeToDelete.value = successor.value;
			nodeToDelete = successor;
			// if the nodeToBeDelete has atmost one child
		} 
		oneChildToParent(nodeToDelete);

	}



	/** 
	 
	 * 
	 * @param rootNode the root of the subtree where the recursive call is made
	 * @param Key the key to be deleted
	 * @return the rootnode is always returned. 
	 * * Why is a return type required? Because we need to do some work even after a node is deleted: we need to change it's parent's pointers. We can do this while deleting the node, but then we would need to pass the parent pointer in the recursive call. Alternatively, we can change the parent pointer after the recursive call returns, which we do here. For this, we return the node to which the parent should now point. 
	 *
	 */
	private TreeNode deleteRecursive(TreeNode rootNode, Key key){

		if (rootNode == null){
			return null;
		}
		int cmp = key.compareTo(rootNode.key);
		if (cmp < 0){
			rootNode.leftChild = deleteRecursive(rootNode.leftChild, key);
			if (rootNode.leftChild != null){
				rootNode.leftChild.parent = rootNode;
			}
		} else if (cmp > 0){
			rootNode.rightChild = deleteRecursive(rootNode.rightChild, key);
			if (rootNode.rightChild != null){
				rootNode.rightChild.parent = rootNode;
			}
		} else {
			// Base Case of recursion. rootNode.key = key
			if (rootNode.leftChild == null){
				return rootNode.rightChild;
			}

			if (rootNode.rightChild == null){
				return rootNode.leftChild;
			} 
			// Base Case when both leftChild and rightChild are present
			TreeNode successor = successor(rootNode);
			System.out.println("Successor is: " + successor.key);
			Key tempKey =  successor.key;
			Value tempValue = successor.value;
			oneChildToParent(successor);
			rootNode.key = tempKey;
			rootNode.value = tempValue;
		  }

		  return rootNode;
		 
	}



/**
 * oneChildToParent() is a helper method employed the delete() case where the node to be deleted has atmost one child. in this case, we simply remove the node, and connect the child directly with the parent.  
 *
 * If first stores the pointer to the child node (possibly null) in nodeToMove.
 * Two pointers need to be replaced in the normal case (one in the parent and one in the child), and only one in the special case of a root or leaf being deleted. If the root is the leaf, then no pointer change is needed. 
 * 
 *
 * If this method is called on the root (which has no parent), then it replaces the root of the BST to its child node.
 * 
 * @param nodeToDelete node to be deleted
 * @throws IllegalArgumentException
 * @throws Error in the case it is called on a node with two children
 */
	private void oneChildToParent(TreeNode nodeToDelete){

		if (nodeToDelete == null) throw new IllegalArgumentException("called oneChildToParent() with null as argument");

		if (nodeToDelete.leftChild != null && nodeToDelete.rightChild !=null){
			throw new Error("called oneChildToParent on a node with two children");
		}


		TreeNode parent = nodeToDelete.parent; // Could be null incase of root.
		TreeNode nodeToMove;

		if (nodeToDelete.leftChild == null){
			nodeToMove = nodeToDelete.rightChild;
		} else {
			nodeToMove = nodeToDelete.leftChild;
		}

		// Change child pointer if the nodeToDelete is not a leaf
		if (nodeToMove != null){
			nodeToMove.parent = parent;
		}


		// Change parent pointer if nodeToDelete is not a root. 
		if (parent == null){
			this.root = nodeToMove;
			System.out.println("called oneChildToParent on root. Replacing root with its childNode");
			return;
		} else {
			if (nodeToDelete == parent.leftChild){
				parent.leftChild = nodeToMove;
			} else {
				parent.rightChild = nodeToMove;
		  	}		
		}

		
	}
	


  /*************************************************************************
      Search and Get
   ***************************************************************************/


/**
 * @param key the key
 * @return Value corresponding to the key or null if the key is not present. 
 * @throws Illegalargumentexception
 */
	public Value get(Key key){

		if (key == null) throw new IllegalArgumentException("called get() with a null key");
		TreeNode node = getRecursive(this.root, key);
		if (node == null){
			System.out.println("key not found: " + key);
			return null;
		} else {
			return node.value;
		}
	}

/**
 * Private helper method. Searches for key in a subtree with given root. Starts with root and ends with either the node or falls down a leaf. Returns either the node or null if the key is not present. 
 * @param rootNode the root of the subtree
 * @param key to be searched for
 * @return null if key is not present, or node where node.key = key
 */
	private TreeNode getIterative(TreeNode rootNode, Key key){

		TreeNode currentNode = rootNode;
	  	
		int cmp = key.compareTo(currentNode.key);
		while(currentNode != null && cmp != 0){
			if (cmp < 0 ){
				currentNode = currentNode.leftChild;
			} else {
				currentNode = currentNode.rightChild;
			}
			if(currentNode != null){
				cmp = key.compareTo(currentNode.key);
			}
		}
		return currentNode;
	}

/**
 * Private helper method with recursive implementation
 * @param
 * @param
 * @return
 */
	private TreeNode getRecursive(TreeNode rootNode, Key key){

		if (rootNode == null || key.compareTo(rootNode.key) == 0){
			return rootNode;
		} else {
			int cmp = key.compareTo(rootNode.key);
			if (cmp < 0){
				return getRecursive(rootNode.leftChild, key);
			} else {
				return getRecursive(rootNode.rightChild, key);
			}
		}
	}



  /*************************************************************************
     Order Related Methods
   ***************************************************************************/



	public Key successor(Key key){
		if (key == null) throw new IllegalArgumentException("called successor() with null as argument");
		TreeNode node = getIterative(this.root, key);
		if (node == null){
			throw new Error("The key given is not present in the tree");
		}

		TreeNode successor = successor(node);
		if (successor == null){
			System.out.println("The given key is the largest in the tree");
			return null;
		} else {
			return successor.key;
		}

	}

/**
 * 
 * @param
 * @return null if no successor is there. 
 * @throws IllegalArgumentException if called with null
 */
	private TreeNode successor(TreeNode node){
		if (node == null){
			throw new IllegalArgumentException("called successor() with null");
		}
		//System.out.println("Finding successor of: " + node.key);

		if (node.rightChild != null){
			return min(node.rightChild);
		} else {
			TreeNode currentNode = node;
			TreeNode parent = node.parent;
			while(parent != null && parent.rightChild == currentNode){
				currentNode = parent;
				parent = currentNode.parent;
			}
			return parent;

		}
	}

/**
 *Returns the largest key in the dictionary which is less than or equal to the given key
 * 
 * @param key the key
 * @return the largest key in the dictionary lessthan or equal to the given key.
 * @throws IllegalArgumentException if key is null
 * @throws NoSuchElementException if there is no such key
 * 
 */

	public Key predecessor(Key key){

		if (key == null) throw new IllegalArgumentException ("called predecessor(key) with null");
		TreeNode node = getIterative(this.root, key);
		if (node == null){
			throw new Error("The key given is not present in the tree");
		}
		TreeNode predecessor = predecessor(node);
		if (predecessor == null){
			System.out.println("The given key is the smallest in the tree");
			return null;
		} else {
			return predecessor.key;
		}
		
	}

	

	private TreeNode predecessor(TreeNode node){

		if (node == null){
			throw new IllegalArgumentException("called predecessor(node) with null");
		}

		if(node.leftChild !=null){
			return max(node.leftChild);
		} else {
			TreeNode parent = node.parent;
			TreeNode currentNode = node;
			while(parent != null && currentNode == parent.leftChild){
				currentNode = parent;
				parent = currentNode.parent;
			}

			return parent;
		}
	}

	private TreeNode predecessorRecursive(TreeNode rootNode, Key key){

		TreeNode result = null;
		if(rootNode == null){
 			return null;
 		}
 		int cmp = key.compareTo(rootNode.key);
 		if(cmp <= 0){
 			result = predecessorRecursive(rootNode.leftChild, key);
 		}
 		if (cmp > 0){
 		// max(rootNode.key, predecessorRecursive)
 			TreeNode probeRight = predecessorRecursive(rootNode.rightChild,key);
 			if (probeRight != null){
 				result = probeRight; //anything not null would be bigger than root
 			} else {
 				result = rootNode;
		  }

 	}

 	return result;

 }

	public Key min(){
		return min(this.root).key;
	}



	private TreeNode min(TreeNode rootNode){
		if (rootNode == null){
			throw new IllegalArgumentException("called min() with null");
		}

		while(rootNode.leftChild != null){
			rootNode = rootNode.leftChild;
		}
		return rootNode;
	}


	public Key max(){
		return max(this.root).key;
	}
	private TreeNode max(TreeNode rootNode){
		if (rootNode == null){
			throw new IllegalArgumentException("called max() with null");
		}
		while(rootNode.rightChild != null){
			rootNode = rootNode.rightChild;
		}

		return rootNode;
	}



  /*************************************************************************
      Iterators and Traversals 
   ***************************************************************************/




/**
 * returns the keys in their inOrdertraversal
 * @return [description]
 */
    public Iterable<Key> keys(){

    	Queue<Key> queue = new LinkedList<Key>();
    	inOrderTraversal((node) -> queue.add(node.key));
    	return queue;
    }

     public Iterable<Key> levelOrderKeys(){
     	Queue<Key> queue = new LinkedList<Key>();
    	levelOrderTraversal((node) -> queue.add(node.key));
    	return queue;
     }



// Interfaces are implitly static and thus can be called from static contexts.  Processable is the interface which processes tree nodes.
 	private interface Processable<T>{
 		void process(T node);
 	}

 	private void levelOrderTraversal(Processable<TreeNode> callback){
 		Queue<TreeNode> queue = new LinkedList<TreeNode>();
 		TreeNode currentNode = this.root;
 		queue.add(currentNode);
 		while(!queue.isEmpty()){
 			currentNode = queue.remove();
 			callback.process(currentNode);
 			if(currentNode.leftChild != null){
 				queue.add(currentNode.leftChild);
 			}

 			if(currentNode.rightChild != null){
 				queue.add(currentNode.rightChild);
 			}

 		}
 	}
    	

/**
 * PreOrder lends itself to a simple, readable code with a stack. 
 * @param callback [description]
 */

     private void preOrderTraversal(Processable<TreeNode> callback){

     		Stack<TreeNode> nodeStack = new Stack<>();
     		TreeNode currentNode = this.root;
     		nodeStack.push(currentNode);
     		while(!nodeStack.isEmpty()){
     			currentNode = nodeStack.pop();
     			callback.process(currentNode);

     			if(currentNode.rightChild != null){
     				nodeStack.push(currentNode.rightChild);
     			}

     			if (currentNode.leftChild != null){
     				nodeStack.push(currentNode.leftChild);
     			}
     		}
     }
/**
* Can we implement preOrder without a Stack? Yes. Here we use two flags. (Note the similarity to inOrderTraversal and postOrderTarversal where each loop finds a successor. Here each loop processes the left subtree, and then switches control to the root of the next node for whom the left subtree is unvisited. For finding such a node, we either go to first ancestor who has a rightChild). Questions: Do we need two flags? Can you prove correctness? 
* @param callback [description]
*/

     private void preOrderTraversalWithoutStack(Processable<TreeNode> callback){

     	boolean visitedLeft = false;
     	boolean visitedRight = false;
     	TreeNode currentNode = this.root;

     	while(currentNode != null){

     		if(visitedLeft == false && visitedRight == false){
     			callback.process(currentNode);
     			while(currentNode.leftChild != null){
     				currentNode = currentNode.leftChild;
     				callback.process(currentNode);
     			}
     			visitedLeft = true;
     		}

     		if (visitedLeft == true && visitedRight == false){

     			if(currentNode.rightChild != null){
     				currentNode = currentNode.rightChild;
     				visitedLeft = false;
     			} else {
     				visitedRight = true;
     			}

     		}

     		if (visitedLeft == true && visitedRight == true){
     			TreeNode parent = currentNode.parent;
     			if(parent != null && currentNode == parent.leftChild){
     				visitedRight = false; 
     			} 
     			currentNode = parent;

     		}

     	}


     }

/**
 * General Thoughts on inOrderTraversal: 
 *
 * Simulate the first few steps of the algorithm and see a pattern. Look how the nodes being printed jump around.
 *
 *
 * During the traversal,  each node is visited twice: the first time, then after the left child is visited. It is processed during the second visit. 
 * 
 * Each iterative algorithm runs a loop. A state of the system is the input.  Here we can keep a pointer to the node that is visited to store the state of the system. But that is not sufficient; we also need some way of knowing which visit it is when we visit a node to find out what to do with it in the loop (process or proceed to proceed to some other node). 
 * 
 
 *   Could we find out which visit it is by the relation of "the node we are visiting from" to "the node we are visiting to." No: Even if it can be done, this wouldnt be an elegant, Because a node might want to "jump" to a remote ancestor in the traversal whose relation to the current node might be difficult to ascertain. 
 *
 * 
 * The structure of the sequence of operations (store, access or retrieve) we perform on the nodes is similar to the abstract mathematical structure of a Stack. For example, when we first visit a node, we need to store its flags (push it to the stack), when we visit it again, we access the node, see its flags, modify them, and store them again (peek/push). When we finally visit it, we don't visit it again (pop it from the stack). 
 * 

 *
 * If we don't have parent pointers, then we need to have some way to backtrack to the node we need to visit next (which might not be the parent. It's the successor). We do this with a stack. 
 *
 * For the iterative algorithms, we decide the terminal conditions of the loop: the loop ends when the tree is traversed.
 * 
 *
 *Resources: http://plasmasturm.org/log/453/
 *
 *
 **/


     private void inOrderTraversal(Processable<TreeNode> callback){

     	Stack<TreeNode> nodeStack = new Stack<>();//The stack stores nodes to be visited again.
     	TreeNode currentNode = this.root; // This variable stores a node visited for the first time. 

     	while(!nodeStack.isEmpty() || currentNode !=null){
     		// If there is still work to be done
     		if(currentNode !=null){
     			nodeStack.push(currentNode);
     			currentNode = currentNode.leftChild;
     		} else {
     			currentNode = nodeStack.pop();
     			callback.process(currentNode);
     			currentNode = currentNode.rightChild;
     		}

     	}
 	}


/*
 * 
 * Pattern: After processing(i), we always process successor(i). We start with the minimum element and end with the maximum element. successor(i) takes log(n) time in the worst case. So this algorithm should run in O(nlogn).  Can we reduce it? Yes, to O(n):
 *
 *  We need to have some way to backtrack to successor in case the successor is an ancestor. This will be easier/faster with parent pointers - otherwise we might need to start from the root - and each node might be visited more than thrice.  Parent pointers would take an O(n) space. With parent pointers, each node is visited atmost thrice. So running time becomes O(n). 
 *  

 * @return [description]
 */
     public void inOrderTraversalWithoutStack(Processable<TreeNode> callback){
     	TreeNode currentNode = this.root;
     	boolean visitedLeft = false;
     	 //After each iteration, currentNode either stores the next element to be processed, or  root of the subtree whose minimum element is next to be printed. The visitedLeft flag tells us which is the case. 
     	while(currentNode != null){

    		if(visitedLeft == false){
    			currentNode = min(currentNode);	
    		}
    		callback.process(currentNode);
    		visitedLeft = true;
    		if(currentNode.rightChild != null){
    				currentNode = currentNode.rightChild;
    				visitedLeft = false;
    			} else {
    				TreeNode parent = currentNode.parent;
    				while(parent != null && currentNode == parent.rightChild){
    					currentNode = currentNode.parent;	
    					parent = currentNode.parent;
    				}
    				if(parent == null){
    					break;
    				} else {
    					currentNode = parent;
    				}

    		}
		}

     }

/**
* Alternatively, we can use a stack. This speeds up the running time to O(n) at the expense of O(logn) memory, since we do not need to do the O(logn) work to find the successor. 
* 
* As we said, we need some way of knowing which visit it is when we visit a node to find out what to do with it in the loop. Here we store nodes for whom a second visit is due in a stack, and the pointer to the node visited for the first time in separate variable. If we pop from the stack to assign to the pointer, it is a second visit; else it is not. 
 *
 * @param  callback [description]
 * @return          [description]
 */




/**
 *   As we said, we need some way of knowing which visit it is when we visit a node to find out what to do with it in the loop: here we store a flag (corresponding to the node) as part of the the state of the system.  But would we need have to store a flag for each node which is yet to be processed? Not really. We only need to keep track of the state of the top of the stack. Everytime we modify the top, we modify the state. 
 * 
 * @param callback [description]
 */
     private void inOrderTraversalWithFlag(Processable<TreeNode> callback){
     	boolean visitedLeft = false;
     	TreeNode currentNode = this.root;
     	Stack<TreeNode> nodeStack = new Stack<>();
     	nodeStack.push(currentNode);

     	while(!nodeStack.isEmpty()){
     		currentNode = nodeStack.peek();
     		// If left subtree is visited, pop the node, process it, and push the right child on the stack.
     		if(visitedLeft == true){
     			nodeStack.pop();
     			callback.process(currentNode);
     			if(currentNode.rightChild != null){
     				nodeStack.push(currentNode.rightChild);
     				visitedLeft = false;
     			}

     		} 
     		// If left subtree is not visited, push the leftChild (the current Node is already on the stack) if present. Else, mark leftsubtree visited.
     		if (visitedLeft == false){
     			if(currentNode.leftChild != null){
     				nodeStack.push(currentNode.leftChild);
     			} else {
     				visitedLeft = true;
     			}		
     		}
     	}
     }


/**
* Using two stacks. Intuition: The iterative method just uses an explicit stack to do what the recursive method does implicitly. https://stackoverflow.com/questions/49409193/understanding-the-logic-in-iterative-postorder-traversal-implementation-on-a-bin/50611267#50611267
*
* Combinatorial Insight: The sequence generated by left-to-right postOrder traversal (LRV) is the same as the 'reverse' of VRL - the sequence generated by right-to-left preOrder traversal. If, during the corse of right-to-left preOrder, instead of printing/processing, we just push the value onto another stack, then this reversing the sequence wil be easier. 

* @param callback [description]
*/
	private void postOrderTraversal(Processable<TreeNode> callback){

     	Stack<TreeNode> reverseStack = new Stack<>();
     	Stack<TreeNode> nodeStack = new Stack<>();
     	TreeNode currentNode = this.root;
     	nodeStack.push(currentNode);
     	while(!nodeStack.isEmpty()){
     		currentNode = nodeStack.pop();
     		reverseStack.push(currentNode);
     		if(currentNode.leftChild != null){
     			nodeStack.push(currentNode.leftChild);
     		}

     		if(currentNode.rightChild !=null){
     			nodeStack.push(currentNode.rightChild);
     		}
     	}

     	while(!reverseStack.isEmpty()){
     		callback.process(reverseStack.pop());
     	}

     }
/**
 * What we do when we encounter a node depends on from where we are approaching it. If we are approaching it from the parent, then we need to push the node, and then its children. If we are approaching it from the left, then we need to push it and its right child (Note that when we use a stack, we only encounter a node from the left when its right child is empty; otherwise we directly backtrack to the right child from the left one). When we traverse it from the right, we process it. 
 * 
 * Here we keep a flag to tell us whether we have already visited the current node are encountering. 
 * Corresponds to inOrderTraversalWithFlag
 * @param callback [description]
 */

     private void postOrderTraversalWithFlag(Processable<TreeNode> callback){

     	Stack<TreeNode> nodeStack = new Stack<>();
     	TreeNode currentNode = this.root;
     	boolean visitedLeft = false;
     	nodeStack.push(currentNode);

     	while(!nodeStack.isEmpty()){

     		currentNode = nodeStack.pop();
     			

     		if (visitedLeft == false){

     			nodeStack.push(currentNode);
     			if(currentNode.leftChild == null && currentNode.rightChild == null){
     				visitedLeft = true;
     				continue;
     			}

     			if(currentNode.rightChild != null){
     				nodeStack.push(currentNode.rightChild);
     			}

     			if(currentNode.leftChild != null){
     				nodeStack.push(currentNode.leftChild);
     			}

     			
     		}

     		if (visitedLeft == true){
     			callback.process(currentNode);

     			if (!nodeStack.isEmpty() && nodeStack.peek() != currentNode.parent){
     				visitedLeft = false;
     			} // If parent pointers are not there, just do nodeStack.peek().leftChild != currentNode && nodeStack.peek().rightChild != currentNode
     		}

     	}
     }

/**
* What if we don't have parent pointers?
* Cleaner handling of cases of the above code
* 
* @param callback [description] 
*/
     private void postOrderTraversalWithFlag2(Processable<TreeNode> callback){

     	Stack<TreeNode> nodeStack = new Stack<>();
     	TreeNode currentNode = this.root;
     	boolean visitedLeft = false;
     	nodeStack.push(currentNode);

     	while(!nodeStack.isEmpty()){

     		currentNode = nodeStack.pop();
     			//System.out.println("Current node now:" + currentNode.key);

     		if (visitedLeft == false){

     			if (currentNode.leftChild == null && currentNode.rightChild == null){
     				visitedLeft = true;
     			}

     			if (currentNode.leftChild == null && currentNode.rightChild != null){
     				nodeStack.push(currentNode);
     				nodeStack.push(currentNode.rightChild);

     			}

     			if (currentNode.leftChild != null && currentNode.rightChild == null){
     				nodeStack.push(currentNode);
     				nodeStack.push(currentNode.leftChild);
     			}

     			if (currentNode.leftChild != null && currentNode.rightChild != null){
     				nodeStack.push(currentNode);
     				nodeStack.push(currentNode.rightChild);
     				nodeStack.push(currentNode.leftChild);
     			}

     		}

     		if (visitedLeft == true){
     			callback.process(currentNode);
     				
     			if (!nodeStack.isEmpty() && nodeStack.peek() != currentNode.parent){
     					visitedLeft = false;
     				}
     			}

     	}
     }

/**
* We can keep the same information via a "Ready to visit" flag and keeping track of the previous node.
* @param callback [description]
*/

     private void postOrderTraversalWithPrevious(Processable<TreeNode> callback){

     	boolean readyToVisit = false;
     	Stack<TreeNode> nodeStack = new Stack<>();
     	TreeNode currentNode = this.root;
     	TreeNode previousNode = null;
     	nodeStack.push(currentNode);
     	while(!nodeStack.isEmpty()){

     		currentNode = nodeStack.pop();

     		if (currentNode.leftChild == null && currentNode.rightChild == null){
     			readyToVisit = true;
     		}

     		if (currentNode.leftChild != null && currentNode.leftChild == previousNode){
     			readyToVisit = true;
     		}

     		if (currentNode.rightChild != null && currentNode.rightChild == previousNode){
     			readyToVisit = true;
     		}

     		if (readyToVisit){
     			callback.process(currentNode);
     			readyToVisit = false;
     			previousNode = currentNode;
     		} else {
     				// Case when the previous node is not one of children, but one of parent.
     			nodeStack.push(currentNode); 
     			if (currentNode.rightChild != null){
     				nodeStack.push(currentNode.rightChild);
     			}
     			if (currentNode.leftChild != null){
     				nodeStack.push(currentNode.leftChild);
     			}		
     		}

     	}
     }

/**
* We don't even need a readyToVisit Flag: this information can be gleaned from investigating the stack.  
* Note the similarity with preOrderTraversalWithoutStack: we visit the leftSubtree until the leaf, process it, then shift control to the first ancestor who has an unvisited rightchild.  
* @param callback [description]
*/
     private void postOrderTraversalWithPrevious2(Processable<TreeNode> callback){

     	Stack<TreeNode> nodeStack = new Stack<>();
     	TreeNode previousNode = null;
     	TreeNode currentNode = this.root;
     	nodeStack.push(currentNode);

     	while(!nodeStack.isEmpty()){
     		// Non-null currentNode corresponds to an unvisited left subtree. If there is a node on which the recursive call is made, we have a left subtree to explore. 
     		//If current node == null, this means the leftSubtree is visited, and we must backtrack to teh previous node. we have to investigate the stack to see the next node - if an unvisited right subtree exists. If not, we visit the next node and set the stage for the next investigation of the stack by setting currentNode == null. Can the next node in the stack have an unvisited left subtree? 
     			
     		if (currentNode != null){
     			nodeStack.push(currentNode);
     			previousNode = currentNode;
     			currentNode = currentNode.leftChild;
     		} 

     		if (currentNode == null){
     			currentNode = nodeStack.peek();
     			if (currentNode.rightChild !=null && previousNode != currentNode.rightChild){
     				currentNode = currentNode.rightChild;
     			} else {
     				currentNode = nodeStack.pop();
     				callback.process(currentNode);
     				previousNode = currentNode;
     				currentNode = null;
     			}

     		}

     	}

     }


  /*************************************************************************
      Check integrity of BST data structure.
   ***************************************************************************/


/**
 * Does this BST satisfy BST property?
 * @return [description]
 */


	private boolean isBST(){

		return isBST(this.root, null, null);
	}
/*
Order is strict: two nodes cannot have the same key.
 */
   	private boolean isBST(TreeNode root, Key min, Key max){
   		if (root == null) return true;
   		if (min !=null && root.key.compareTo(min) <= 0) return false;
   		if (max !=null && root.key.compareTo(max) >= 0) return false;
   		return isBST(root.leftChild, min, root.key) && isBST(root.rightChild, root.key, max);
   }

/**
 * Unit tests the BST data structure
 * @param args the command-line arguments

 */
	public static void main(String[] args){

		BST<Integer, String > test = new BST<Integer, String>();
		test.insert(8, "hehe");
		System.out.println("Root is: " + test.root.key);
		test.insert(12, "new");
		test.insert(10, "moo");
		test.insert(6, "yoo");
		test.insert(9, "Bliss");
		test.insert(14, "Wes");
		// System.out.println(test.get(8));
		// System.out.println(test.get(10));
		// System.out.println("S: "+ test.successor(10));
		// test.delete(8);
		// System.out.println("Minimum 1: " + test.min());
		// System.out.println("maximum:" + test.max());
		// System.out.println(test.get(8));
		// System.out.println("Root's leftChild is: " + test.root.leftChild.key);
		// System.out.println("Minimum 1.5: " + test.min());
		// test.delete(9);
		// System.out.println("Root is: " + test.root.key);
		// System.out.println("Root's leftChild is: " + test.root.leftChild.key);
		// System.out.println(test.get(12));	
		// test.delete(6);	
		// System.out.println("Minimum 2: " + test.min());
		// test.delete(12);
		// System.out.println("Minimum 3: " + test.min());
		// System.out.println("S: "+ test.successor(10));	
		// System.out.println("S2: "+ test.successor(test.successor(10)));

		// Processable<TreeNode<String, Integer> callback = new Processable<>(){
		//  	public void process(TreeNode t){
		// 		System.out.println(t.key);
		// 	}
		//  };
		

		System.out.println("inOrderKeys:");
		
		for(Integer key: test.keys()){
			System.out.println(key);
		}

		System.out.println("levelOrderkeys:");
		
		for(Integer key: test.levelOrderKeys()){
			System.out.println(key);
		}

		System.out.println(test.isBST());
	
	
	}

}