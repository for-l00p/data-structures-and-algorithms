/*

Is the given linked list singly linked or doubly linked?


Time commplexity of the solution: Linear time solution in the size of the list?

Space complexity: Constant extra space that is, independent of n, or the size of the list) or larger?

https://codereview.stackexchange.com/questions/43464/reversing-k-sized-sequences-in-a-linked-list


----

import java.util.*; "Of course, in production code, I would list individual classes."  I consider it to be a best practice to never use import .*

PERFORMANCE

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

public class LLWithReverseK{

	private ListNode head;

// One common use of a static member class is as a public helper class, useful only in conjunction with its outer class. For example, consider an enum describing the operations supported by a calculator. The Operation enum should be a public static member class of the Calculator class. Clients of Calculator could then refer to operations using names like Calculator.Operation.PLUS and Calculator.Operation.MINUS.
// 
// If you declare a member class that does not require access to an enclosing instance, always put the  static modifier in its declaration, making it a static rather than a nonstatic member class.
	private static final class ListNode{
		//one of the principles of Effective Java is to favor composition over inheritance. The use of the final keyword also helps to enforce that principle. 
		//Being able to extend a class is a flexible feature. In fact it’s so flexible that it can be tricky to forsee all possible ways a class can be extended. So, unless you take care to design your class to allow for extension and document how methods may be overridden etc, you might be better off marking the class as final as a defensive measure. This is especially true if the class is part of a library that you hand out to other developers.

		private final int data;
		private ListNode next;

		ListNode(int data){
			this.data = data;
			this.next = null;
		}
	}

	public void reverse(int k){

	}

















	// This is a method used for unit testing
	// 

	public static void main(String[] args){
		
	}
	


}