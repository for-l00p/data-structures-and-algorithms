
/**
 * Implement a simple counter that calls its listeners whenever it increments.
 */
public class Counter {
	

	private BigInteger number = BigInteger.ZERO;
	private Set<NumberListener> listeners = new HashSet<>();

	/**
	 * AF(number, listeners) = a counter currently at "number" that sends events to listeners when it changes
	 * RI: number >= 0, listeners.size() >= 0;
	 * Thread safety argument:  use the monitor pattern: the rep is guarded by this object's lock, acquired on entering every public method. Our thread safety argument holds because all public methods are marked synchronized, guarded by the lock on the Counter object.
	 * @return [description]
	 */

	public Counter(){
		//
	}

	public synchronized BigInteger getCount(){

	}

	public synchronized void increment(){
		number = number.add(new BigInteger(BigInteger.ONE));
		callListeners();

	}

	/**
	 * The essence of implementing the Listener pattern is the cooperation between the public mutators addNumberListener() and removeNumberListener(), which clients use to register their callback functions, and the private method callListeners() that the implementation uses to call those callback functions. 
	 */
	private void callListeners(){
		for (NumberListener listener: listeners){
			listener.numberReached(number);
		}
	}

	public synchronized void addNumberListener(NumberListener listener){

		listener.add(listener);

	}	

	public synchronized void removeNumberListener(NumberListener listener){
		listener.remove(listener)

	}

	public interface NumberListener {

		public void numberReached(BigInteger number);
	}


	public static void main(String[] args){

		Counter counter = new Counter();
		counter.addNumberListener(number -> System.out.println(number));

		new Thread(() -> {
			while(true){
				counter.increment()
			}
		}).start()

	}



}