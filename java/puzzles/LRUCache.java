package puzzles;


import java.util.*;

public class LRUCache {
	

	Map<Integer, Node> elements;
	Node leastRecentlyUsed;
	Node mostRecentlyUsed;

	private final int CAPACITY;

	public LRUCache(int capacity){
		this.CAPACITY = capacity;
		this.elements = new HashMap<>();
		this.leastRecentlyUsed = null;
		this.mostRecentlyUsed = null;
	}


	static class Node {
		int key;
		int value;
		Node next;
		Node previous;
		public Node(int key, int value){
			this.key = key;
			this.value = value;
			
		}
	}

	public void put(int key, int value){
		
		Node node = elements.get(key); 
		if (node != null){	
			removeFromList(node);
			moveToHead(node);
			node.value = value;
			return;
		} 

		node = new Node(key, value);

		//If size is exceeded
		
		if (elements.size() + 1 > CAPACITY){
			elements.remove(leastRecentlyUsed.key);
			removeFromList(leastRecentlyUsed);
		}

		elements.put(new Integer(node.key), node);
		moveToHead(node); 

	}

	public int get(int key){

		Node node = this.elements.get(key);
		if(node == null){
			return -1;
		}
		
		removeFromList(node);
		moveToHead(node);
		return node.value;
	}

	private void removeFromList(Node node){
		if(node.previous != null){
			node.previous.next = node.next;		
		} else {
			mostRecentlyUsed = node.next;
		}

		if(node.next != null){
			node.next.previous = node.previous;
		} else {
			leastRecentlyUsed = node.previous;
		}
	}

	private void moveToHead(Node node){

		if (mostRecentlyUsed != null){
			node.next = mostRecentlyUsed;
			mostRecentlyUsed.previous = node;
			node.previous = null;
			mostRecentlyUsed = node;
		} else {
			node.next = null;
			node.previous = null;
			mostRecentlyUsed = node;
			leastRecentlyUsed = node;	
		}

		
	}

/*	@Override
	public String toString(){
		Node current = mostRecentlyUsed;
		StringBuilder s = new StringBuilder();
		while(current != null){
			s.append(current.key);
			s.append(" -> ");
			current = current.next;
		}
		s.append('\n');

		current = leastRecentlyUsed;
		StringBuilder t = new StringBuilder();
		while(current != null){
			t.append(current.key);
			t.append(" -< ");
			current = current.previous;
		}
		s.append(t.reverse());

		return String.valueOf(s);
	}
*/



	public static void main(String[] args){

		LRUCache cache = new LRUCache( 1 /* capacity */ );
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