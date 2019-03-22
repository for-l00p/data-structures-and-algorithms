package puzzles;

import java.util.*;

public class HarryPotter {
	

	

	public static double cartTotal(int[] books){
		Cart cart = new Cart(books);
		List<BookSet> possibleBookSets = new ArrayList<BookSet>();
		int[] currentBooks = new int[5];
		generateBookSets(possibleBookSets, currentBooks ,  0);
		Map<String, Double> cache = new HashMap<>();
		return cartTotal(cart, possibleBookSets, cache);
	}	

	private static void generateBookSets(List<BookSet> possibleBookSets, int[] books , int bookNumber){

		if (bookNumber > 4){
			if(!Arrays.equals(new int[5], books)){
				possibleBookSets.add(new BookSet(books));
			}
			
		} else {
				books[bookNumber] = 1;
				generateBookSets(possibleBookSets, books, bookNumber + 1);
				books[bookNumber] = 0;
				generateBookSets(possibleBookSets, books, bookNumber + 1);
			}
		
	}

	private static double cartTotal(Cart cart, List<BookSet> possibleBookSets, Map<String, Double> cache ){
		//System.out.println("Cart:" + cart.toString());
		
		if (cache.containsKey(cart.toSortedString())){
			return cache.get(cart.toSortedString());
		}
		double currentMinTotal = Double.MAX_VALUE;
		if (cart.isEmpty()){
			currentMinTotal = 0;
		} else {
			for (BookSet bookset: possibleBookSets){
				if(cart.contains(bookset)){
					Cart newCart = cart.remove(bookset);
					double totalWithoutBookSet = cartTotal(newCart, possibleBookSets, cache);
					if (totalWithoutBookSet + bookset.getPrice() < currentMinTotal){
						currentMinTotal = totalWithoutBookSet + bookset.getPrice();
					}
				} 
			}
		}
		cache.put(cart.toSortedString(), currentMinTotal);
		return currentMinTotal;
	}




	static class Cart {

		private int[] books;

		public Cart(int[] books){
			this.books = books;
		}

		@Override
		public String toString(){

			return Arrays.toString(books);
		}

		public String toSortedString(){
			int[] dummy = Arrays.copyOf(books, books.length);
			Arrays.sort(dummy);
			return Arrays.toString(dummy);
		}

		public boolean isEmpty(){
			for(int i = 0; i < 5; i++){
				if(books[i] > 0){
					return false;		
				} 
			}
			return true;
		}

		public boolean contains(BookSet set){
			for(int i = 0; i < 5; i++){
				if(set.contains(i) && books[i] <= 0){
					return false;		
				} 
			}
			return true;

		}

		public Cart remove(BookSet set){
			int[] newCartBooks = Arrays.copyOf(books, books.length);
			for(int i = 0; i < 5; i++){
				if(set.contains(i) && books[i] > 0){
					newCartBooks[i] = newCartBooks[i] -1;		
				}		
			}
			return new Cart(newCartBooks);
		}
	}

	static class BookSet {

		private boolean[] books = new boolean[5];
		private double PRICE = 8.0;

		public BookSet(){

		}

		public BookSet(String binary){
		
			for (int i = 0; i < 5; i++){
				this.books[i] = (Character.getNumericValue(binary.charAt(i)) > 0);
			}
		}

		public BookSet(int[] books){
			for (int i = 0; i < 5; i++){
				this.books[i] = (books[i] > 0);
			}
		}

		@Override
		public String toString(){
			return Arrays.toString(books);
		}

		public boolean contains(int index){
			return books[index];
		}


		private int numBooks(){
			int n = 0;
			for (boolean b: books){
				if(b) n++;
			}
			return n;
		}

		public double getPrice(){
			
			int n = numBooks();
			double total = n*(PRICE);

			switch(n){
				case 5: 
					total = total*0.75;
					break;
				case 4:
					total = total*0.80;
					break;
				case 3:
					total = total*0.90;
					break;
				case 2:
					total = total*0.95;
					break;
			}

			return total;
		}
	}


	public static void main(String[] args){

		//List<BookSet> b = new ArrayList<BookSet>();
		//generateBookSets(b, new int[5], 0);
		int[] input = new int[]{100,20,33,30,50};
		System.out.println(cartTotal(input));
	


		// for (BookSet c: b){
		// 	System.out.println(c.toString() + ": " + c.getPrice());
		// }
		
	}




}