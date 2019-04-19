
/**

Reference:
http://web.mit.edu/6.031/www/fa18/classes/16-recursive-data-types/1-recursive/

Let’s define a data type for an immutable list, ImList<E>. The data type has four fundamental operations:

empty: void → ImList	// returns an empty list
cons: E × ImList → ImList 	// returns a new list formed by adding an element to the front of another list
first: ImList → E	// returns the first element of a list, requires the list to be nonempty
rest: ImList → ImList	// returns the list of all elements of this list except for the first, requires the list to be nonempty


**/

interface ImList<E> {
	public ImList<E> cons(E elt);
	public E first();
	public ImList<E> rest();
	/**
	 * The signature for empty uses an unfamiliar bit of generic type syntax. The E in ImList<E> is a placeholder for the type of elements in an instance of ImList, but empty is a static method: it cannot see instance variables or methods, and it also cannot see the instance type parameter. You can read the declaration of empty as: “for any E, empty() returns an ImList<E>.”
	 * @return [description]
	 */
	public static <E> ImList<E> empty(){
		return new Empty<E>();
	};

	public String toString();
	/**
	 * Rep Invariant:
	 * first(cons(elt, lst) ) = elt 
	 * rest(cons(elt, lst) ) = lst
	 *
	 * Datatype definition: ImList<E> = Empty + Cons(elt:E, rest:ImList<E>)
	 */
}


class Empty<E> implements ImList<E>{

	/**
	 * Abstraction Function: AF() = an empty list/represents an empty list
	 * 
	 * The abstraction function goes from representation to abstract value. But Empty has an empty rep, with no fields at all! That’s all right, its job is to represent a single value in the abstract space: the empty list.
	 *
	 * Rep Invariant: Since Empty has an empty rep, it has an empty rep invariant.
	 */

	public Empty(){

	}

	
	public ImList<E> cons(E elt){
		return new Cons<E>(elt, this);
	}

	public E first(){
		throw new UnsupportedOperationException();
	}

	public ImList<E> rest(){
		throw new UnsupportedOperationException();
	}
	
	public String toString(){
		return new String("");
	}
}


class Cons<E> implements ImList<E>{

	/**
	 * AF: represents an empty list
	 *
	 * RI: elt != null and rest != null
	 */
	private final E first;
	private final ImList<E> rest;

	public Cons(E first, ImList<E> rest){
		this.first = first;ro
		this.rest = rest;
	}

	// public ImList<E> empty(){
	// 	return new Empty<>();
	// }
	public ImList<E> cons(E elt){
		return new Cons<E>(elt, this);
	}

	public E first(){
		return this.first;
	}

	public ImList<E> rest(){
		return this.rest;
	}

	public String toString(){
		return ("[" + first.toString() + ", " + rest.toString() + "]") ;
	}

}

public class ImListClient{

	public static void main(String[] args){

	 	ImList<Integer> testList = ImList.empty();
	 	testList = testList.cons(3);
	 	System.out.println(testList.toString());

	}


}