package ds;

import java.util.List;
import java.util.ArrayList;
import java.util.*;


interface MinPQ<E> {
	public void insert (E element);
	public E min();
	public E deleteMin();
}

class Job implements Comparable<Job>{

	int duration;
	String name;
	int priority;

	public Job(int duration, String name, int priority){
		this.duration = duration;
		this.name = name;
		this.priority = priority;
	}

	public int compareTo(Job job2){
		return this.priority - job2.priority;
	}

	@Override
	public String toString(){
		return name;
	}

	
}


public class BinaryHeap<E extends Comparable<E>> implements MinPQ<E>{


	private final List<E> heap;
	private int size;
	private Comparator<E> comparator;

	public BinaryHeap(){
		heap = new ArrayList<>();
		size = 0;
		heap.add(null);
	}

	public BinaryHeap(E[] elements){
		this();
		int n = elements.length;
		for (int i = 1; i <= n; i++){
			heap.add(elements[i-1]);
			size++;
		}
		int limit = size/2;

	 	for (int i = limit; i > 0; i--){
	 		bubbleDown(i);
	 	}
	}




	public BinaryHeap(Comparator<E> comparator){
		this();
		this.comparator = comparator;
	}

	public BinaryHeap(E[] elements, Comparator<E> comparator){
		this(elements);
		this.comparator = comparator;
	}

	@Override		
	public void insert (E element){
		heap.add(element);
		size++;
		bubbleUp(size);
	}

	@Override
	public E min(){
		if (isEmpty()) return null;
		return heap.get(1);
	}
	
	@Override
	public E deleteMin(){
		if (isEmpty()) return null;
		E temp = heap.get(1);
		heap.set(1, heap.get(size));
		size--;
		bubbleDown(1);
		return temp;

	}

	private boolean isEmpty(){
		return this.size == 0;
	}


	private void bubbleDown(int i){
		validateIndex(i);
		
		while(2*i <= size){
			int childToSwap = 2*i;
			if((2*i+1 <= size) && greater(2*i, 2*i+1)){
				childToSwap++;
			}
			if (greater(childToSwap, i)) break;
			swap(i, childToSwap);
			i = childToSwap;
		}
	}

	private void bubbleUp(int i){
		validateIndex(i);
		while(i > 1 && greater(i/2, i)){
			swap(i, i/2);
			i = i/2;
		}

	}

	private boolean greater(int i, int j){
		validateIndex(i);
		validateIndex(j);
		E first = heap.get(i);
		E second = heap.get(j);

		if (this.comparator == null){
			return first.compareTo(second) > 0;
		}
		return comparator.compare(first, second) > 0;
	}

	private void swap(int i, int j){
		validateIndex(i);
		validateIndex(j);
		E temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}

	private boolean validateIndex(int i){
		if (i <= 0 || i > size) throw new NoSuchElementException("Index: " + i);
		return true;
	}

	public String toString(){

		StringBuilder s = new StringBuilder();
		for (int i = 1; i <= size; i++){
			s.append(i);
			s.append(": ");
			s.append(heap.get(i));
		}
		return s.toString();

	}

	static class DurationComparator implements Comparator<Job>{

		@Override
		public int compare(Job job1,Job job2){
			return job1.duration*job1.priority - job2.duration*job2.priority;
		}

	}


	public static void main(String[] args){
		Job dishes = new Job (10, "dishes", 1);
		Job shower = new Job (20, "shower", 2);
		Job cleaning = new Job (5, "cleannig", 3);
		Job email = new Job (12, "email", 2);
		Job call = new Job (5, "call", 4);
		Job[] jobs = new Job[]{dishes, shower};
		DurationComparator cmp = new DurationComparator();
		System.out.println("Comparing" + cmp.compare(dishes, shower));

		BinaryHeap<Job> test = new BinaryHeap<>(jobs, new DurationComparator());


		System.out.println("After 1: " + test);
		test.insert(cleaning);
		System.out.println("After 2: " + test);
		test.insert(email);
		test.insert(null);
		System.out.println("After inserting all except call: " + test);
		System.out.println("Minimum: " + test.min());
		System.out.println("Deleteting:: " + test.deleteMin());
		System.out.println("Minimum now: " + test.min());
		test.insert(call);
		System.out.println("After inserting call: " + test.min());
		
	}
}