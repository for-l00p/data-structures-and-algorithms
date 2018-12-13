
/**
 *
 * 
RB Tree has the property that any path from the root to leaf is not more than twice as long as any other path. We ensure this property holds by maintaining these invariant properties: no node of a bit 1 (red) can be a child of a node with bit 1 (red), and on any path from the root to leaf has the same number of nodes with bit 0 (black). We can ensure these properties hold after every insertion/deletion without worsening the time complexity.  

- Root is always black. 
- We create external nodes (leaf) by adding external nodes
- We do not colour external nodes (or we can consider the black)


Properties:

-  A red node has only black children
- The black-depth of all external nodes is to be the same. The black-depth of an external node is the number of black ancestors.

A RB tree with black-height h has atleast 2^h-1 (when all nodes are black)  and atmost 2^2h-1 internal nodes (when alternative levels are red).  Hence Log4n < h < log2n + 1

The black height is >= actual height/2. 

Any Red-black tree can be converted to a 2-4 tree (Take a black node and its red children and combine them into one node. Each node so formed has atleast one and almost 3 keys. Since black-height of all external nodes are same, all leaves are at the same height (If I start from the leaf and go up to the tree, I encounter the same number of black nodes no matter which leaf i start from, so all leaves of the resilient 


Faster than AVL trees: almost one rotation is needed for rebalancing after inserting/deletion. If the pathology (double red in the case of insertion, black height change in the case of deletion) propagates, we only need to recolour i.e. change 1 bits, and this can be done very fastly compared to rotations (which require 5-6 pointer changes) Even though both AVL and RB take O(logn) worst case for insertion deletion, the constants behind that (Ologn) are much higher in the case of AVL trees. 

Resources: Insertion: https://www.youtube.com/watch?v=6QOKk_pcv3U&t=1336s

 */



public class RedBlackTree<Key extends Comparable<Key>, Value>{


	private TreeNode root;
	private TreeNode NIL; // By setting up the sentinal node NIL to represent an external leaf, we ensure that every node in the RB tree has a child. We use object NIL so that we don't have to have separate node for each external node: such an approach would waste space.  The fields of NIL are immaterial. 
	//Question: Could we simply use null? If we use null, then we won't have a  treenode as a child. From StackOverflow: Formally, null is a singleton member of the null type, which is defined to be the subtype of every other Java type.  null is a reference type and its value is the only reference value which doesn't refer to any object. Therefore there is no representation of null in memory. The binary value of a reference-typed variable whose value is null is simply zero (all zero bits). Even though this is not explicitly specified, it follows from the general initialization semantics of objects and any other value would cause major problems to an implementation.
	private static final boolean RED = true;
	private static final boolean BLACK = false; // so that we can pass RED, BLACK as parameters instead of true/false;


	public RedBlackTree(){
		this.NIL = new TreeNode(); //  The fields of NIL are immaterial.
	}

	// How can i modify the rotate functions to take the tree as input so other classes can use it?
	// Can I implement R-B trees as extension of regular trees?
	private class TreeNode{

		Key key; // Should these be made private?
		Value value;
		TreeNode leftChild, rightChild, parent;
		boolean colour;

		//Constructor for nodes whose fields are immaterial or added later, e.g. NIL
		public TreeNode(){
			this.colour = BLACK;
		}

		public TreeNode(Key key, Value value, boolean colour){
			this.key = key;
			this.value = value;
			this.colour = colour;
			this.leftChild = NIL;
			this.rightChild = NIL;
			// this.parent = NIL;
		}

	}


	private boolean isRed(TreeNode node){
		if (node == null){
			throw new Error("called isRed with null");
		} else {
			return node.colour == RED;
		}
	}

	private boolean isEmpty(){
		return this.root == null;
	}

	private void setColour(TreeNode node, boolean colour){
		node.colour = colour;
	}

	private void rotateLeft(TreeNode leftNode){

		TreeNode rightNode = leftNode.rightChild;

		if (rightNode == NIL){
			System.out.println("No rightChild. Cannot rotate");
			return;
		}

		leftNode.rightChild = rightNode.leftChild;
		rightNode.leftChild.parent = leftNode; // If rightNode has no leftChild, this will set NIL's parent.
		rightNode.leftChild = leftNode;
		// Set children for leftNode's parent.
		if (leftNode.parent == null){
			this.root = rightNode;
		} else if (leftNode.parent.leftChild == leftNode){
			leftNode.parent.leftChild = rightNode;
		} else {
			leftNode.parent.rightChild = rightNode;
		}
		//Change rightNode's parent
		rightNode.parent = leftNode.parent;
		// Change leftNode's parent.
		leftNode.parent = rightNode;

	}

	private void rotateRight(TreeNode rightNode){

		TreeNode leftNode = rightNode.leftChild;
		if(leftNode == NIL){
			System.out.println("No left child. Cannot rotate");
			return;
		}
		// Swing child node (change three pointers: one in child, two in parents)
		rightNode.leftChild = leftNode.rightChild; //Rightnode takes on leftNode's child.
		leftNode.rightChild.parent = rightNode; //The child adopts the new parent
		leftNode.rightChild = rightNode;//LeftNode, going up, now takes on rightnode as child. 


		// Change parent pointer in leftNode and rightNode
		leftNode.parent = rightNode.parent;
		rightNode.parent = leftNode;
		// Change parent's pointers. 
		// 
		if (rightNode.parent == null){
			this.root = leftNode;
		} else if (rightNode.parent.leftChild == rightNode){
			rightNode.parent.leftChild =leftNode;
		} else {
			rightNode.parent.rightChild = leftNode;
		}


	}


	private TreeNode getRecursive(TreeNode rootNode, Key key){

	if (rootNode == NIL || key.compareTo(rootNode.key)==0){
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

 private TreeNode predecessorRecursive(TreeNode rootNode, Key key){

 	if(rootNode == NIL){
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


/*************************************************************************
     								 Insertion
 ***************************************************************************/


/**
 * We colour the new node red, so that the black height is not changed. The only case we need to fix is when this introduces a double red problem: when the parent of the inserted node is also red. (Unlike the case of deletion, where the fix was required when black-height was changed).
 *
 * Two cases: 
 * when the sibling of parent is black: one rotation is needed to fix.
 * when the sibling of parent is red: just recolouring fixes the local problem, and propogates the problem up. Worst case Terminating case: root is made red. in this case we colour the root black, which increases the black-height of the R-B tree uniformly. 
 *
 * So during each insertion, we do atmost one rotation to fix the double red problem (if one is introduced by insertion).  
 * 
 *
 * Insertion pseudocode:
 * if
 * 
 * @param key [description]
 *
 *
 *
 *
 *
 * 
 */
	public void insert(Key key, Value value){

		if(key == null) throw new IllegalArgumentException("called insert with null key");
		if(value == null){
			// delete(key);
			return;
		}

		TreeNode newNode = new TreeNode(key, value, RED);
		insertIterative(this.root, newNode); 
	}
	// Returns the root with new node inserted. If no root is passed, then the newNode becomes the root of the original tree. 
	private void insertIterative(TreeNode rootNode, TreeNode newNode){ 
		TreeNode parent = null;
		if (rootNode == null){
			this.root = newNode;
			newNode.parent = null;
			return;
			// System.out.println("Inserting key " + newNode.key + " as root");
		} else {
			TreeNode currentNode = rootNode;
			int cmp = newNode.key.compareTo(currentNode.key);

			//Loop invariant: after each iteration of the while loop, if cmp < 0, then we are supposed to probe the left subtree of current Node, else if > 0 right subtree. If we reach NIL, then cmp  tells you if NIL is parent's leftChild or rightChild.  
			while(currentNode != NIL){
				if (cmp == 0){
					System.out.println("Key already present");
					currentNode.value = newNode.value;
					return;
				}
				parent = currentNode;

				if (cmp < 0){
					currentNode = currentNode.leftChild;
				} else {
					currentNode = currentNode.rightChild;
				  }
				if (currentNode != NIL){
					cmp = newNode.key.compareTo(currentNode.key); 
				}  
				
			}
			// At this point, currentNode = NIL, cmp = comparison with parent.
			newNode.parent = parent;
			if (cmp < 0){
				parent.leftChild = newNode;
		  	} else {
		  		parent.rightChild = newNode;
		     }

		insertFix(newNode);
	}
}
	

	private void insertFix(TreeNode newNode){

		TreeNode parent = newNode.parent;
		TreeNode gran;
		TreeNode sibling; // parent's sibling
		boolean isParentLeftChild;
		boolean isChildLeftChild;
		while(parent != null && parent.colour == RED){
			// Set grandparent and siblings
			gran = parent.parent; // cannot be null as parent cannot be root, because root is always black. 
			if(gran.leftChild == parent){
				isParentLeftChild = true;
				sibling = gran.rightChild; // could be null
			} else {
				isParentLeftChild = false;
				sibling = gran.leftChild;
			}

			// Case when sibling is null or black: atmost two rotations.
			if(sibling.colour == BLACK){ // NIL has the colour black

				if (newNode == parent.leftChild){
					isChildLeftChild = true;
				} else {
					isChildLeftChild = false;
				}

				if (isChildLeftChild && isParentLeftChild){

					rotateRight(gran);
					setColour(gran, RED);
					setColour(parent, BLACK);

				}

				if(isChildLeftChild && !isParentLeftChild){

					rotateRight(parent);
					rotateLeft(gran);
					setColour(gran, RED);
					setColour(newNode, BLACK);

				}

				if(!isChildLeftChild && isParentLeftChild){
					rotateLeft(parent);
					rotateRight(gran);
					setColour(newNode, BLACK);
					setColour(gran, RED);

				}

				if(!isChildLeftChild && !isParentLeftChild){

					rotateLeft(gran);
					setColour(gran, RED);
					setColour(parent, BLACK);

				}

				break; // If sibling is black, the above rotations+ recolouring is sufficient to solve the problem

			} else {

				// Case when sibling is red
				
				setColour(gran, RED);
				setColour(parent, BLACK);
				setColour(sibling, BLACK);
				newNode = gran;
				parent = newNode.parent;
				
			}

		}

		setColour(this.root, BLACK);

	}

/****************************************************************************
     								 Deletion
******************************************************************************/

 public void delete(Key key){

 	if (key == null) throw new IllegalArgumentException("called delete() with null");

 	TreeNode nodeToDelete = getRecursive(this.root, key);
 	if (nodeToDelete == null){
 		System.out.println("Key to delete is not present");
 		return;
 	} else {
 		deleteIterative(nodeToDelete);
 	}

 }


 /**
  * When we call delete on a BST, we always delete the parent of an external node. If the node is the parent of two external nodes, it is a leaf. If it the parent of an internal node and an external node, then the internal node has to be red and a leaf! 
  * 
  * @param  rootNode [description]
  * @return          [description]
  */
 private TreeNode deleteIterative(TreeNode nodeToDelete){

 	if (nodeToDelete.leftChild != NIL && nodeToDelete.rightChild != NIL){
 		 TreeNode predecessor = predecessor(nodeToDelete)
 		 //assert successor != null;
 		 nodeToDelete.key = predecessor.key;
 		 nodeToDelete.value = predecessor.value;
 		 nodeToDelete = predecessor;
 	}
 	oneChildToParent(nodeToDelete);
 }

 private void oneChildToParent(TreeNode nodeToDelete){

 	if(nodeToDelete == null) throw new IllegalArgumentException("called oneChildToParent() with null");

 	if (nodeToDelete.leftChild != NIL && nodeToDelete.rightChild != NIL){
			throw new Error("called oneChildToParent on a node with two children");
		}

 	TreeNode parent = nodeToDelete.parent;
 	// Defind and assign nodeToMove. Could be null
 	TreeNode nodeToMove;
 	if (nodeToDelete.leftChild == NIL){
			nodeToMove = nodeToDelete.rightChild;
		} else {
			nodeToMove = nodeToDelete.leftChild;
		 }

    // Change child pointer if the nodeToDelete is not a leaf
	if (nodeToMove != NIL){
		nodeToMove.parent = parent;
	}

	if (parent == null){
		this.root = nodeToMove;
		System.out.println("Called oneChildToParent on root. New root assigned");
		return;
	} else {
		if (parent.leftChild == nodeToDelete){
			parent.leftChild = nodeToMove;
		} else {
			parent.rightChild = nodeToMove;
		}

	}

 }



private void deleteFix(TreeNode problemNode){

	



}




// private TreeNode getIterative(TreeNode rootNode, Key key){

// 	if (rootNode == null){
// 		return null;
// 	} 
// 	TreeNode currentNode = rootNode;
// 	int cmp = key.compareTo(rootNode.key);

// 	while(cmp != 0 && currentNode !=null){
// 		if(cmp < 0){
// 			currentNode = currentNode.leftChild;
// 		} else {
// 			currentNode = currentNode.rightChild;
// 		}

// 		if(currentNode != null){
// 			cmp = key.compareTo(currentNode.key)
// 		}

// 	}
// 	return currentNode;
// }


/**
 * copied from BST. 
 * @param  node [description]
 * @return      [description]
 */
 // private TreeNode predecessor(TreeNode node){

 // 	if(node == null) throw new IllegalArgumentException("called successor with null node")

 // 	if(node.leftChild != NIL){
 // 		return max(node.leftChild);
 // 	} else {
 // 		TreeNode currentNode = node;
 // 		TreeNode parent = currentNode.parent;
 // 		while(parent != null && currentNode == parent.leftChild){
 // 			currentNode = parent;
 // 			currentNode = parent.parent;
 // 		}
 // 		return parent

 // 	}

 // }
/**
 * Difference from BST: NIL comparison is used instead of null for base case.
 * https://www.quora.com/How-do-you-recursively-find-the-predecessor-and-successor-in-a-binary-search-tree
 * @param  rootNode [description]
 * @param  key      [description]
 * @return          [description]
 */

/**
 * Only difference from BST is that NIL comparisons are used instead of null for base case. Also, this is a recursive procedure.
 * @param  rootNode [description]
 * @return          [description]
 */
 // private TreeNode max(TreeNode rootNode){
 // 	if (rootNode == null) throw new IllegalArgumentException ("called max on null");
 // 	if (rootNode.rightChild == NIL){
 // 		return rootNode;
 // 	} else return max(rootNode.rightChild);
 // }

/**
 * [main description]
 * @param args [description]
 */

	public static void main(String[] args){

		// RedBlackTree test = new RedBlackTree();
		// test.insert(7, "new");
		// test.insert(3);
		// test.insert(10);

	}



}