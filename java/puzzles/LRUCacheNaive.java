package puzzles;

/*

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
Follow up:
Could you do both operations in O(1) time complexity?

*/

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

public class LRUCacheNaivee p{
	//hold elements uniquely in Last-In-First-Out order.
	List<Node> elements;
	int capacity;
	int time;


	// A bounded capacity can be specified at construction, so there is an upper limit to the number of items contained, with the least recently added items dropped on overflow.
	public LRUCache(int capacity){
		elements = new ArrayList<>(capacity);
		time = 0;
		this.capacity = capacity;
	}

	//put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
	//The most recently added item is first, the least recently added item is last. Items in the list are unique, so duplicate insertions are moved rather than added. Null insertions (empty strings) are not allowed.
	

	// Time complexity: O(n) for search and replace, O(n) for evict, O(1) FOR ADD.
	public void put (int key, int value){
		
		ListIterator<Node> it = elements.listIterator();
		Node current;
		while(it.hasNext()){
			current = it.next();
			if(current.key == key){
				current.value = value;
				time++;
				current.lastUsedTime = time;
				return;
			}
			
		}

		if (elements.size() + 1 > capacity){
			evict();
		}
		time++;
		elements.add(new Node(key, value, time));
	}

	// Time complexity: O(n) for LRU and O(n) for remove
	private void evict(){
		Node n = getLRU();
		elements.remove(n);
		System.out.println("Evicted: " + n.key); 
	}

	// Time complexity: O(n)
	private Node getLRU(){
		Node earliestUsed = elements.get(0);
		int minTime = earliestUsed.lastUsedTime;
		for (Node n: elements){
			if (n.lastUsedTime < minTime){
				minTime = n.lastUsedTime;
				earliestUsed = n;
			}
		}
		return earliestUsed;
	}

	//get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
	public int get(int key){
		ListIterator<Node> it = elements.listIterator();
		int answer = -1;
		Node current = null;
		while(it.hasNext()){
			current = it.next();
			if(current.key == key){
				time++;
				current.lastUsedTime = time;
				answer = current.value;
			}		
		}
		return answer;
	}


	static class Node {
		int key;
		int value;
		int lastUsedTime;

		public Node(int key, int value, int time){
			this.key = key;
			this.value = value;
			this.lastUsedTime = time;
		}
	}







	public static void main(String[] args){

		LRUCache cache = new LRUCache( 2 /* capacity */ );
		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1)); // returns 1
		cache.put(3, 3); // evicts key 2
		System.out.println(cache.get(2)); // returns -1 (not found)
		 cache.put(4, 4); // evicts key 1
		System.out.println(cache.get(1));  // returns -1 (not found)
		System.out.println(cache.get(3));         // returns 3
		System.out.println(cache.get(4));        // returns 4

	}


}