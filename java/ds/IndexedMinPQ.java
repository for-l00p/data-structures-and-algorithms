/**
 * 
 * In many applications, it makes sense to allow clients to refer to items that are already on the priority queue. One easy way to do so is to associate a unique integer index with each item.
 * 
 * In this indexed Priority Queue, we store a mapping from the priorities (given by heap[j]) in a heap to their assosciated objects. The objects are stored in a separate array (keys []). By referring to the index of the object array, and traversing the mapping, we can get a pointer to an object's position in a heap. The heapIndex of an object at index i is stored in qp[i]. 
 *
 * Index priority-queue implementation. Implement IndexMaxPQ.java by modifying MaxPQ.java as follows: Change pq[] to hold indices, add an array keys[] to hold the key values, and add an array qp[] that is the inverse of pq[] — qp[i] gives the position of i in pq[] (the index j such that pq[j] is i). Then modify the code to maintain these data structures. Use the convention that qp[i] is -1 if i is not on the queue, and include a method contains() that tests this condition. You need to modify the helper methods exch() and less() but not sink() or swim().
 */

import java.util.*;

// Are there indices of the the keys[] that are not represented in the heap? Yes. Can we check, given a key index, whether it is part of the heap or not? Yes:  contains method
//
// Keys are just Priorities

public class IndexedMinPQ<Key extends Comparable<Key>> implements Iterable<Key>{

	private int[] heap;
	private int[] heapIndices;
	ArrayList<Key> keys;
	private int size;
	private int maxSize;





	public IndexedMinPQ(int maxSize){

		this.heap = new int[maxSize + 1];
		this.heapIndices = new int[maxSize];
		this.keys = new ArrayList<Key>(maxSize);
		this.maxSize = maxSize;
		this.size = 0;

		for(int i = 0; i < maxSize; i++){
			keys.add(null);
		}

		for(int i = 0; i < maxSize; i++){
			heapIndices[i] = -1;
		}

	}


	public boolean isEmpty(){
		return (this.size == 0);
	}

	public int size(){
		return this.size;
	}


	public boolean contains(int keyIndex){
		validateKeyIndex(keyIndex);
		return (heapIndices[keyIndex] != -1);
	}



	public void insert(int keyIndex, Key key){
		validateKeyIndex(keyIndex);
		keys.set(keyIndex, key);
		size++;
		heapIndices[keyIndex] = size; 
		heap[size] = keyIndex;
		bubbleUp(size);
	}


	public void delete(Key key){
		int index = keys.indexOf(key);
		delete(index);
	}



	public void delete(int keyIndex){
		validateKeyIndex(keyIndex);
		keys.set(keyIndex, null);
		int heapIndex = heapIndices[keyIndex];
		swap(heapIndex, size);
		size--;
		heapify(heapIndex);
		bubbleUp(heapIndex);
	}



	public int extractMin(){

		if(isEmpty()) throw new NoSuchElementException("Priority Queue underflow");
		int minIndex = heap[1];
	 	swap(1, size);
	 	size--;
	 	if(!isEmpty()) {heapify(1);}
	 	assert heap[size+1] == minIndex;
	 	keys.set(minIndex, null);
	 	heapIndices[minIndex] = -1;
	 	heap[size+1] = -1; //optional
	 	return minIndex;

	}

	public Key extractMinKey(){

		if(isEmpty()) throw new NoSuchElementException("Priority Queue underflow");
		int minIndex = heap[1];
	 	swap(1, size);
	 	size--;
	 	if(!isEmpty()) {heapify(1);}	
	 	assert heap[size+1] == minIndex;
	 	Key result = keys.get(minIndex);
	 	keys.set(minIndex, null);
	 	heapIndices[minIndex] = -1;
	 	heap[size+1] = -1; //optional
	 	return result;

	}

	public int minIndex(){
		if(isEmpty()) throw new NoSuchElementException("Priority Queue underflow");
		return heap[1];		
	}

	public Key minKey(){
		if(isEmpty()) throw new NoSuchElementException("Priority Queue underflow");
		return keys.get(heap[1]);		
	}


	public void changeKey(int keyIndex, Key key){
		validateKeyIndex(keyIndex);
		keys.set(keyIndex, key);
		heapify(heapIndices[keyIndex]);
		bubbleUp(heapIndices[keyIndex]);		
	}


	public Key keyOf(int keyIndex){
		validateKeyIndex(keyIndex);
		return keys.get(keyIndex);
	}





	public void swap(int firstHeapIndex, int secondHeapIndex){
		validateHeapIndex(firstHeapIndex);
		validateHeapIndex(secondHeapIndex);
	
		int firstKeyIndex = heap[firstHeapIndex];
		int secondKeyIndex = heap[secondHeapIndex];

		heap[firstHeapIndex] =  secondKeyIndex;
		heap[secondHeapIndex] = firstKeyIndex;
		heapIndices[firstKeyIndex] = secondHeapIndex;
		heapIndices[secondKeyIndex] = firstHeapIndex;


	}




	public void heapify(int heapIndex){

		validateHeapIndex(heapIndex);
		//While heapIndex has alteast one child.
		
		while(2*heapIndex <= size){

			int swapIndex = 2*heapIndex;			 	
			if(swapIndex < size && greater(swapIndex, swapIndex + 1)){
				swapIndex++;
			}
			if (!greater(heapIndex, swapIndex)){
				break;
			}
			swap(heapIndex, swapIndex);
			heapIndex = swapIndex;
		}
	}



	public void bubbleUp(int heapIndex){
	
		validateHeapIndex(heapIndex);
		int k = heapIndex;
		while(k > 1 && greater(k/2,k)){
			swap(k/2,k);
			k = k/2;
		}
	}

	// For maxHeap, we only need modify greater so that it simulates lesser. 

	public boolean greater(int firstIndex, int secondIndex){
		validateHeapIndex(firstIndex);
		validateHeapIndex(secondIndex);
		
		int cmp = keys.get(heap[firstIndex]).compareTo(keys.get(heap[secondIndex]));
		return (cmp > 0);
	}



	public void validateKeyIndex(int index){
		if(index < 0 || index >= maxSize) throw new IllegalArgumentException("input index out of bounds");
	}
	

	public void validateHeapIndex(int index){
		if(index <=0 || index > size) throw new IllegalArgumentException("input index out of bounds");
	}

	public Iterator<Key> iterator(){
		return new HeapIterator();
	}


	private class HeapIterator implements Iterator<Key>{

		private IndexedMinPQ<Key> copy;

		public HeapIterator(){
			copy = new IndexedMinPQ<Key>(maxSize);
			int i = 1;
			while(i <= size){
				copy.insert(heap[i], keys.get(heap[i]));
				i++;
			}
		}
		

		public boolean hasNext(){
			return !copy.isEmpty();
		}


		public Key next(){
			//System.out.println(copy.size());
			if(!hasNext()) throw new NoSuchElementException();
			return copy.extractMinKey();
		}
	}





	public static void main(String[] args ){

		IndexedMinPQ<String> test = new IndexedMinPQ<String>(10);
		test.insert(0, "Chandigarh");
		test.insert(2, "Delhi");
		test.insert(3, "Mumbai");
		test.insert(4, "Singapore");
		test.insert(5, "Noida");
		test.insert(6, "London");
		test.insert(7, "Thailand");
		test.insert(9, "Gurgaon");

		
		for(String key: test){
			System.out.println(key);
		}


		System.out.println("Done. Now updating key");

		//test.delete(0);


		test.changeKey(5, "Indonesia");


		for(String key: test){
			System.out.println(key);
		}

		
		
	}










}