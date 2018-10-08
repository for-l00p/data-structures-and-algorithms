

import java.util.ArrayList;


public class HashTableOpen<K,V> {
	
	public ArrayList<HashNode<K,V>> bucketArray; 
	private int size;
	private int capacity;

	public HashTableOpen(int capacity){
		this.bucketArray = new ArrayList<HashNode<K,V>>();
		this.size = 0;
		this.capacity = capacity;
		for (int i = 0; i < capacity; i++){
			 bucketArray.add(null); 
		} 
           
	}


	private int getIndex(K key){
		int index = (key.hashCode()) & (capacity-1);
		System.out.println("Index for " + key + " is " + index);
		return index;
	}

	private void resize(){

		ArrayList<HashNode<K,V>> temp = bucketArray;
		capacity = 2*capacity;
		bucketArray = new ArrayList<HashNode<K,V>>(capacity);
		size = 0;

		for (int i = 0; i < capacity; i++){
            bucketArray.add(null); 
		}

		for (int i = 0; i < capacity/2; i++){
			//System.out.println("Copying element: " + temp.get(i).key);
			HashNode<K,V> currentNode = temp.get(i);
			if(currentNode != null){
				add(currentNode.key, currentNode.value);
			}
		}

		


	}


	public void add(K key, V value){

		double loadFactor = 1.0* size/capacity;

		if (loadFactor > 0.7){
			System.out.println("LOAD FACTOR ALERT: " + loadFactor);
			resize();
		}
		int index = getIndex(key);
		if (size == capacity){
			//throw new Exception("Array is full");
			System.out.println("Array is full");
			return;
		}

		HashNode<K,V> currentNode = bucketArray.get(index);

		while(currentNode != null && currentNode.isDeleted == false){
			
			if(currentNode.key.equals(key)){
				System.out.println("Key already present. Updating value");
				currentNode.value = value;
				return;
			}else{
				index = (index+1) % (capacity);
				System.out.println("New index: " + index);
				currentNode = bucketArray.get(index);
			}
		}

		HashNode<K,V> newNode = new HashNode<>(key, value);
		System.out.println("Inserting " + key + " in index: " + index);
		bucketArray.set(index, newNode);
		size++;
	}


	public V get(K key){

		int index = getIndex(key);
		HashNode<K,V> currentNode = bucketArray.get(index);
		while(currentNode != null){
			if (currentNode.key.equals(key) && currentNode.isDeleted == false){
				return currentNode.value;
			} else {
				index = (index + 1)% size;
				currentNode = bucketArray.get(index);
				continue;
			}


		// throw new Exception("Key not found");

		}
		System.out.println("Key not found");
		return null;	

	}

	public V delete(K key){

		int index = getIndex(key);
		HashNode<K,V> currentNode = bucketArray.get(index);

		while(currentNode != null){
			if (currentNode.key.equals(key)){
				currentNode.isDeleted = true;
				System.out.println("Key Deleted: " + key + " Index: " + index );
				size--;
				return currentNode.value;
			} else {
				index = (index + 1)% capacity;
				currentNode = bucketArray.get(index);
				continue;
			}	

		}

		System.out.println("Not found: Unable to delete");
			return null;
			// throw new Exception("Key absent");
		


	}



	private class HashNode<K,V>{

		K key;
		V value;
		boolean isDeleted;

		public HashNode(K key, V value){
			this.key = key;
			this.value = value;
			this.isDeleted = false;
		}



	}

	public static void main(String args[]){
		HashTableOpen<String, Integer> test = new HashTableOpen<>(10);
		test.add("key", 1);
		test.add("P", 2);
		test.add("Pi", 3);
		test.add("Piy", 4);
		test.add("Piyu", 9);
		//test.add("Helena", 11);
		test.add("Amia", 5);
		test.add("Emma", 13);
		test.add("Cate", 444);
		test.add("Stephen", 432);
		test.add("Anderson", 412);
		test.add("Ander", 4112);
		test.add("Courtney", 411212);
		test.add("Jodie", 123);
		test.add("Rooney", 412);
		test.add("Zuckerberg", 4112);
		//test.add("Thiel", 41212);
		//System.out.println(test.get("Jodie"));
		test.delete("Stephen");
		System.out.println(test.get("Stephen"));
		System.out.println(test.get("Jodie"));
		test.delete("Jodie");
		test.add("Jodie", 122);
		test.delete("Stephen");

		



	}


}