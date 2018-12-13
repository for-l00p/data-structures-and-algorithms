

import java.util.ArrayList; 


public class HashTable<K,V>{

	//private HashNode[] _array;
	private ArrayList<HashNode<K,V>> _bucketArray;
	private int _size;
	private int _capacity;


	//Constructor
	public HashTable(){
		_bucketArray = new ArrayList<>();
		_size = 0;
		_capacity = 10;

		for (int i =0; i < _capacity; i++){
			_bucketArray.add(null);
		}

	}

	private int getIndex(K key){
		int hashCode = key.hashCode();
		int index = hashCode % _capacity;
		System.out.println("index for " + key + " is " + index);
		return index;
	}

	public V get(K key){

		int index = getIndex(key);
		HashNode<K,V> head = _bucketArray.get(index);

		while(head != null){

			if (head.key.equals(key) ){
				return head.value;
			}

			head = head.next;
		}

		//Should throw exception
		return null;

	}

	public boolean isEmpty(){
		return _size == 0;
	}

	public int size(){
		return _size;
	}

	public void add(K key, V value){

		int index = getIndex(key);
		HashNode<K,V> head = _bucketArray.get(index);

		HashNode<K,V> temp = head;
		while (head != null){
			System.out.println("head is now: " + "key: " + head.key+ " value: " +  head.value);
			if (head.key.equals(key)){
				head.value = value;
				return;
			}

			head = head.next;

		}

		_size++;
		HashNode<K,V> newNode = new HashNode<K,V>(key, value);
		newNode.next = temp;
		_bucketArray.set(index, newNode);

		double loadFactor = 1.0 * (_size/_capacity);

		if (loadFactor >= 0.7){

			ArrayList<HashNode<K,V>> tempArray = _bucketArray ;
			_bucketArray = new ArrayList<>();
			_capacity = 2*_capacity;
			_size = 0;

			for (int i = 0; i < _capacity; i++ ){
				_bucketArray.add(null);
			}

			for (HashNode<K,V> headNode: tempArray){
				while (headNode != null){
					add(headNode.key,headNode.value);
					headNode = head.next;
				}
			}
		}





	}

	public V delete(K key){

		int index = getIndex(key);
		HashNode<K,V> head = _bucketArray.get(index);
		HashNode <K,V> previous = null;

		while(head != null){

			if(head.key.equals(key)){
				if (previous == null){
						_bucketArray.set(index, head.next);
				} else {
					previous.next = head.next;
				}

				_size--;

				return head.value;	
			}

			previous = head;
			head = head.next;
		}

		
		return null;
		

	}


	class HashNode<K,V>{

		K key;
		V value;
		HashNode<K,V> next;

		public HashNode(K key, V value){
			this.key = key;
			this.value = value;
		}


	}

	public static void main(String args[]){

		HashTable<Integer,String> testTable = new HashTable<>();
		testTable.add(3, "new");
		testTable.add(1, "this"); 
        testTable.add(1000, "coder"); 
        testTable.add(4, "this" ); 
        testTable.add(2, "hi" ); 
        testTable.add(1002, "hasj" ); 
        testTable.add(1012192, "nea" ); 
        System.out.println(testTable.size()); 
        System.out.println(testTable.delete(4)); 
        System.out.println(testTable.get(1002)); 
        System.out.println(testTable.size()); 
        System.out.println(testTable.isEmpty()); 


	}
}