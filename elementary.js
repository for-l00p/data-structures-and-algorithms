

--------






---------



Platonia:


- Test conversation APIs
- Make APIs: updateSuperpower, updateTags, mergeTags, deleteTag

- Remove duplicate superpowers
- Remove tag entries which arent proper tag words: \m/, 😈, .., working professionally
- Merge two tag documents into one tag: possibly into tag synonyms. 

----




-----





- HashTables in Sedgewick and CLRS. https://en.wikibooks.org/wiki/Data_Structures/Hash_Tables
/**
 * 

Hash Table’

https://courses.csail.mit.edu/6.006/fall11/lectures/lecture10.pdf
https://en.wikibooks.org/wiki/Data_Structures/Hash_Tables

Hash functions are used to implement the abstract data type dictionaries. Dictionaries require the operation of search, insert and delete.  It can be implemented via 
a linked list (O(n) search, O(1) insert, and O(n) delete) . Application: log files (frequent insertions, rare search and deletions). 
unordered array: O(n) search average and worst, 
Ordered sequence with a direct access mechanism (e.g ordered array)  O(logn) search, O(n) insert, O(n) delete. We’ll have to shift all the elements once we insert or delete in the right position.  Application: lookup table (frequent searches, rare insertions and deletions)
Direct access: Array indexed by keys O(1) search, insert and delta. But terrible space properties. O(r), where r is the range. 
BST has O(Log n) search time

To decide which data structure to use, we need to see which operations out of search, insert and delete are used frequently.

Hash tables are a compromise between fast search and less memory O(n+m) space. 

Simple uniform hashing yields an average list length = a = load factor = n/m. Search time: O(1 + a). Inserting is the same, because we have to search first. deletion is same when the list is doubly linked.

The efficiency of this data structure depends critically on the hash function chosen. And picking a hash function is more like an art than a science: there are principles we can follow to pick a good hash function, but there is no optimal hash function.


Hash tables need to be able to handle collisions: when the hash function maps two different keys to the same bucket of the array. The two most widespread approaches to this problem are separate chaining and open addressing.[1][2][4][9] In separate chaining, the array does not store the value itself but stores a pointer to another container, usually an association list, that stores all of the values matching the hash. On the other hand, in open addressing, if a hash collision is found, then the table seeks an empty spot in an array to store the value in a deterministic manner, usually by looking at the next immediate position in the array.
￼

This graph compares the average number of cache misses required to look up elements in tables with separate chaining and open addressing.
Open addressing has a lower cache miss ratio than separate chaining when the table is mostly empty. However, as the table becomes filled with more elements, open addressing's performance degrades exponentially. Additionally, separate chaining uses less memory in most cases, unless the entries are very small (less than four times the size of a pointer

S.No.	Seperate Chaining	Open Addressing
1.	Chaining is Simpler to implement.	Open Addressing requires more computation.
2.	In chaining, Hash table never fills up, we can always add more elements to chain.	In open addressing, table may become full.
3.	Chaining is Less sensitive to the hash function or load factors.	Open addressing requires extra care for to avoid clustering and load factor.
4.	Chaining is mostly used when it is unknown how many and how frequently keys may be inserted or deleted.	Open addressing is used when the frequency and number of keys is known.
5.	Cache performance of chaining is not good as keys are stored using linked list.	Open addressing provides better cache performance as everything is stored in the same table.
6.	Wastage of Space (Some Parts of hash table in chaining are never used).	In Open addressing, a slot can be used even if an input doesn’t map to it.
7.	Chaining uses extra space for links.	No links in Open addressing

https://stackoverflow.com/questions/49709873/cache-performance-in-hash-tables-with-chaining-vs-open-addressing
 * @type {[type]}
 */



- BFS and DFS Graph: Solve 22.1, 22.2 in CLRS and Undirected Graph Sedgewick. Learn this BFS Technique: https://icpcarchive.ecs.baylor.edu/index.php?Itemid=8&category=343&option=com_onlinejudge&page=show_problem&problem=2738

https://codereview.stackexchange.com/questions/192694/graph-implementation-in-java-using-adjacency-list-v2

- DFS Directed: Solve 22.3, 22.4, 22.5 in CLRS and Sedgewick. and Sedgewick Directed Graphs. Chapter.  Topological Sort. Is Directed Graph Acyclic? Stack implementation of DFS. Is Graph Planar? http://www.bowdoin.edu/~ltoma/teaching/cs231/fall09/Homeworks/old/rest/H10-sol.pdf. https://cs.stackexchange.com/questions/194/the-time-complexity-of-finding-the-diameter-of-a-graph
https://www.geeksforgeeks.org/bipartite-graph/
http://shlegeris.com/2016/07/02/graph


---------

//Bottom-up DP is equivalent to getting the directed acyclic graph formed by the DFS, running a topological sort on it, and computing the recurrence on each node in order without actually recursing. The key observation is that the topological sort ensures that each time you solve the recurrence for a state, the solutions of the 'children' states is already computed due to the topological ordering. In practice you rarely run an explicit topological sort and instead exploit some inherent topological ordering in the problem.

- Divide and Conquer (Revise MergeSort,  Order statistics by quicksort: median in O(n))
- Greedy Algorithms (Huffman Code Algorithm through Priority Queue)
https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
http://www.ccs.neu.edu/home/vkp/2510-sp11/Labs/Lab11/lab11.pdf
https://math.stackexchange.com/questions/62282/do-dynamic-programming-and-greedy-algorithms-solve-the-same-type-of-problems?rq=1


- CLRS: Binary Search Trees. Tree Sort https://stackoverflow.com/questions/5502916/explain-morris-inorder-tree-traversal-without-using-stacks-or-recursion
http://plasmasturm.org/log/453/
https://stackoverflow.com/questions/5278580/non-recursive-depth-first-search-algorithm
https://www.geeksforgeeks.org/?p=6358
https://www.hackerearth.com/practice/notes/iterative-tree-traversals/
https://www.quora.com/What-is-a-good-way-to-implement-stackless-recursion-less-post-order-traversal-for-a-non-threaded-binary-tree-using-Morris-method
https://stackoverflow.com/questions/5502916/explain-morris-inorder-tree-traversal-without-using-stacks-or-recursion?rq=1
- Sedgewick: Binary Search Trees


- BST Practice Problems (Timed Trial): https://www.quora.com/What-are-the-most-common-and-most-important-interview-questions-on-trees-binary-trees-BST-from-data-structures
- All Dynamic Programming Bookmarks 
- CLRS Dynamic Programming Exercises (Timed Trial)
- Sedgewick: Recursion Problems (Timed Trial) https://introcs.cs.princeton.edu/java/23recursion/
- Common Interview Problems (Timed Trial): https://www.quora.com/What-are-the-standard-puzzles-asked-in-programming-interviews/answer/Michal-Danil%C3%A1k?share=1&srid=3OBi 
 //Most algorithm questions in interviews involve manipulating one or two dimensional arrays of integers. Practice the shit out of this type of question on interview prep websites. Be sure you get the difference between an index and a count in a zero-based array.
- Elements of Programming Interviews or Cracking The Coding Interview


------

Extras:

- Design a Poker Game. 
- Design Instant Messaging. Design Text Editor. Leader Election amongst servers. Design a web search engine. Design a url shortener. Design Twitter. 
https://www.educative.io/collection/page/5668639101419520/5649050225344512/5673385510043648?affiliate_id=5082902844932096&gclid=Cj0KCQiAi57gBRDqARIsABhDSMruo-JOcu7HqX5YkfDPMqY8u5m7egvgNyF6Bmk57YuLjcRAxRRkAikaAldLEALw_wcB#utm_source=google&utm_medium=cpc&utm_campaign=grokking-manual
https://www.quora.com/What-are-some-of-the-best-answers-to-the-question-How-would-you-design-Twitter-in-a-system-design-interview
- Regular Expressions http://web.mit.edu/6.005/www/fa14/classes/12-regex-grammars/
- Design Patterns (Builder, Decorator, Chain of Responsibility, Facade, Adapter etc.)
- Prefix Neighbour: https://gist.github.com/jianminchen/6652256213dd5924c033536822133909
- Revise MST in Directed Graphs. Chapter 23 CLRS.  
- Dijkstras in Disguise. Chapter 24 CLRS. https://news.ycombinator.com/item?id=17745779
- Read about permutations. https://www.topcoder.com/blog/generating-permutations/
- Read about faster exponentiation https://eli.thegreenplace.net/2009/03/21/efficient-integer-exponentiation-algorithms
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
- Strategy Games Programming
- Branch and Bound