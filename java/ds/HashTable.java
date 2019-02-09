/**
 
 Dictionaries support the operation of search, insert and delete on the data. The data primitive is a key-value pair. All that is required of the key is that they can be compared.  

Dictionaries are basically an abstract model of a database. 

 Examples: 

 Phone number registry. A CallerID functionality by a phone company implements the get(PhoneNumber) method.
 Bank Accounts where account no. is the key.
 Student entry numbers
 primary key in a document store like MongoDb

Implementations:
Linked List
Arrays
Hash Tables
Trees: Binary Search Tree, R-B Tree, AVL Tree, B Trees

Java Library Classes:
Map Interfact
Dictionary Abstract Class
HashMap
HashTable
LinkedHashMap
TreeMap

The best representation/implementation depends on the problem statement. To decide which implementation to use, we need to see which operations out of search, insert and delete are used frequently. 

For example:
Unordered Sequence implementation such as a LinkedList or array: O(n) search, O(n) delete, O(1) insert. 
Application: When you have to maintain a log file: When you are making transactions in a database, one has to maintain log files. So if there are problems you can revert the transaction. Or in System administration, you track all the activities that are taking place. Here it is  rare that you would delete or search, but you have to just frequently insert.

Ordered sequence implementation (if keys can be ordered) with a direct access mechanism (such as an array):  O(logn) search, O(n + logn) insert, O(n) delete. We’ll have to shift all the elements once we insert or delete in the right position.  
Application: lookup table (frequent searches, rare insertions and deletions)


BST has O(Log n) search time

Direct access implementation such Array indexed by keys.  O(1) search, insert and delete. All operations take constant time! But terrible space properties. O(r), where r is the range.  

Could we map direct access implementation  to a smaller range? For example, could we map the larger range to one we can manage? Yes. We can use a function! (and call this a hashing function). The resultant smaller array is called a  Hash table! (compromise between fast search and less space.) 

O(1) expected  time for all operations O(n+m) space. 

A hash table is just a representation of a mapping or an onto function. Thus, Hash tables need to be able to handle collisions: when the hash function maps two different keys to the same bucket of the array. The two most widespread approaches to this problem are separate chaining and open addressing. In separate chaining, the array does not store the value itself but stores a pointer to another container, usually an association list, that stores all of the values matching the hash. On the other hand, in open addressing, if a hash collision is found, then the table seeks an empty spot in an array to store the value in a deterministic manner, usually by looking at the next immediate position in the array.

Simple uniform hashing yields an average list length = a = load factor = n/m. Search time: O(1 + a). Inserting is the same, because we have to search first. deletion is same when the list is doubly linked. 

The efficiency of this data structure depends critically on the hash function chosen. When all the keys are mapped to the same hash (bad hash function), the performance reduces to that of a linkedList. And picking a hash function is more like an art than a science: there are principles we can follow to pick a good hash function, but there is no optimal hash function. 
For example:Implement hashCode() and equals() for the following data type. Be careful since it is likely that many of the points will have small integers for x, y, and z.
public class Point2D {
    private final int x, y;
    ...
}
 one solution would to make the first 16 bits of the hash code be the xor of the first 16 bits of x and the last 16 bits of y, and make the last 16 bits of the hash code be the xor of the last 16 bits of x and the first 16 bits of y. Thus, if x and y are only 16 bits or less, the hashCode values will be different for different points.



S.No.	Seperate Chaining	Open Addressing
1.	Chaining is Simpler to implement.	Open Addressing requires more computation.
2.	In chaining, Hash table never fills up, we can always add more elements to chain.	In open addressing, table may become full.
3.	Chaining is Less sensitive to the hash function or load factors.	Open addressing requires extra care for to avoid clustering and load factor.
4.	Chaining is mostly used when it is unknown how many and how frequently keys may be inserted or deleted.	Open addressing is used when the frequency and number of keys is known.
5.	Cache performance of chaining is not good as keys are stored using linked list.	Open addressing provides better cache performance as everything is stored in the same table.
6.	Wastage of Space (Some Parts of hash table in chaining are never used).	In Open addressing, a slot can be used even if an input doesn’t map to it.
7.	Chaining uses extra space for links.	No links in Open addressing


Open addressing has a lower cache miss ratio than separate chaining when the table is mostly empty. However, as the table becomes filled with more elements, open addressing's performance degrades exponentially. Additionally, separate chaining uses less memory in most cases, unless the entries are very small (less than four times the size of a pointer


Important for use of HashMap and HashSet Java:  

- it is good practice to use only immutable types for keys in hash tables. This is because the get operation fails after a key is mutated. Mutation changes the hashCode, the get operation will look for in the bucket corresponding to the new hashCode, but the key would be in the bucket correponding to the old hashCode.

- Always override hashCode when you override equals.  if you don’t override hashCode at all, you’ll get the one from Object, which is based on the address of the object. If you have overridden equals, this will mean that you will have almost certainly violated the contract. 

- A simple and drastic way to ensure that the contract is met is for hashCode to always return some constant value, so every object’s hash code is the same. This satisfies the Object contract, but it would have a disastrous performance effect, since every key will be stored in the same slot, and every lookup will degenerate to a linear search along a long list. As long as you satisfy the requirement that equal objects have the same hash code value, then the particular hashing technique you use doesn’t make a difference to the correctness of your code. It may affect its performance, by creating unnecessary collisions between different objects, but even a poorly-performing hash function is better than one that breaks the contract.




References:

https://algs4.cs.princeton.edu/34hash/
https://courses.csail.mit.edu/6.006/fall11/lectures/lecture10.pdf
https://en.wikibooks.org/wiki/Data_Structures/Hash_Tables
https://courses.csail.mit.edu/6.006/fall11/lectures/lecture10.pdf
http://web.mit.edu/6.005/www/fa14/classes/09-af-ri-equality/

Interesting Stack Overflow answers:

https://stackoverflow.com/questions/49592995/i-dont-understand-what-is-0x7fffffff-mean-is-there-any-other-way-to-code-gethas
https://stackoverflow.com/questions/49709873/cache-performance-in-hash-tables-with-chaining-vs-open-addressing



**/


package ds;

import java.util.ArrayList; 


public final class HashTable<K,V>{

	private ArrayList<HashNode<K,V>> _bucketArray;
	private int _size;
	private int _capacity;


	//Constructor/Creater Method
	public HashTable(){
		_bucketArray = new ArrayList<>();
		_size = 0;
		_capacity = 10;

		for (int i =0; i < _capacity; i++){
			_bucketArray.add(null);
		}

	}

	// Observer Methods

	public boolean isEmpty(){
		return _size == 0;
	}

	public int size(){
		return _size;
	}

	private int getIndex(K key){
		int hashCode = key.hashCode();
		int index = hashCode % _capacity;
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

	// Mutator Methods
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