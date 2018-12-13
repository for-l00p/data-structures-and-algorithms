
package ds;
import java.util.HashMap;
import java.util.Map;


/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?


 * 	Data structure which holds an elemenet (e) with its last used times (t). Size is fixed and limited, everytime: everytime the size is full and an element is added, the element with the least t is evicted. 
 *
 * We we use a maxHeap with t as priority, we could support insert in O(logn) time and search in O(n) time. Ideally we want to insert and search in O(1) time. 
 *
 * For O(1) search, we can think of a hashtable (generalization of an array). Whenever the LRU element is removed, we need a pointer to the new LRU, so we need to either search for the next LRU element, or always have a pointer to it. If we have a pointer to it, everytime we remove the LRU element, we need to update the pointer for the next LRU element, and the problem re-emerges. 
 *
 * So we maintain the elements in a linkedList. 
 */


//Not thread safe because not final. 

class LRUCache<Key, Value>{

	private Map<Key, Node> nodeMap;  // For O(1) lookup. We could dispense with it, but that would make lookup O(n);
	private Node leastRecentlyUsed;
	private Node mostRecentlyUsed;
	private int CAPACITY; 


	class Node{
		private Key key;
		private Value value;
		private Node left;
		private Node right;

		public Node(Key key, Value value){
			this.key = key;
			this.value = value;
		}
	}


	public LRUCache(int size){
		nodeMap = new HashMap<Key, Node>();
		this.CAPACITY = size;
	}


	Value get(Key key){

		Node node = nodeMap.get(key);
		if (node != null){
			moveToMostRecent(node);
			Value value = node.value;
			return value;
		} else {
			return null;
		}

		//assert node.key == key;

	}

	void put(Key key, Value value){
		System.out.println("Updating: " + key);
		Node node = nodeMap.get(key);

		if(node != null){
			node.value = value;
			removeFromList(node);
		} else {
			if(nodeMap.size() == CAPACITY){
				System.out.println("Size exceeded. Removing: " + leastRecentlyUsed.key);
				assert leastRecentlyUsed != null;
				nodeMap.remove(leastRecentlyUsed.key);
				removeFromList(leastRecentlyUsed);
			}
			node = new Node(key, value);
			nodeMap.put(key,node);
		}
		
		moveToMostRecent(node);

		System.out.println("Most recently used now: " + mostRecentlyUsed.key);
		System.out.println("leastRecentlyUsed used : " + leastRecentlyUsed.key);
	}

	void removeFromList(Node node){

		if(node.left != null){
			node.left.right = node.right;
		} else {
			assert node == leastRecentlyUsed;
			leastRecentlyUsed = node.right;
		}

		if(node.right != null){
			node.right.left = node.left;	
		} else {
			assert mostRecentlyUsed == node;
			mostRecentlyUsed = node.left;
		}


	}

	void moveToMostRecent(Node node){
		node.left = mostRecentlyUsed;
		if(mostRecentlyUsed != null){
			mostRecentlyUsed.right = node;	
		} else {
			assert leastRecentlyUsed == null;
			leastRecentlyUsed = node;
			//System.out.println("leastRecentlyUsed used : " + leastRecentlyUsed.key);

		}
		mostRecentlyUsed = node;
	}

	
	public static void main(String[] args){

		LRUCache<Integer, Integer> test = new LRUCache<>(4);
		test.put(4,7);
		test.put(3,4);
		//System.out.println(test.get(3));
		test.put(1,2);
		test.put(2,8);
		test.put(1,3);
		test.put(4,7);
		//System.out.println(test.get(1));
		//System.out.println(test.get(4));
		test.put(5,3);

	
		
	}
	
}