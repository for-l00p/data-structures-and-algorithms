public interface MinPriorityQueue<E extends Comparable<E>>{

	public void insert(E element);

	public boolean isEmpty();

	public E extractMin();

	public E min();


}
