
/******************************************************************************
Compilation: javac BST.java
Execution: java BST
Dependencies: 
Data files: 


/** A Binary Search Tree (BST) implements the abstract data structure of ordered dictionry (also called associative array or symbol table) of generic key-value pairs.  

It supports the operations(methods):  
- insert/put, search/get/contains, delete  (Generic dictionary methods. Time Complexity: O(h). O(n) worst case if the tree is unbalanced)
- successor, predecessor, minimum and maximum (Special methods related to ordering. Time Complexity: O(h), O(n) worst case if the tree is unbalanced)
- size, isEmpty (Container methods. Time complexity O(1): if size field is maintained)
- iterator (returns iterator for iterating over the keys)

Convention: (Unlike the {@link java.util.Map} implementation) Values cannot be null. Setting the Value corresponding to a key to null is equivalent to deleting the key. 


This class requires that the Key type implements the Comparable interface and calls the compareTo() method to compare two keys. It does not call the methods equal() or hashCode() (as is the case in hash table implementation of dictionaries to compare two keys). 

For additional understanding, see:
{@link https://en.wikipedia.org/wiki/Associative_array}
{@link https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/BST.java.html}


Questions one can ask to the interviewer: 
- Can keys be equal? No, because it is a dictionary. If a new insert calls a key which already exists, it replaces the old value. 
- Shall we keep a parent pointer in the tree node structure? Keeping one makes things easier, but comes at the cost of memory. Also, some extra work to update parent pointers whenever we delete. 
- Shall we accept a key as argument to public methods or a node? Preferably key, as a user would not have access to node pointers.
- Exception handling: throw exceptions in the case of illegal arguments, or when an element is not found in methods like predecessor, successor, search, min, max etc.
- Recursive implementation of methods or iterative?



@author Piyush Ahuja
**/

// package name:


//import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value>{


	private TreeNode root; // root of BST. 


	private class TreeNode{

		private Key key;   
		private Value value;  //satellite data
		private TreeNode parent, leftChild, rightChild; //One can do without storing a pointer to parent, but it is useful in certain operations. 

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
	  Initializes an empty Binary Search Tree
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

// returntype is needed for recursive methods because we are not sending parent pointers to each recursive call. 
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

 	if(rootNode == null){
 		return null;
 	}
 	int cmp = key.compareTo(rootNode.key);
 	if(cmp <= 0){
 		return predecessorRecursive(rootNode.leftChild, key);
 	}
 	if (cmp > 0){
 		// max(rootNode.key, predecessorRecursive)
 		TreeNode probeRight = predecessorRecursive(rootNode.rightChild,key);
 		if (probeRight != null){
 			return probeRight; //anything not null would be bigger than root
 		} else {
 			return rootNode.key;
		  }
 	}

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

    // public Iterable<Key> keys(){
    	

    // }




  /*************************************************************************
      Check integrity of BST data structure.
   ***************************************************************************/


  public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    } 

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp <  0) return floor(x.left, key);
        Node t = floor(x.right, key); 
        if (t != null) return t;
        else return x; 

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
		System.out.println(test.get(8));
		System.out.println(test.get(10));
		System.out.println("S: "+ test.successor(10));
		test.delete(8);
		System.out.println("Minimum 1: " + test.min());
		System.out.println("maximum:" + test.max());
		System.out.println(test.get(8));
		System.out.println("Root's leftChild is: " + test.root.leftChild.key);
		System.out.println("Minimum 1.5: " + test.min());
		test.delete(9);
		System.out.println("Root is: " + test.root.key);
		System.out.println("Root's leftChild is: " + test.root.leftChild.key);
		System.out.println(test.get(12));	
		test.delete(6);	
		System.out.println("Minimum 2: " + test.min());
		test.delete(12);
		System.out.println("Minimum 3: " + test.min());
		System.out.println("S: "+ test.successor(10));	
		System.out.println("S2: "+ test.successor(test.successor(10)));
		
		//System.out.println("S: "+ test.successor(9));
	}

}