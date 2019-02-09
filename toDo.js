




/**
 

Object Oriented Design . https://www.cs.utexas.edu/users/wcook/papers/OOPvsADT/CookOOPvsADT90.pdf



CARDS
Design a deck of cards that can be used for different card game applications.



ATM
http://www.cs.gordon.edu/courses/cs211/ATMExample/index.html


PARKING LOT
Do Parking lot : https://www.educative.io/collection/page/5668639101419520/5692201761767424/5770234338213888


ZOO
https://medium.com/omarelgabrys-blog/the-story-of-object-oriented-programming-12d1901a1825
Model the Animal kingdom as a class system, for use in a Virtual Zoo program. 

LIBRARY MANAGEMENT SYSTEM
Do Library Management System: https://www.educative.io/collection/page/5668639101419520/5692201761767424/5636470266134528


ELEVATORS
Design an Elevator System. How would you implement code to operate the elevators in a high rise?
http://play.elevatorsaga.com/
https://stackoverflow.com/questions/493276/modelling-an-elevator-using-object-oriented-analysis-and-design
https://stackoverflow.com/questions/12009556/datastructure-for-elevator-mechanism
https://codereview.stackexchange.com/questions/179645/elevator-design-interview
https://www.careercup.com/question?id=1808669
https://news.ycombinator.com/item?id=10889075

TIC-TAC-TOE
http://www.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html#zz-2
https://codepumpkin.com/tic-tac-toe/

CHESS
Given a task to program chess, what would we your classes. The goal of these interviews is to check if you write good modular code that adheres to SOLID principles.  https://codereview.stackexchange.com/questions/71790/design-a-chess-game-using-object-oriented-principles
https://stackoverflow.com/questions/4168002/object-oriented-design-for-a-chess-game

MONOPOLY
How might you design a program that lets people play Monopoly with each other over the internet? 
Sketch out a software design to referee the game Monopoly.
http://www.codinghorror.com/blog/archives/000628.html http://weblog.raganwald.com/2006/06/my-favourite-interview-question.html
 https://news.ycombinator.com/item?id=8788311

TETRIS
"Implement Tetris" is a problem the can't be given away. There's no secret that you could tell someone that would fundamentally improve how they implment Tetris. It's a process of programming and design choices. 
Contrast this with "Imagine a person walking up a flight of stairs. Imagine that at any point the person can either take a small stride (up a single step) or a large stride (up two steps). How many unique paths are there to reach the Nth stair?" The solution to this problem is the fibonacci series. Now, I don't mean to say that being able to solve this problem means nothing. Being able to answer this is some indication that a programmer is mathy and smart. But the problem can be given away, requires a single leap of insight, and is probably a bad interview question for those reasons. 
If you've done Tetris on your own beforehand the interview will go much better/faster. There are a couple of bits of tricky logic you won't get hung up on (rotations and deleting full lines) and you'll be able to speak much more fluently about the design problems and the trade-offs of each since you're not thinking it through completely on the spot. That is, knowing the problem well I could walk through a complete solution in ~20 minutes. When I solved it as an interviewer it took more like 60 minutes. That seems like it fits your description?



Create a class design to represent a filesystem.
Design an OO representation to model HTML.
How would you implement the rendering engine of a web browser?




JAVA Code Katas


----

OBJECT ORIENTED PROGRAMMING VS ALTERNATIVES (FUNCTIONAL, DECLARATIVE, IMPERATIVE)

--------


SOLID principles:  


https://softwareengineering.stackexchange.com/questions/153410/what-are-the-design-principles-that-promote-testable-code-designing-testable-c
http://wiki.c2.com/?PrinciplesOfObjectOrientedDesign



OOD Tips:

 Not every noun is a class. All your classes which describe roles should probably simply be variables, not classes. Pet is such an example. Both, a dog and a cat can be a pet. In fact, one can have any animal as pet. Pet is much more a description of the relationship between the pet holder and the animal.
 
Use Delegation/Composition whenever it makes  sense to create classes for roles. You could have a class Pet. But in that case, Pet should not be a subclass of these other classes like class Dog. Instead you should use delegation / composition, like this:

class Pet {
    Animal animal;
}
Then the class Pet describes a role, and that role can be fulfilled by any type of animal.


Separation of Concerns
This is more of an esoteric critique, but generally speaking you'll want your interfaces to be well-segregated and more specific. The current Animals interface doesn't really communicate what the interface is used for. If I were making an application for example that only showed pictures of various animals, it is unclear whether or not I should use your interface on my new class. Rather, it is better to split interfaces (usually) based on the behavior they add. This forces them to be very targeted and succint, which in turn produces code that is more stable and typically easier to maintain since changes are very isolated.

For example, instead of:

interface Animals {

    void callSound();
    int run();

}
You might have:

interface IMakesSound {
    void callSound();
}

interface ICanMove {
    int move();
}

interface IWarmBlooded {
    int currentBodyTemperature();
    bool isOverheated();
}

abstract class Animal implements IMakesSound, ICanMove {
    abstract void callSound();
    abstract int move();
}

abstract class Mammal extends Animal implements IWarmBlooded {
    private int _bodyTemp;
    abstract void callSound(); // We don't know what ALL mammals sound like
    abstract int move(); // Some mammals run, others walk, some swim, and some do all of the above.
    public int currentBodyTemperature() {
        return _bodyTemp;
    }

    public bool isOverheated() {
        return _bodyTemp > 98; // This can be overridden based on the child class if needed
    }
}

// Other classes here that inherit either from `Animal` or `Mammal`.
In the above example, I have separated out your interface into two separate interfaces and an abstract base type that combines them. Using this structure, I can create something that makes a sound but isn't an animal (for example, a robot toy dog) that can still have all of the attributes of a "real" dog, but none of the inherited animal features. This allows your code to be more flexible and more loosely coupled (which is the point of using an interface). By having code dependent on these loosely typed constructs, it allows for more flexibility.

https://codereview.stackexchange.com/questions/144468/animals-inheritance-and-interfaces





-----


ENCAPSULATION vs INFORMATION HIDING

----------


POLYMORPHISM VS ABSTRACTION

Why is polymorphism useful?

polymorphism is the ability (in programming) to present the same interface for differing underlying forms (data types). Polymorphism describes a pattern in object oriented programming in which classes have different functionality while sharing a common interface.

Examples: A real world analogy for polymorphism is a button. Everyone knows how to use a button: you simply apply pressure to it. What a button “does,” however, depends on what it is connected to and the context in which it is used — but the result does not affect how it is used. If your boss tells you to press a button, you already have all the information needed to perform the task.

The classic example is the Shape class and all the classes that can inherit from it (square, circle, dodecahedron, irregular polygon, splat and so on). With polymorphism, each of these classes will have different underlying data. A point shape needs only two co-ordinates (assuming it's in a two-dimensional space of course). A circle needs a center and radius. A square or rectangle needs two co-ordinates for the top left and bottom right corners and (possibly) a rotation. An irregular polygon needs a series of lines.

Why is it useful? 
polymorphism is used to make applications more 

(1) Modular:  Instead of messy conditional statements describing different courses of action, you create interchangeable objects that you select based on your needs. when you have different types of objects and can write classes that can work with all those different types because they all adhere to the same API (e.g. List of objects sharing a base class/interface). By making the class responsible for its code as well as its data, you can achieve polymorphism. This is in contrast to the old way of doing things in which the code was separate from the data, shape.Draw() vs drawSquare() and drawCircle().


(2) extensible.  you can add new FlyingMachines to your application without changing any of the existing logic.

Polymorphism helps implement the Open Closed Principle. https://en.wikipedia.org/wiki/Open%E2%80%93closed_principle#Polymorphic_open/closed_principle


https://stackoverflow.com/questions/1031273/what-is-polymorphism-what-is-it-for-and-how-is-it-used

------
INHERITANCE

What are the pitfalls of using inheritance? 

- Child extends Father extends Grandfather. Father implements foo. Grandfather class implements foo(), unaware of Father class’s foo() method. Now Child needs foo. Which foo will it get access to? https://en.wikipedia.org/wiki/Fragile_base_class
- In multiple inheritance, its not clear which method is inherited. Copier extends Scanner, Printer. Scanner,  Printer extends PoweredDevice. PoweredDevice halt method stops power.  Printer overwrites halt() method which stops printing. which halt() method does Copier have access to? )If your Copier paper jams, you might want to tell it to halt() to prevent damage. Fortunately, you don’t have to write that method because it’s already implemented in Printer. Unfortunately, because inheritance usually is implemented via depth-first search of the inheritance tree, you’ve called the PoweredDevice.halt() method, removing power to the Copier altogether, losing all of the other queued jobs.
These problems arise because of two conflicting tendencies of classes: classes tend to increase in size as they take on more responsibilities, but code reuse requires decrease in size.  Inheritance shouldn’t be used to share code. Although one of the advantages of inheritance is that you can put common code in the parent class, sharing code shouldn;t be the reason why you use inheritance. Inheritance should be used to used to model classes that share behavior, not merely code. 
And A pure behavior, sans state should not be a class in the first place. (Egglaying NOT OK. Mammal OK)

How should we code with these in mind? 
Programmers should "Design and document for inheritance or else prohibit it". 
How to document for inheritance?
If a method or class is not to be overridden or extended, it should be labelled as final. 

Programmers should favour composition over inheritance. Composition is a "has-a" relationship, while inheritence is an is-a relationship.

https://www.youtube.com/watch?v=wfMtDGfHWpA&t=2s 
https://stackoverflow.com/questions/8696695/composition-inheritance-and-aggregation-in-javascript
https://www.reddit.com/r/programming/comments/5dxq6i/composition_over_inheritance/da8bplv/     

What is multiple inheritance?

------

What is method overloading? What is method overriding?

Methods are overriden during compile time, but overloaded only on runtime. 

-----



https://www.programcreek.com/2013/11/arrays-sort-comparator/

- Design Patterns (Builder, Interpretor, Decorator, Chain of Responsibility, Facade, Adapter, Accumulator)
Delegation/forwarding Interpretor Patten

Chain of Responsibility

Sentinel Pattern

Accumulator: Used in Recursion. A mutable object is passed around in a recursive function.


Builder: Used when the number of arguments is variable. 

Decorator

Factory Method: Make new instances of a class without exposing the class name. 
Example: We avoid providing direct access to database connections (make its constructor private) because they're resource intensive. So we use a static factory method getDbConnection that creates a connection if we're below the limit.  Otherwise, it tries to provide a "spare" connection, failing with an exception if there are none.


Template



**/


-----

Binary Search Trees

BST 1: Sedgewick: Binary Search Trees
BST 2: https://qr.ae/TUnSpl
BST 3: CLRS
BST 4: Tree Sort 
http://plasmasturm.org/log/453/
https://stackoverflow.com/questions/5278580/non-recursive-depth-first-search-algorithm
https://www.geeksforgeeks.org/?p=6358
https://www.hackerearth.com/practice/notes/iterative-tree-traversals
https://www.quora.com/What-is-a-good-way-to-implement-stackless-recursion-less-post-order-traversal-for-a-non-threaded-binary-tree-using-Morris-method
https://stackoverflow.com/questions/5502916/explain-morris-inorder-tree-traversal-without-using-stacks-or-recursion?rq=1
BST 5: //What-are-the-most-common-and-most-important-interview-questions-on-trees-binary-trees-BST-from-data-structures?


----

Recursion and Dynamic Programming

Recursion 1: Divide and Conquer from CLRS.
Recursion 2: Order statistics by quicksort: median in O(n)
Recursion 3: All Dynamic Programming Bookmarks 
Recursion 4: Sedgewick.  https://introcs.cs.princeton.edu/java/23recursion/
Recursion 5: CLRS Dynamic Programming Exercises


------

- HashTables CLRS
//A startlingly high percentage of interview questions reduce to breadth-first search or the use of a hash table to count uniques or the use of set to remove duplicates. You need to be able to write a BFS cold, and you need to understand how a hash table is implemented. 

/**
 * For a given abstract data type (e.g. a Queue), they should be able to suggest at least two possible concrete implementations, and explain the performance trade-offs between the two implementations.
*  what you use them for (real-life examples)
* why you prefer them for those examples
*  the operations they typically provide (e.g. insert, delete, find)
*  the big-O performance of those operations (e.g. logarithmic, exponential)
*  how you traverse them to visit all their elements, and what order they're visited in
*  at least one typical implementation for the data structure
*
* 
What would be a good data structure for a photo editor? https://stackoverflow.com/questions/32211409/data-structure-to-implement-text-editor
https://stackoverflow.com/questions/649279/what-is-best-data-structure-suitable-to-implement-editor-like-notepad
https://stackoverflow.com/questions/2143817/4-program-design-interview-questions

What are some options for implementing a priority queue?

https://stackoverflow.com/questions/322715/when-to-use-linkedlist-over-arraylist-in-java
https://stackoverflow.com/questions/6129805/what-is-the-fastest-java-collection-with-the-basic-functionality-of-a-queue
 */


-----

Graphs

- BFS and DFS Graph: Solve 22.1, 22.2 in CLRS and Undirected Graph Sedgewick. Learn this BFS Technique: https://icpcarchive.ecs.baylor.edu/index.php?Itemid=8&category=343&option=com_onlinejudge&page=show_problem&problem=2738

https://codereview.stackexchange.com/questions/192694/graph-implementation-in-java-using-adjacency-list-v2

- DFS Directed: Solve 22.3, 22.4, 22.5 in CLRS and Sedgewick. and Sedgewick Directed Graphs. Chapter.  Topological Sort. Is Directed Graph Acyclic? Stack implementation of DFS. Is Graph Planar? http://www.bowdoin.edu/~ltoma/teaching/cs231/fall09/Homeworks/old/rest/H10-sol.pdf. https://cs.stackexchange.com/questions/194/the-time-complexity-of-finding-the-diameter-of-a-graph
https://www.geeksforgeeks.org/bipartite-graph/
http://shlegeris.com/2016/07/02/graph

//Bottom-up DP is equivalent to getting the directed acyclic graph formed by the DFS, running a topological sort on it, and computing the recurrence on each node in order without actually recursing. The key observation is that the topological sort ensures that each time you solve the recurrence for a state, the solutions of the 'children' states is already computed due to the topological ordering. In practice you rarely run an explicit topological sort and instead exploit some inherent topological ordering in the problem.

---

- Greedy Algorithms (Huffman Code Algorithm through Priority Queue)
https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
http://www.ccs.neu.edu/home/vkp/2510-sp11/Labs/Lab11/lab11.pdf
https://math.stackexchange.com/questions/62282/do-dynamic-programming-and-greedy-algorithms-solve-the-same-type-of-problems?rq=1


--------

/**
- Bit Representation. https://ocw.mit.edu/courses/aeronautics-and-astronautics/16-01-unified-engineering-i-ii-iii-iv-fall-2005-spring-2006/comps-programming/number_systems.pdf

https://www.geeksforgeeks.org/check-binary-representation-number-palindrome/
https://www.geeksforgeeks.org/count-total-bits-number/
https://www.geeksforgeeks.org/check-whether-k-th-bit-set-not/
https://www.geeksforgeeks.org/determine-string-unique-characters/
//They should understand the difference between a bitwise-AND and a logical-AND; similarly for the other operations.  Everyone should know the difference between signed and unsigned types, what it does to the range of representable values for that type, and whether their language supports signed vs. unsigned types.

* XORing of all the elements in a boolean array would tell you if the array has odd number of true elements
* If you have an array with all numbers repeating even number of times except one which repeats odd number of times you can find that by XORing all elements.
* Swapping values without using temporary variable
* Finding missing number in the range 1 to n
* Basic validation of data sent over the network.

x&1 will tell if the number is odd.
key.hashCode() & 0x7fffffff) % M;
}
The code masks off the sign bit (to turn the 32-bit integer into a 31-bit nonnegative integer) and then computing the remainder when dividing by M, as in modular hashing.

index = hashCode(key) & (n-1) (for mod n)

a mod 2i = a & (2i–1)
a >> b shifts the binary representation of a left a total of b places. As with logical and, this is a very inexpensive operation on a binary computer, and the effect is the same as dividing a by 2b.
https://stackoverflow.com/questions/141525/what-are-bitwise-shift-bit-shift-operators-and-how-do-they-work

**/
-----


General Programming Practice:

https://www.reddit.com/r/cscareerquestions/comments/20ahfq/heres_a_pretty_big_list_of_programming_interview/
https://web.stanford.edu/class/cs9/
http://courses.csail.mit.edu/iap/interview/materials.php
ttps://www.quora.com/What-are-the-standard-puzzles-asked-in-programming-interviews/answer/Michal-Danil%C3%A1k?share=1&srid=3OBi 
https://www.quora.com/What-are-the-best-programming-interview-questions-youve-ever-asked-or-been-asked
https://www.reddit.com/r/learnprogramming/comments/xwd16/had_a_technical_phone_interview_today_for_an/
http://www.java67.com/2018/05/top-75-programming-interview-questions-answers.html?utm_source=quora&utm_medium=referral
-----


- Elements of Programming Interviews


---------


Others:
 
- System Design: Design Instant Messaging. Design Text Editor (GoF design patterns book). Leader Election amongst servers. Design a web search engine. 
https://www.educative.io/collection/page/5668639101419520/5649050225344512/5673385510043648?affiliate_id=5082902844932096&gclid=Cj0KCQiAi57gBRDqARIsABhDSMruo-JOcu7HqX5YkfDPMqY8u5m7egvgNyF6Bmk57YuLjcRAxRRkAikaAldLEALw_wcB#utm_source=google&utm_medium=cpc&utm_campaign=grokking-manual
https://www.quora.com/What-are-some-of-the-best-answers-to-the-question-How-would-you-design-Twitter-in-a-system-design-interview

- Regular Expressions http://web.mit.edu/6.005/www/fa14/classes/12-regex-grammars/

- Unix: More specifically, linux troubleshooting and debugging. Understanding things like memory, io, cpu, shell, memory etc. would be pretty helpful. Knowing how to actually write a unix shell would also be a good idea. What tools might you use to debug something? On another note, this interview will likely push your boundaries of what you know (and how to implement it).



-----

Algorithms and Data Structures:
		

- Prefix Neighbour: https://gist.github.com/jianminchen/6652256213dd5924c033536822133909
- Revise MST in Directed Graphs. Chapter 23 CLRS.  
- Dijkstras in Disguise. Chapter 24 CLRS. https://news.ycombinator.com/item?id=17745779
- Faster exponentiation https://eli.thegreenplace.net/2009/03/21/efficient-integer-exponentiation-algorithms
https://www.geeksforgeeks.org/exponential-squaring-fast-modulo-multiplication/
- Bellman-Ford, Ford Fulkerson, Floyd Warshall
- Suffix Trees and Suffix Arrays. https://stackoverflow.com/questions/9452701/ukkonens-suffix-tree-algorithm-in-plain-english?rq=1
- Self Balancing Trees: Red-Black deleteFix. https://algs4.cs.princeton.edu/33balanced/RedBlackLiteBST.java.html 
https://stackoverflow.com/questions/29830064/the-intuition-of-red-black-tree
https://www.geeksforgeeks.org/red-black-tree-set-1-introduction-2/
- Self Balancing Trees: AVL
- Self Balancing Trees: B Trees and 2-4 Trees
- Segment Trees: http://letuskode.blogspot.in/2013/01/segtrees.html
- Radix Trees: https://news.ycombinator.com/item?id=101987
- Strategy Games Programming. https://www.cs.cmu.edu/~adamchik/15-121/lectures/Game%20Trees/Game%20Trees.html
- Branch and Bound



-----

/**
 
Java I/O: https://www.cs.cmu.edu/~adamchik/15-121/lectures/IO%20Framework/io.html
JAVASCRIPT/BACKEND DEVELOPER INTERVIEW

Microservices:

https://www.sequoiacap.com/article/build-us-microservices/

BACKEND OBJECT-ORIENTED DESIGN/ DATABASE SCHEMA PREPARATION

https://stackoverflow.com/questions/24096546/mongoose-populate-vs-object-nesting
https://www.mongodb.com/blog/post/6-rules-of-thumb-for-mongodb-schema-design-part-3?_ga=2.207890002.1365381269.1528077157-349313011.1525218488
https://www.mongodb.com/blog/post/the-mean-stack-mistakes-youre-probably-making
https://www.mongodb.com/presentations/webinar-working-with-graph-data-in-mongodb
http://seanhess.github.io/2012/02/01/mongodb_relational.html
https://www.compose.com/articles/graph-data-with-mongodb/
https://medium.com/labcodes/graph-databases-talking-about-your-data-relationships-with-python-b438c689dc89
https://news.ycombinator.com/item?id=18795498


JAVASCRIPT INTERVIEW PREPARATION

https://stackoverflow.com/questions/21607692/understanding-the-event-loop
https://www.youtube.com/watch?time_continue=71&v=8aGhZQkoFbQ
What happens when javascript makes an ajax request in a browser?
https://blog.bloomca.me/2018/12/22/writing-a-web-server-node.html

On Closures

https://softwareengineering.stackexchange.com/questions/285941/why-would-a-program-use-a-closure
https://stackoverflow.com/questions/111102/how-do-javascript-closures-work?rq=1

Closures are a neat way of dealing with the following two realities of JavaScript: a. scope is at the function level, not the block level and, b. much of what you do in practice in JavaScript is asynchronous/event driven. A closure is one way of supporting first-class functions; it is an expression that can reference variables within its scope (when it was first declared), be assigned to a variable, be passed as an argument to a function, or be returned as a function result.

When a function (foo) declares other functions (bar and baz), the family of local variables created in foo is not destroyed when the function exits. The variables merely become invisible to the outside world. Foo can therefore cunningly return the functions bar and baz, and they can continue to read, write and communicate with each other through this closed-off family of variables ("the closure") that nobody else can meddle with, not even someone who calls foo again in future.

https://medium.com/coderbyte/a-tricky-javascript-interview-question-asked-by-google-and-amazon-48d212890703

On Prototypical Inheritance (constructor syntax vs Object Only "Factory function based" syntax) vs Class Based Inheritance (class syntax)

https://davidwalsh.name/javascript-objects-deconstruction
https://stackoverflow.com/questions/1646698/what-is-the-new-keyword-in-javascript
https://zeekat.nl/articles/constructors-considered-mildly-confusing.html
https://stackoverflow.com/questions/33692912/using-object-assign-and-object-create-for-inheritance
https://stackoverflow.com/questions/4166616/understanding-the-difference-between-object-create-and-new-somefunction
https://stackoverflow.com/questions/50050937/prototypal-inheritance-object-create-vs-object-assign
https://medium.com/javascript-scene/3-different-kinds-of-prototypal-inheritance-es6-edition-32d777fa16c9
https://medium.com/javascript-scene/common-misconceptions-about-inheritance-in-javascript-d5d9bab29b0a
https://medium.com/javascript-scene/the-two-pillars-of-javascript-ee6f3281e7f3
https://medium.com/javascript-scene/javascript-factory-functions-vs-constructor-functions-vs-classes-2f22ceddf33e

One major drawback to delegation is that it’s not very good for storing state. If you try to store state as objects or arrays, mutating any member of the object or array will mutate the member for every instance that shares the prototype. In order to preserve instance safety, you need to make a copy of the state for each object.


Private Variables:

https://lazamar.github.io/closures-private-variables-and-methods-in-javascript/
https://www.joezimjs.com/javascript/javascript-closures-and-the-module-pattern/
https://stackoverflow.com/questions/9772307/declaring-javascript-object-method-in-constructor-function-vs-in-prototype/9772864#9772864
http://www.crockford.com/javascript/private.html
https://stackoverflow.com/questions/12180790/defining-methods-via-prototype-vs-using-this-in-the-constructor-really-a-perfo
https://stackoverflow.com/questions/310870/use-of-prototype-vs-this-in-javascript

This pattern of public, private, and privileged members is possible because JavaScript has closures. What this means is that an inner function always has access to the vars and parameters of its outer function, even after the outer function has returned. This is an extremely powerful property of the language. There is no book currently available on JavaScript programming that shows how to exploit it. Most don't even mention it. Private and privileged members can only be made when an object is constructed. Public members can be added at any time.


On 'This'

https://zellwk.com/blog/this/
https://stackoverflow.com/questions/80084/in-javascript-why-is-the-this-operator-inconsistent/80478#80478


On Continuation Passing Style 

https://www.quora.com/What-is-continuation-passing-style-in-functional-programming


On Iterators

https://codeburst.io/a-simple-guide-to-es6-iterators-in-javascript-with-examples-189d052c3d8e


On Async Programming


async/await
Promise. Promise.reject. Promise.resolve
Generator
Promise.coroutine
try catch throw
.catch.then
Promise.all

Promises and Generators:

Interview question: https://stackoverflow.com/questions/5080028/what-is-the-most-efficient-way-to-concatenate-n-arrays
Interview question:
Implement a callback based asynchronous function. The callback handles error cases and correct response cases. 

Examples:

import {defineRequestType} from "./crow-tech";

defineRequestType("note", (nest, content, source, done) => {
  console.log(`${nest.name} received note: ${content}`);
  done();
});

import {bigOak} from "./crow-tech";


function readStorage(name, )

bigOak.readStorage("food caches", caches => {
  let firstCache = caches[0];
  bigOak.readStorage(firstCache, info => {
    console.log(info);
  });
});




Next: write a promise wrapper for the callback function: a function that runns the async function but returns a promise. (The promise is such that it is rejected in the case of an error)

function storage(nest, name) {
  return new Promise(resolve => {
    nest.readStorage(name, result => resolve(result));
  });
}

storage(bigOak, "enemies")
  .then(value => console.log("Got", value));


function requestType(name, handler) {
  defineRequestType(name, (nest, content, source,
                           callback) => {
    try {
      Promise.resolve(handler(nest, content, source))
        .then(response => callback(null, response),
              failure => callback(failure));
    } catch (exception) {
      callback(exception);
    }
  });
}

beforeEach(function() {
    sinon.stub(tax, 'calculate', function(subtotal, state, done) {
      setTimeout(function() {
        done({
          amount: 30
        });
      }, 0);
 afterEach(function() {
    tax.calculate.restore();
  });

Generator function with yield keyword: used when you have a function which needs to suspend its execution, do some task, then resume execution.  

Promise.coroutine(generator function): returns a function. This function, when called, returns a promise to execute the series of taks impllicit in generator.  This promise is resolved when the done is set true within the generator.

async keyword returns a promise  = Promise.couroutine returns a promise. The 'async' annotation or Promise.coroutine is syntactic sugar for 'returns a promise'. 

await is nothing but sugar for promise.then syntax. 

https://stackoverflow.com/questions/44149096/for-async-tests-and-hooks-ensure-done-is-called-if-returning-a-promise-en
https://stackoverflow.com/questions/46515764/how-can-i-use-async-await-at-the-top-level
https://stackoverflow.com/questions/40745153/es8-immediately-invoked-async-function-expression


Principles:

Short Methods.Good method sizes are like 3-4 lines, sometimes 5, only in rare situations a bit more.
A method should do only one thing, it should do it well, and it should do it only. (Robert C. Martin)


Use Proper and Consistent Grammar
Sometimes you use the normal verb form, i.e. giveMilk(), sometimes you use the third person form, i.e. sounds(). Be consistent. It's common in Java to always use the normal verb form, not the third person form, in code. And in comments, it's common in Java to always use the third person form, except if you directly address the reader.

Explicitness
The default modifier for your classes / interfaces will make them only visible from within your package. While this may be what you intended, it's always better to be explicit and state the level of access you intend each portion of your code to have. This reduces guess-work made by others who work with your code (which can help to reduce bugs made based on assumptions) and it improves the readability.



