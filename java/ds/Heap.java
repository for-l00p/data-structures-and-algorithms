/**
 *Resources: 
 *  https://algs4.cs.princeton.edu/24pq/
 * https://cs.stackexchange.com/questions/27860/whats-the-difference-between-a-binary-search-tree-and-a-binary-heap
 *
 *
 * 
 * Heaps are an implementation of ADT called Priority Queues. Priority Queues need to support the following operations:
 * - insert
 * - delete-min
 * - min
 *
 * Priority Queues require a total order on the objects in the data type, implemented by a Comparator object called Comparator external to the objects being stored. 
 * 
 * Applications:
 * - job scheduling in Unix using shared computer resources (the job requiring the least amount of time)
 * 

 * Implemented with unsorted sequence: insert takes O(1), delete-min and min take O(n)
 * Implemented with sorted sequence: delete-min and min take O(1) and insert takes O(n)
 * Implemented with binary search tree: all operations take O(logn)
 * Implemented with Binary Heap: insert O(logn), min in O(1) and delete-min in O(logn). 
 *
 * If the setting is such that there are very few insertions but a lot of findMin operations, it might make sense to use a sorted sequence.
 * 
 * 
 * 
 * Binary Heaps: Binary Trees which store priorities of elements with two additional properties:
 * We add two properties:
 *
 * 1. All levels except the last level are full. Last level is left-filled. 
 * 2. Priority of node is atleast as large as its parent. 
 *
 *
 * Motivation: 
 * 
 * Can we do better than BST (where all operations take O(logn)? The Binary Search Property is introduced to binary trees optimize search. This property introduces more costs on other operations, e.g. if we were free of this constraint, we could implement insert in O(1) instead of O(logn)
 *
 * We implement insert in O(1) by left-filling progressively. The corresponding property is:
 * 
 * 1. All levels except the last level are full. Last level is left-filled. 
 * 
 * Implemented with Binary tree where only last level is being filled: insert O(1). deletemin and min(): O(n).
 *
 * But now deletemin and min() are worse than in BST. Can we introduce another constraint which improves min() and deletemin() without worsening insert?
 *
 * We store min() in root (recursively).
 * 
 * This gives the following property:
 * 2. Priority of node is atleast as large as its parent. 
 * 
 * So min takes O(1). deletemin takes O(logn) because deleting changes the heap property.   What happens to insert? Simple algorithm of inserting at bottom and progressively replacing with parent gives insert in O(logn). 
 *
 * So now we have: insert O(logn), min is O(1) and delete-min in O(logn). 
 * 
 * Height of a Binary Heap with nodes n and height h:
 *
 * Such a heap has more nodes than a complete binary tree of height h-1 and less nodes than those of height h.
 * So 2^h-1 < n < 2^(h+1) - 1  
 *
 * ------
 * Insertion: Can bubble up the new node. (Or we can directly find the position, insert there, and push all descendents down -  argue correctness)
 * Can implement with arrays. 
 * Heapify: Assume both subtrees are a heap, and node i violates heap property. successively replace with the lesser sibling. Traces a path down - argue correctneess. 
 *
 * Building a heap: we could insert successively, resulting in log1 + log2 + ...logn running time = O(n!) = O(nlogn)
 *  HeapSort: creat a heap, successively deleteMin and put it at the very end (to sort in place). 
 *
 * A comparator object is external to the classes of objects which it allows you to compare: since priorities can differ over time in the same application and same data structure. So in priority queue applications, it is not sufficient to use Comparable Values: one must provide a comparator object. 
 *
 *
 * 
 */


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class Heap<Value extends Comparable<Value>> implements MinPriorityQueue<Value>, Iterable<Value>{

// Advantage of putting the nodes of a heap in an array: Save on memory overhead, get more speed from data locality. an explicit tree would use up more memory than an array.The pointers of the tree use a lot of memory, compared to the array-based heap, where you barely need any additional space but the one taken by the values themselves. And manipulating these pointers takes time too.  array has better cache locality and thus much better runtime in practice, Allocating and deallocating nodes might take some time and space also. , 
// 
// For a sparse tree most of array positions will be wasted. it will still make sense to use array based implementation when you know size of tree in advance and know that it will be a dense tree.

// Starting the heap at index 1 will probably make faster calculation of parent, left and right child indexes. On most computers, the LEFT procedure can compute 2*i in one instruction by simply shifting the binary representation of i left by one bit position. Similarly, the RIGHT procedure can quickly compute 2*i+1 by shifting the binary representation of i left by one bit position and then adding in a 1 as the low-order bit. The PARENT procedure can compute i/2 by shifting i right one bit position. 

	private ArrayList<Value> values;
	private int size;
	private Comparator<Value> comparator;


  /*************************************************************************
     Constructors
   ***************************************************************************/


	/**
	 * Initializes an empty priority queue
	 * @return [description]
	 */
	public Heap(){
		this.values = new ArrayList<>();
		this.values.add(null);
		this.size = 0;
	}


	public Heap(Comparator<Value> comparator){
		this();
		this.comparator = comparator;
	}

	/**
	 *To prove correctness: induction on i. Assume all heaps rooted at m > i are heaps. 
	 *
	 * Running time: O(n): For n/2 nodes of height 1, only 1 swap need. For n/2^i nodes, heapitfy requires at most i swaps. Total swaps: O(n). Most of the heapify calls are done on heaps which are too small. 
	 * @param  inputArray [description]
	 * @return            [description]
	 */

	 public Heap(Value[] inputArray){

	 	// for(Value value: values){
	 	// 	insert(value);
	 	// }
	 	this();
	 	for(Value value: inputArray){
	 		this.values.add(value);
	 		size++;
	 	}

	 	int limit = (int) Math.floor(size/2);

	 	for (int i = limit; i > 0; i--){
	 		heapify(i);
	 	}
	 	System.out.println(this.values);

	}



  /*************************************************************************
      Container Methods
   ***************************************************************************/



	public boolean isEmpty(){
		return size == 0;
	}

	public int size(){
		return size;
	}




  /*************************************************************************
     Priority Queue Methods
   ***************************************************************************/


	public Value min(){

		if (isEmpty()) return null;
		return values.get(1);

	}

	// public Value deleteMin(){

	// }

	public void insert(Value value){


		values.add(value);
		size++;
		bubbleUp(size);
		assert isMinHeap();
	}

	public Value extractMin(){


		if (isEmpty()) return null;
		Value min = values.get(1);
		values.set(1, values.get(size));
		values.remove(size);
		size--;
		if (!isEmpty()){
			heapify(1);
		}
		
		return min;
	}


  public void delete(int index){
    validateIndex(index);
    values.set(index, values.get(size));
    values.remove(size);
    size--;
    if (!isEmpty()){
      heapify(index);
      bubbleUp(index);
    }




  }




  /*************************************************************************
      Helper methods for Array-based implementation of a Heap
   ***************************************************************************/

     private void validateIndex(int index){
        if(index <=0 || index > size) throw new IllegalArgumentException("index input out of bounds: " + index);
     }

     private void heapify(int index){

        validateIndex(index);
      	int childToSwap = index;
      	if (!hasLeftChild(index) && !hasRightChild(index)){
      		return;
      	}

      	if (hasLeftChild(index) && !hasRightChild(index)){
      		childToSwap = leftChild(index);
      	}
      	
      	if (hasLeftChild(index) && hasRightChild(index)){
      		childToSwap = greater(leftChild(index), rightChild(index)) ? rightChild(index): leftChild(index);
      	}

      	if(greater(index, childToSwap)){
      		swap(index, childToSwap);
      		heapify(childToSwap);
      	}

     }




    private void bubbleUp(int index){
      	validateIndex(index);
      	 //What if parent is null? What if index is 1?
      	while(index > 1 && greater(parent(index), index)){
      		swap(index, parent(index));
      		index = parent(index);
      	}

    }

   

    /**
	 * Restore the min-heap property.  When this method is called, the min-heap property holds everywhere, except possibly at node index and its children. When this method returns, the min-heap property holds everywhere.
	 * @param  index [description]
	 * @return       [description]
	 */

    private boolean greater(int firstIndex, int secondIndex){
      	validateIndex(firstIndex);
      	validateIndex(secondIndex);
      	boolean cmp = false;
      	if (comparator == null){
      		cmp = values.get(firstIndex).compareTo(values.get(secondIndex)) > 0;
      	} else {
      		cmp = comparator.compare(values.get(firstIndex), values.get(secondIndex)) > 0; 
      	}
       return cmp;
    }


    private void swap(int firstIndex, int secondIndex){
        validateIndex(firstIndex);
        validateIndex(secondIndex);
      	Value temp = values.get(firstIndex);
      	values.set(firstIndex, values.get(secondIndex));
      	values.set(secondIndex, temp);
      	System.out.println("Swapping " + firstIndex + " and " + secondIndex);
    }





    private int parent(int index){
      	validateIndex(index);
      	if (index == 1) return 0;
      	int parent = (int) Math.floor(index/2);// Casting to an int implicitly drops any decimal. No need to call Math.floor() (assuming positive numbers)
      	return parent;
    }

    private int leftChild(int index){
      	validateIndex(index);
      	return index*2;
    }

    private int rightChild(int index){
      	validateIndex(index);
      	return (index*2) + 1;
    }

    private boolean hasLeftChild(int index){
        validateIndex(index);
      	return index <= Math.floor(size/2);
    }

    private boolean hasRightChild(int index){
        validateIndex(index);
      	return index < Math.floor(size/2);
    }


    public Iterator<Value> iterator(){
    	return new HeapIterator();
    }


    private class HeapIterator implements Iterator<Value> {

    	private ArrayList<Value> iteratorValues;
    	private int currentIndex;

    	public HeapIterator(){

    		this.iteratorValues = new ArrayList<>();
    		iteratorValues.add(null);
    		for(int i = 0; i< size; i++){
    			iteratorValues.add(i+1, values.get(i+1));
    		}
    		currentIndex  = 0;
    	}

    	public boolean hasNext(){
    		return (currentIndex < size);

    	}

    	public Value next(){

    		currentIndex++;
    		return iteratorValues.get(currentIndex);

    	}

    }


   /*************************************************************************
      Check integrity of Heap data structure.
   ***************************************************************************/


    public boolean isMinHeap(){

    	return isMinHeap(1);

    }

    /**
     * Only checks the min property. 
     * @param  index [description]
     * @return       [description]
     */
    
    private boolean isMinHeap(int index){

    	// hasLeftChild(index) is equivalent to 2*index <= size and hasRightChild(index) is equivalent to 2*index + 1 <= size
    	 
    	//The case when a node has no children is handled by this by returning true for left and right subtree. 
    	if (index > size) return true; //

    	if (hasLeftChild(index) && greater(index, leftChild(index))){
    		return false;
    	}

    	if (hasRightChild(index) && greater(index, rightChild(index))){
    		return false;
    	}

    	return (isMinHeap(leftChild(index)) && isMinHeap(rightChild(index)));
    	
    }






    public static void main(String[] args){
    	Heap<String> pq = new Heap<String>();
    	pq.insert("cat");
    	pq.insert("dog");
    	pq.insert("bee");
    	System.out.println("Smallest is: " + pq.min());
    	System.out.println("Smallest again: " + pq.extractMin());
    	System.out.println("Next smallest is: " + pq.extractMin());
    	System.out.println("Is it empty? : " + pq.isEmpty());
    	pq.insert("eagle");
    	System.out.println("Next smallest is: " + pq.extractMin());
    	System.out.println("Next smallest is: " + pq.extractMin());
    	System.out.println("Is it empty? : " + pq.isEmpty());
    	System.out.println("Min of empty queue: " + pq.min());
    	System.out.println("Remove min of empty queue: " + pq.extractMin());
    	pq.insert("bear");
    	System.out.println("Smallest is: " + pq.min());
    	System.out.println("Smallest again: " + pq.extractMin());
    // pq.insert("cat");
    // pq.insert("dog");
    // pq.insert("sheep");
    // pq.insert("cow");
    // pq.insert("eagle");
    // pq.insert("bee");
    // pq.insert("lion");
    // pq.insert("tiger");
    // pq.insert("zebra");
    // pq.insert("ant");

    	System.out.println("Bigger example:");
    	String[] a = {"cat", "dog", "sheep", "cow", "eagle", "bee", "lion", "tiger", "zebra", "ant"};
    	pq = new Heap<String>(a);
    	// System.out.println("Smallest is: " + pq.extractMin());
    	// System.out.println("Next smallest is: " + pq.extractMin());
    	// System.out.println("Next smallest is: " + pq.extractMin());
    	// System.out.println("Next smallest is: " + pq.extractMin());
    	// System.out.println("Next smallest is: " + pq.extractMin());
    	// System.out.println("Next smallest is: " + pq.extractMin());
    	// System.out.println("Next smallest is: " + pq.extractMin());
    	// System.out.println("Next smallest is: " + pq.extractMin());
    	// System.out.println("Next smallest is: " + pq.extractMin());
    	// System.out.println("Next smallest is: " + pq.extractMin());
    	// System.out.println("Next smallest is: " + pq.extractMin());

    	Iterator<String> iterato = pq.iterator();
    	System.out.println(iterato.next());
    	System.out.println(iterato.next());
    	System.out.println(iterato.next());
    	System.out.println(iterato.next());
    	System.out.println(iterato.next());
    	System.out.println(iterato.next());
    	System.out.println(iterato.hasNext());
    	System.out.println(iterato.next());
    	System.out.println(iterato.next());
    	System.out.println(iterato.next());
    	System.out.println(iterato.next());
    	System.out.println(iterato.hasNext());
    	System.out.println(iterato.next());

    }





}