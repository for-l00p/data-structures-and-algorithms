
QuickSort:
CLRS Chapter

Recap:
https://www.iarcs.org.in/inoi/online-study-material/topics/sorting.php
https://www.toptal.com/developers/sorting-algorithms/nearly-sorted-initial-order
https://www.youtube.com/watch?v=4OxBvBXon5w

Constraint Sorting 
RadixSort
https://www.youtube.com/watch?v=cR4rxllyiCs


Technique: Greedy
http://www3.cs.stonybrook.edu/~skiena/214/lectures/
Fractional Knapsack (with duplicates, without duplicates)
0-1 Knapsack (with duplicates, without duplicates) //Draw Analogy with CoinChange, LIS, LCS
Activity Selection
Huffman Code Algorithm through abs/Lab11/lab11.pdf
https://www.topcoder.com/community/competitive-programming/tutorials/greedy-is-good/
//What are some options for implementing a priority queue?
CLRS Theory: Greedy Algorithms
Priority Queue
https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
http://www.ccs.neu.edu/home/vkp/2510-sp11/L



Topic: Dynamic Programming

Edit distance
CLRS Dynamic Programming Exercises
All Dynamic Programming Bookmarks
Polynomial Hash
http://blog.ezyang.com/2010/11/dp-zoo-tour/

//Bottom-up DP is equivalent to getting the directed acyclic graph formed by the DFS, running a topological sort on it, and computing the recurrence on each node in order without actually recursing. The key observation is that the topological sort ensures that each time you solve the recurrence for a state, the solutions of the 'children' states is already computed due to the topological ordering. In practice you rarely run an explicit topological sort and instead exploit some inherent topological ordering in the problem.


----------------




Find Median in O(n)
//Order statistics by quicksort: median in O(n)). https://cs.stackexchange.com/questions/1455/how-to-use-adversary-arguments-for-selection-and-insertion-sort

https://www.hackerearth.com/practice/notes/dynamic-programming-problems-involving-grids/

Implement FizzBuzz (criteria as parameter)  
// You will be given a list of numbers where the first number is the desired sum and the remaining numbers are the coins. Determine if the coins can be added together to reach the exact sum. You cannot use the same coin twice, but you can use duplicate coins (if there are any). For example, when given {12, 1, 2, 3, 4, 5}, print true since 1+2+4+5 = 12 (among other possibilities). When given {11, 1, 5, 9, 13}, print false, since there's no way to reach a sum of 11 with those numbers.https://www.learneroo.com/modules/71/nodes/400


----------------------------------



Topic: Undirected Graphs

https://codereview.stackexchange.com/questions/192694/graph-implementation-in-java-using-adjacency-list-v2
https://codereview.stackexchange.com/questions/114313/simple-oriented-graph-implementation-in-java
https://stackoverflow.com/questions/40810454/how-to-implement-graph-search-dfs-with-object-oriented-design/40810666#40810666

BFS and DFS Graph: Solve 22.1, 22.2 in CLRS and Undirected Graph Sedgewick.
https://cs.stackexchange.com/questions/64379/can-breadth-first-search-be-implemented-recursively-without-data-structures
Learn this BFS Technique: https://icpcarchive.ecs.baylor.edu/index.php?Itemid=8&category=343&option=com_onlinejudge&page=show_problem&problem=2738
https://www.quora.com/What-graph-topics-should-I-study-in-order-to-be-adequately-prepared-for-a-Google-software-engineer-interview-Would-it-be-worthwhile-to-also-study-algorithms-for-minimum-spanning-trees-maximum-network-flows-bipartite-matching-etc

https://www.topcoder.com/community/competitive-programming/tutorials/introduction-to-graphs-and-their-data-structures-section-1/
https://www.topcoder.com/community/competitive-programming/tutorials/introduction-to-graphs-and-their-data-structures-section-2/
https://www.topcoder.com/community/competitive-programming/tutorials/introduction-to-graphs-and-their-data-structures-section-3/
https://stackoverflow.com/questions/8039097/print-binary-tree-in-bfs-fashion-with-o1-space
https://www.iarcs.org.in/inoi/online-study-material/topics/advanced-graph-algorithms.php
https://www.iarcs.org.in/inoi/online-study-material/topics/graphs.php



Topic: Directed Graphs

https://www.iarcs.org.in/inoi/online-study-material/topics/dags.php
DFS Directed: Solve 22.3, 22.4, 22.5 in CLRS and Sedgewick. and Sedgewick Directed Graphs. Chapter.  
Topological Sort.
 Is Directed Graph Acyclic? Stack implementation of DFS. 
 Is Graph Planar? 
 http://www.bowdoin.edu/~ltoma/teaching/cs231/fall09/Homeworks/old/rest/H10-sol.pdf. https://cs.stackexchange.com/questions/194/the-time-complexity-of-finding-the-diameter-of-a-graph
https://www.geeksforgeeks.org/bipartite-graph/


----------

Phase II: Revision


Sedgewick Recursion: https://introcs.cs.princeton.edu/java/23recursion/
CLRS Theory: Hashtables
http://shlegeris.com/2016/07/02/graph
Implement:  http://plasmasturm.org/log/453/
Morris Traversals
Do BST construction from given traversals.
(CLRS): Tree Sort. Prove running time with tree building.
https://www.geeksforgeeks.org/?p=9411/
Sucessor threaded binary tree question (CLRS)
http://cslibrary.stanford.edu/110/BinaryTrees.html#s2
Neatest Tree Question: http://cslibrary.stanford.edu/109/TreeListRecursion.pdf
Exercises: Binary Search Trees: Sedgewick
LinkedList
http://cslibrary.stanford.edu/110/BinaryTrees.html#s2


Review solutions to:
Yatzy: Make UML Diagram //Learning how to diagram the problem on a whiteboard, step by step, gave me a way to visualize the problem. The benefit of the whiteboard is that you can erase very easily (also if you have a metal one you can use colored magnets to represent things like pointers).
Harry Potter bottom-up DP
Zackendorf Bottoms-Up
Roman Numbers
NumberChains
Integer to English Words Leetcode hints

Topic: Trie
PhoneNumberTrie (with minimal requirements and then extending to autocomplete feature) https://www.topcoder.com/community/competitive-programming/tutorials/using-tries/
https://leetcode.com/articles/implement-trie-prefix-tree/

Common Patterns in Games and Simulations (Reversi, MarsRover, GameOfLife, BowlingGame, Tennis, MontyHall, Friday13th) //https://www.topcoder.com/community/competitive-programming/tutorials/algorithm-games/
http://www3.cs.stonybrook.edu/~skiena/214/lectures/lect14/lect14.html

Review LIS O(n) technique. 



LinkedList Practice: https://www.quora.com/How-do-I-reverse-elements-of-a-linked-list-in-groups-of-n-at-a-time-where-n-is-less-than-equal-to-size-of-the-linked-list-Can-anyone-suggest-an-algorithm-for-doing-this-in-generic-way-using-minimum-number-of-variable-Refrain-from-using-STLs/answer/Brian-Bi





Technique: Sliding Window 

https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/sliding-window-algorithm-template-to-solve-all-the-leetcode-substring-search-problem
https://medium.com/leetcode-patterns/leetcode-pattern-2-sliding-windows-for-strings-e19af105316b
https://www.iarcs.org.in/inoi/online-study-material/topics/sliding-window.php

Technique: Backtracking

https://en.wikipedia.org/wiki/Backtracking
Theory: http://www3.cs.stonybrook.edu/~skiena/214/lectures/lect10/lect10.html
http://www3.cs.stonybrook.edu/~skiena/214/lectures/lect11/lect11.html
https://www.cs.princeton.edu/courses/archive/fall12/cos226/lectures/67CombinatorialSearch-2x2.pdf
https://cses.fi/book/book.pdf
https://www.cis.upenn.edu/~matuszek/cit594-2012/Pages/backtracking.html

Problems: 
Knights Tour //https://leetcode.com/problems/knight-probability-in-chessboard/
//https://www.geeksforgeeks.org/the-knights-tour-problem-backtracking-1/
Sudoku Solver // https://leetcode.com/problems/valid-sudoku/
https://leetcode.com/problems/sudoku-solver/
SPOJ:  Grouping Ahoy! //https://www.spoj.com/problems/ANARC05H/
N-Queens
Permutations
Subsequences
Magic Squares   


Technique: UnionFind
https://news.ycombinator.com/item?id=17770418


Leetcode:
https://leetcode.com/problems/lfu-cache/
https://leetcode.com/articles/design-in-memory-file-system/
https://leetcode.com/problems/merge-k-sorted-lists/



/**

https://www.cs.uct.ac.za/mit_notes/software/htmls/ch08s03.html

Design OO Systems 

- Implement Roulette Strategy
- Chess
- Elevator
- Parking Lot
- Deck of Cards
- Tetris
- Implement Bowling Game Kata


SUPERMARKET CART
https://codereview.stackexchange.com/questions/94213/super-market-checkout-pricing-strategies


CARDS
Design a deck of cards that can be used for different card game applications.
https://codereview.stackexchange.com/questions/128398/deck-of-cards-design
https://stackoverflow.com/questions/15942050/deck-of-cards-java
https://www.geeksforgeeks.org/design-data-structuresclasses-objectsfor-generic-deck-cards/

ATM
- http://www.cs.gordon.edu/courses/cs211/ATMExample/index.html
- http://c2.com/doc/oopsla89/paper.html


PARKING LOT
- Do Parking lot : https://www.educative.io/collection/page/5668639101419520/5692201761767424/5770234338213888
https://www.geeksforgeeks.org/design-parking-lot-using-object-oriented-principles/

ZOO
- https://medium.com/omarelgabrys-blog/the-story-of-object-oriented-programming-12d1901a1825
- Model the Animal kingdom as a class system, for use in a Virtual Zoo program. 

LIBRARY MANAGEMENT SYSTEM
Do Library Management System: https://www.educative.io/collection/page/5668639101419520/5692201761767424/5636470266134528


ELEVATORS
Design an Elevator System. How would you implement code to operate the elevators in a high rise?
- https://codereview.stackexchange.com/questions/7990/elevator-program
http://play.elevatorsaga.com/
- https://stackoverflow.com/questions/493276/modelling-an-elevator-using-object-oriented-analysis-and-design
- https://stackoverflow.com/questions/12009556/datastructure-for-elevator-mechanism
- https://codereview.stackexchange.com/questions/179645/elevator-design-interview
- https://www.careercup.com/question?id=1808669
- https://news.ycombinator.com/item?id=10889075

TIC-TAC-TOE
- http://www.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html#zz-2
- https://codepumpkin.com/tic-tac-toe/
- http://faculty.ycp.edu/~dhovemey/spring2011/cs320/assign/assign5.html

CHESS
Given a task to program chess, what would we your classes. The goal of these interviews is to check if you write good modular code that adheres to SOLID principles.  
- https://codereview.stackexchange.com/questions/71790/design-a-chess-game-using-object-oriented-principles
- https://stackoverflow.com/questions/4168002/object-oriented-design-for-a-chess-game
- https://codereview.stackexchange.com/questions/101574/chess-game-in-python


MONOPOLY
How might you design a program that lets people play Monopoly with each other over the internet? 
Sketch out a software design to referee the game Monopoly.
http://www.codinghorror.com/blog/archives/000628.html http://weblog.raganwald.com/2006/06/my-favourite-interview-question.html
https://news.ycombinator.com/item?id=8788311

TETRIS

"Implement Tetris" is a problem the can't be given away. There's no secret that you could tell someone that would fundamentally improve how they implement Tetris. It's a process of programming and design choices. 

Contrast this with "Imagine a person walking up a flight of stairs. Imagine that at any point the person can either take a small stride (up a single step) or a large stride (up two steps). How many unique paths are there to reach the Nth stair?" The solution to this problem is the fibonacci series. Now, I don't mean to say that being able to solve this problem means nothing. Being able to answer this is some indication that a programmer is mathy and smart. But the problem can be given away, requires a single leap of insight, and is probably a bad interview question for those reasons. 
If you've done Tetris on your own beforehand the interview will go much better/faster. There are a couple of bits of tricky logic you won't get hung up on (rotations and deleting full lines) and you'll be able to speak much more fluently about the design problems and the trade-offs of each since you're not thinking it through completely on the spot. That is, knowing the problem well I could walk through a complete solution in ~20 minutes. When I solved it as an interviewer it took more like 60 minutes. That seems like it fits your description?

Create a class design to represent a filesystem.

Design an OO representation to model HTML.

Design a web search engine. 

How would you implement the rendering engine of a web browser?

What would be a good data structure for a photo editor? 

Text Editor
- https://stackoverflow.com/questions/32211409/data-structure-to-implement-text-editor
- https://stackoverflow.com/questions/649279/what-is-best-data-structure-suitable-to-implement-editor-like-notepad
- https://stackoverflow.com/questions/2143817/4-program-design-interview-questions



/**

Revision:


https://stackoverflow.com/questions/322715/when-to-use-linkedlist-over-arraylist-in-java
https://stackoverflow.com/questions/6129805/what-is-the-fastest-java-collection-with-the-basic-functionality-of-a-queue
https://thenoisychannel.com/2011/08/08/retiring-a-great-interview-problem https://stackoverflow.com/questions/21273505/memoization-algorithm-time-complexity
- https://web.stanford.edu/class/cs9/
- http://courses.csail.mit.edu/iap/interview/materials.php

 Know everything there is to know about hash tables, from being able to implement one yourself, to knowing about hashing functions, to why the number of buckets should be a prime number. The concepts involved with hash tables are relevant to just about every area of Computer Science.

BST:

- https://www.quora.com/How-do-I-become-capable-of-solving-binary-search-problems-I-can-implement-binary-from-scratch-and-understand-the-concept-but-I-always-fail-to-see-how-I-can-apply-the-technicque-in-competitive-programming-problems/answer/Cosmin-Negruseri?ch=10&share=960ba396&srid=3HW0
- https://gist.github.com/alexbowe/c709c0aa2a9a3c809eedcb6912920c80
- What-are-the-most-common-and-most-important-interview-questions-on-trees-binary-trees-BST-from-data-structures?



A startlingly high percentage of interview questions reduce to breadth-first search or the use of a hash table to count uniques or the use of set to remove duplicates. You need to be able to write a BFS cold, and you need to understand how a hash table is implemented. 

http://bigocheatsheet.com/

For a given abstract data type (e.g. a Queue), they should be able to suggest at least two possible concrete implementations, and explain the performance trade-offs between the two implementations. What you use them for (real-life examples)? Why you prefer them for those examples? the operations they typically provide (e.g. insert, delete, find)
the big-O performance of those operations (e.g. logarithmic, exponential)
how you traverse them to visit all their elements, and what order they're visited in at least one typical implementation for the data structure

What is the difference between an Array and a LinkedList? A Tree and a Graph? When would you use one over the other? How would that impact speed/memory tradeoffs? An interview question doesn't end at a working solution. Be able to explain the runtime of your approach and what sorts of trade offs you could make. For example, "if I cached everything it would take X gigs of RAM but would perform faster because...". Or, "if I kept the binary tree sorted while I performed the operations X would be slower, Y would be faster etc. Basic graph traversal algorithms, tree traversal algorithms, and two good approaches for sorting numbers.

Know your way around major data structures including but not limited to: binary trees, binary search trees, hash tables, heaps, stacks, queues, graphs, lists, tries... as well as all algorithms related to them (insert, delete, search, find, find max, find min...) and the time complexity for each of these, at least at a high level. For graphs you need to know searches (BFS and its properties, DFS and its properties including cycle detection and the like) and shortest path algorithms (Dijkstra, Bellman-Ford, and A*) at a bare minimum

- https://kartikkukreja.wordpress.com/2013/07/31/preparing-for-the-coding-interview/
http://www.ardendertat.com/2012/01/09/programming-interview-questions/
- https://www.reddit.com/r/cscareerquestions/comments/20ahfq/heres_a_pretty_big_list_of_programming_interview/
- https://www.quora.com/What-are-the-standard-puzzles-asked-in-programming-interviews/answer/Michal-Danil%C3%A1k?share=1&srid=3OBi 
- https://www.quora.com/What-are-the-best-programming-interview-questions-youve-ever-asked-or-been-asked
- https://www.reddit.com/r/learnprogramming/comments/xwd16/had_a_technical_phone_interview_today_for_an/
- http://www.java67.com/2018/05/top-75-programming-interview-questions-answers.html?utm_source=quora&utm_medium=referral
https://www.programcreek.com/2012/11/top-10-algorithms-for-coding-interview/

Google papers on GFS and MapReduce (I actually got asked questions which could be solved very easily using them. The interviewers may not allow to use either GFS or MapReduce in your solution, but mentioning how you would solve your problem with them will certainly give you a plus. )

 Head First Design Pattern

 https://www.topcoder.com/community/competitive-programming/tutorials/how-to-find-a-solution/

 https://www.topcoder.com/community/competitive-programming/tutorials/planning-an-approach-to-a-topcoder-problem-part-1/

 https://www.topcoder.com/community/competitive-programming/tutorials/planning-an-approach-to-a-topcoder-problem-part-2/

 https://www.topcoder.com/community/competitive-programming/tutorials/dynamic-programming-from-novice-to-advanced/

 https://www.topcoder.com/community/competitive-programming/tutorials/introduction-to-string-searching-algorithms/


 https://www.topcoder.com/community/competitive-programming/tutorials/computational-complexity-section-1/
 https://www.topcoder.com/community/competitive-programming/tutorials/computational-complexity-section-2/

Deep copy a linked list with both next and random pointers.
Deserialize/serialize binary tree
Trapping rain water/skyline problem (I love this problem, think carefully and you'll realize its actually not THAT bad)
remove invalid parens
minimum window substring (important again, you can use this idea of using a queue to solve some tricky string manipulation questions)
All O'one data structure (Again not THAT hard, but the idea is crucial)
Lots of LC hards are built on top of each other.
Merge interval -> insert interval -> skyline problem -> Drop Squares
Unique paths -> Unique Paths ii -> Dungeon Game
Largest Histogram -> Maximal Rectangle
Word Search -> Word Search ii -> Alien Dictionary
Sit down and try and solve it for 30m. After that, look at the discussion but don't click a solution; the title should give you enough of a hint. If you see "Based on problem X", go do problem X and come back to it. If you don't, keep drilling the discussion until you see anything that gives you a hint towards the solution, and then close the tab. Your goal is to build towards the problems, not memorize the solutions.



---------


Others:
 
- System Design: Design Instant Messaging. Leader Election amongst servers
https://www.educative.io/collection/page/5668639101419520/5649050225344512/5673385510043648?affiliate_id=5082902844932096&gclid=Cj0KCQiAi57gBRDqARIsABhDSMruo-JOcu7HqX5YkfDPMqY8u5m7egvgNyF6Bmk57YuLjcRAxRRkAikaAldLEALw_wcB#utm_source=google&utm_medium=cpc&utm_campaign=grokking-manual

https://leetcode.com/discuss/interview-question/124657/Design-a-distributed-web-crawler-that-will-crawl-all-the-pages-of-wikipedia

https://www.quora.com/What-are-some-of-the-best-answers-to-the-question-How-would-you-design-Twitter-in-a-system-design-interview

- Unix: More specifically, linux troubleshooting and debugging. Understanding things like memory, io, cpu, shell, memory etc. would be pretty helpful. Knowing how to actually write a unix shell would also be a good idea. What tools might you use to debug something? On another note, this interview will likely push your boundaries of what you know (and how to implement it).

Sockets:
http://web.stanford.edu/class/archive/cs/cs108/cs108.1092/handouts/33Sockets.pdf


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
https://www.topcoder.com/community/competitive-programming/tutorials/a-bit-of-fun-fun-with-bits/

Networking

192.168.2.2 (My machine)
192.168.1.1 (Router IP address)
150.242.174.47 (my public IP address)
Web server default port: 80
VNC(Remote control): 5900
Wiki: 2500
FTP: 21
Bittorent: 6881-6990


Java I/O: https://www.cs.cmu.edu/~adamchik/15-121/lectures/IO%20Framework/io.html

JAVASCRIPT/BACKEND DEVELOPER INTERVIEW

Microservices:

https://www.sequoiacap.com/article/build-us-microservices/
https://news.ycombinator.com/item?id=19239247
https://news.ycombinator.com/item?id=19321070

BACKEND OBJECT-ORIENTED DESIGN/ DATABASE SCHEMA PREPARATION

http://www.sarahmei.com/blog/2013/11/11/why-you-should-never-use-mongodb/
https://stackoverflow.com/questions/24096546/mongoose-populate-vs-object-nesting
https://www.mongodb.com/blog/post/6-rules-of-thumb-for-mongodb-schema-design-part-3?_ga=2.207890002.1365381269.1528077157-349313011.1525218488
https://www.mongodb.com/blog/post/the-mean-stack-mistakes-youre-probably-making
https://www.mongodb.com/presentations/webinar-working-with-graph-data-in-mongodb
https://www.compose.com/articles/graph-data-with-mongodb/

Graph DB:
https://medium.com/labcodes/graph-databases-talking-about-your-data-relationships-with-python-b438c689dc89
https://news.ycombinator.com/item?id=18795498

JavaScript: Event Loop and stuff

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


-----

Algorithms and Data Structures:

- Dijkstras in Disguise. Chapter 24 CLRS. https://news.ycombinator.com/item?id=17745779
- A* Star https://www.redblobgames.com/pathfinding/a-star/introduction.html
- Prefix Neighbour: https://gist.github.com/jianminchen/6652256213dd5924c033536822133909
- Revise MST in Directed Graphs. Chapter 23 CLRS.  
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
- Skip Lists, Fenway Trees, Bloom Filters
- https://stackoverflow.com/questions/24101719/understanding-a-five-dimensional-dp-with-bitshifts-and-xors
- Lucy and the Flowers - Problem from December long contest, Try to solve it with suffix arrays. You can only if you understand suffix arrays and LCP completely. https://www.codechef.com/DEC13/problems/DECORATE
I was able to solve a not-so-obvious medium level Max-Flow problem at ACM KGP Onsite only because I completely understood how the algorithm works. It was at 4 hour 25 minutes I got 5th problem accepted, then I read this problem and got it accepted 4 minutes before end. Learning the algorithm helped. Dot.
----


To Ask in Interview:

- I could define new abstractions (work on a higher level vs low level)
Pro: 
would make the code more readable
safer form bugs and easier to debug
Con: 
Overhead e.g. Overiding equals and hashCode if they are to be used in a collection
More memory usage in the heap space allocated to objects (as opposed to pointers to existing concepts)
Possibly more computation in creating those objects.

- I could validate arguments or leave it as //Todo

- Other OOD best practices: make classes and objects immutable, field variables private etc.


Common Problem Solving Tricks:

- To avoid boundary on grid: Assume the grid is surrounded by inactive cells (sentinel pattern, example: also used in merging two arrays, by putting a sentinel = Integer.MAXInt, to avoid checking boundary coundition)
- To convert int to char, do:  'n' = (char) (n + 48). In ASCII, every char have their own number. And char '0' is 48 for decimal, '1' is 49, and so on.
- Use "ABCDEFGHIK...".charAt(i) pattern to quickly map int to chars.
- Use [] wrapper if you want to modify a primitive passed to a function.
- Whenevr a problem involves search, consider sorting + binary search.


Best Practices:

Graphs: For most applications, isAdjacent() is enough to model a graph. 
Fail Fast: Validate arguments
Use Enums when arguments can only take a finite number of values (self-documentation, prevents errors from invalid arguments)
Implement a toString method for your classes for ease of debugging
Check invalid states via assert statements. 

Common Source of Bugs:

- Using equals method to compare StringBuilders. StringBuilders is a mutable object which inherits equals from Object, and thus compares for observational equality(memory references) and not behavior equality.

- Not treating primitive wrapper classes like Boolean, Integer etc. as immutable. (e.g. it would be a mistake to pass them as a parameter and expect to be modified in a function, like an accumulator variable is)

- Equals() and HashCode() must be overriden for immutable custom classes, otherwise they inherit these methods from Object class. 

- TreeSet and TreeMap uses compareTo method to differentiate elements, whereas HashSet and HashMap uses equals. By default, compareTo uses equals. This can result in subtle bugs: if you expect two custom different objects to return true on equals but count as different in a TreeSet, you must override compareTo. e.g comparing Poker Hands: you might want the value of same numberd cards of different suits to return true on compareTo, but should return false on equals and counted as different. If you don't override compareTo, this can result in a bug.

-  For mutable objects, it is okay - better not to override the object equals (e.g. StriingBuilder). When equals() and hashCode() can be affected by mutation, we can break the rep invariant of a hash table that uses that object as a key.

- Before we pass a mutable collection to another part of our program, we can wrap it in an unmodifiable wrapper. We should be careful at that point to forget our reference to the mutable collection, lest we accidentally mutate it. (One way to do that is to let it go out of scope.) Just as a mutable object behind a final reference can be mutated, the mutable collection inside an unmodifiable wrapper can still be modified by someone with a reference to it, defeating the wrapper.

- HashMap keys and values have to be casted propely before being used as primitive types.
Semicolons.

- int overflow: especially in functions where n grows largely, like fibonacci or any combinatorial explosion. https://stackoverflow.com/questions/21070506/reverse-integer-leetcode-how-to-handle-overflow 
Also in general, doing stuff like int mid = (low + high) / 2 and not taking into account integer overflow can lead to a bug
https://ai.googleblog.com/2006/06/extra-extra-read-all-about-it-nearly.html. Fix the bug by this: int mid = low + ((high - low) / 2); (. It is not sufficient merely to prove a program correct; you have to test it too.)

- Violation of preconditions that the compiler cannot check. (e.g. an interface is supposed to be immutable, but some iimplementatiin adds a mutator method. Or merge method: inputs are supposed to be sorted, but a recursive call doesnt do it.) compiler cannot check that we haven’t weakened the specification in other ways: strengthening the precondition on some inputs to a method, weakening a postcondition, weakening a guarantee that the interface abstract type advertises to clients.  If you declare a subtype in Java — implementing an interface is our current focus — then you must ensure that the subtype’s spec is at least as strong as the supertype’s. Classic example: no correct way for MutableSquare to implement MutableRectangle.setSize(..)and mutable square is not a subtype of mutable rectangle.


Questions

Question: When to use <E> Set<E> f() patternin signature? When to use <? supertype somthing>
Why use private constructors?
computeIfAbsent
public V computeIfAbsent(K key,
                         Function<? super K,? extends V> mappingFunction)
 

Tips

Use putIfAbsent, computeIfAbsent(instead of running a for loop, checking containsKey, then doing).  putIfAbsent removes the imperative way of having to define the if-statement https://stackoverflow.com/questions/48183999/what-is-the-difference-between-putifabsent-and-computeifabsent-in-java-8-map

//With Java 8 lambdas it's supper easy to return first argument, even for specific class, i.e. when(foo(any()).then(i -> i.getArgumentAt(0, Bar.class)). And you can just as well use a method reference and call real method.

Patterns:
- while(true) with return  vs while with terminal conditions
- do something. while(...){do tsomrthing} (Do something atleast once) vs do (//do something) while{} 
- if (x){return;}
  if (y){return;}
	//do something
vs if (!x && !y){
	//do something
}

- if-then-elseif-then-elseif vs switch statement.


- label1: 
for (int i = 0;;) {
    for (int g = 0;;) {
      break label1;
    }
}
Wrap in a function and return instead



