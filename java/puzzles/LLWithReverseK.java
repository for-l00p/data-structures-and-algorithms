/*

https://codereview.stackexchange.com/questions/43464/reversing-k-sized-sequences-in-a-linked-list

https://www.quora.com/How-do-I-reverse-elements-of-a-linked-list-in-groups-of-n-at-a-time-where-n-is-less-than-equal-to-size-of-the-linked-list-Can-anyone-suggest-an-algorithm-for-doing-this-in-generic-way-using-minimum-number-of-variable-Refrain-from-using-STLs/answer/Brian-Bi



Is the given linked list singly linked or doubly linked?

Time commplexity of the solution: Linear time solution in the size of the list?

Space complexity: Constant extra space that is, independent of n, or the size of the list) or larger?

*I think we should implement a linkedlist, because otherwise, we could just use the use its reverse function... (descendingIterator())

Is the list of generic type or a specific type?

How are we taking the input? One by one or in an array form?

Is it okay to use a recrusive solution or an iterative solution? (Note: Are we worried about stack overflow?)


----
import java.util.*; "Of course, in production code, I would list individual classes."  

If asked why?

I consider it to be a best practice to never use import .*

PERFORMANCE (No difference)

import a.*;
vs
import a.X;
Makes no difference at runtime. The compiler hardwires the resolved class names into the generated .class files.
There is not a performance or overhead cost to doing import .* vs importing specific types.

NAME COLLISION

Take a look at the java API, and you'll see many classes and interfaces with the same name in different packages.
For example:
java.lang.reflect.Array
java.sql.Array

So, if you import java.lang.reflect.* and java.sql.* you'll have a collision on the Array type, and have to fully qualify them in your code.Importing specific classes instead will save you this hassle. his is actually a very bad problem.

Suppose you write

import a.*;
import b.*;
...
Foo f;
and class Foo exists in package a.
Now you check in your perfectly compiling code, and six months later, someone adds class Foo to package b. (Perhaps it's a third party lib that added classes in the latest version). Poof! Now your code refuses to compile. Never use import-on-demand. It's evil!



CLARITY AND NO-AMBIGUITY
in my code I tend to use tons of classes from a few packages along with a few odd classes here and there. I like to keep my imports list small so I can tell what is going on at a glance. To do this, I set the threshold at 4 classes. Above that, Eclipse will use * for my code. I find this keeps my package imports readable, and I tend to refer to them as the first thing I do when I look at a class, to answer the question: Who does it talk to?

---


 */


//Reverse LL refers to a LL with the method reverse
//

import java.util.*;

public class LLWithReverseK<T>{

	private ListNode<T> head;

// One common use of a static member class is as a public helper class, useful only in conjunction with its outer class. For example, consider an enum describing the operations supported by a calculator. The Operation enum should be a public static member class of the Calculator class. Clients of Calculator could then refer to operations using names like Calculator.Operation.PLUS and Calculator.Operation.MINUS.
// 
// If you declare a member class that does not require access to an enclosing instance, always put the  static modifier in its declaration, making it a static rather than a nonstatic member class.
// 
	private static final class ListNode<T>{
		//one of the principles of Effective Java is to favor composition over inheritance. The use of the final keyword also helps to enforce that principle. 
		//Being able to extend a class is a flexible feature. In fact it’s so flexible that it can be tricky to forsee all possible ways a class can be extended. So, unless you take care to design your class to allow for extension and document how methods may be overridden etc, you might be better off marking the class as final as a defensive measure. This is especially true if the class is part of a library that you hand out to other developers.

		private final T data;
		private ListNode<T> next;

		ListNode(T data, ListNode<T> next){
			this.data = data;
			this.next = next;
		}
	}


	private LLWithReverseK(){
		this.head = null;
	}


	public LLWithReverseK(List<T> list){

		for(int i = list.size()-1; i >= 0; i--){
			this.head = new ListNode<T>(list.get(i), this.head);
		}
	}

	public void reverseK(final int k){

		if( k <= 1 || this.head == null || this.head.next == null){
			System.out.println("The list is either empty or a singleton; or the argument is 1");
			return;
		}


		/**
		 * Let us assume that the list from 0....i-1 has been reversed: each of the nodes from 0...i-1 point to the correct node in the final order.  and we are next supposed to reverse the list from i .... i+ n-1. This involves three steps:
		 * Change the node at i-1's next pointer to the node at i+n-1.
		 * Change the node at i's next pointer to the node at i+n 
		 * Change node at i + j's next pointer to the node at i+j-1, for j = i+1 ...i+n-1.
		 *
		 * What about the boundary cases? 
		 *
		 * Initially, i - 1 is null. We dont need to change its next pointer. i is head. 
		 *
		 * 
		 */

		ListNode<T> nodebeforeIndex = null; // beforeIndex
		ListNode<T> nodeAtIndex = this.head;
		ListNode<T> current;; // current's index = i
		ListNode<T> next; // next's index = i+1
		ListNode<T> temp;

		while(nodeAtIndex != null){
			current = nodeAtIndex; // current's index = i
			next = current.next; // next's index = i+1
			for(int j = 1; j < k && next != null; j++){
				temp = next.next;
				next.next = current;
				current = next;
				next = temp;
			}
			if(nodebeforeIndex != null){nodebeforeIndex.next = current;} else {
				this.head = current;
			}
			nodeAtIndex.next = next;
			nodebeforeIndex = nodeAtIndex;
			nodeAtIndex = next;
		}

	}


	public void recrusiveReverseK(int k){
		this.head = recrusiveHelper(this.head, k, this.head, null, k);
	}



	private ListNode<T> recrusiveHelper(ListNode<T> head, int k, ListNode<T> current, ListNode<T> prev,  int remaining){

		if(current == null){
			return head;

		} else if (remaining == 1){

			ListNode<T> nextHead = recrusiveHelper(current.next, k, current.next, null, k);
			current.next = prev;
			head.next = nextHead;
			return current;

		} else {

			ListNode<T> headOfRemainingListReversed = recrusiveHelper(head, k, current.next, current, remaining - 1);
			if(current != head && headOfRemainingListReversed != head){
				current.next = prev;
			}
			return headOfRemainingListReversed;	

		}
	}


	public void reverseKUnchanged(int k){

		Deque<ListNode<T>> stack = new ArrayDeque<>();
		int nodesInStack = 0;
		ListNode<T> beforeHead = null;
		ListNode<T> currentHead = this.head;
		ListNode<T> current = currentHead;

		while(current.next != null){
			//System.out.println("Current points to: " + current.data);
			stack.push(current);
			nodesInStack++;
			 if(nodesInStack == k){
			 	//System.out.println("Reached end of k group. Attaching original head: " + currentHead.data +  " to next k group's head " +  current.next.data);
				currentHead.next = current.next;
				if(beforeHead != null) {
					//System.out.println("Attaching previous k group to new head " +  current.data);
					beforeHead.next = current;
				} else {
					//System.out.println("No previous k group to attach. Making the head of list: " +  current.data);
					this.head = current;
				}
				beforeHead = currentHead;
				currentHead = current.next;
			
				while(nodesInStack > 0){
					current = stack.pop();
					nodesInStack--;
					if(stack.peek() != null){
						//System.out.println("Moving next pointer of " + current.data + " to " + stack.peek().data) ;
						current.next = stack.peek();
					}
				}
				current = currentHead;	
			 } else {
			 	current = current.next;
			 }

			
			
		}

	}





	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder(this.head.data.toString());
		ListNode<T> next = this.head.next;

		while(next != null){
			//System.out.println("Appending: " + next.data.toString());
			sb.append(" -> " + next.data.toString());
			next = next.next;
		}

		return sb.toString();
	}

	/**
	 * Unit tests
	 * 
	 * 
	 */
	

	public static void main(String[] args){

		LLWithReverseK<Integer> test = new LLWithReverseK<>(Arrays.asList(new Integer[] {1,2,3,4,5,6,7,8,9,10,11}));
		//LLWithReverseK<Integer> test = new LLWithReverseK<>(Arrays.asList(new Integer[] {1,2,3,4,5,6}));
		System.out.println(test.toString());
		//test.recrusiveReverseK(3);
		test.reverseKUnchanged(3);
		System.out.println(test.toString());

		
	}
	


}