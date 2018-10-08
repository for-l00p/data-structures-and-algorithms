/**
 * Heaps are an implementation of ADT called Priority Queues. Priority Queues need to support the following operations:
 * - insert
 * - delete-min
 * - min
 *
 * Priority Queues require a total order on the objects in the data type, implemented by a Comparator object called Comparator  external to the objects being stored. 
 * Applications:
 * - job scheduling in Unix using shared computer resources
 * - 

 * Implemented with unsorted sequence: insert takes O(1), delete-min and min take O(n)
 * Implemented with sorted sequence: delete-min and min take O(1) and insert takes O(n)
 * Implemented with binary search tree: all operations take O(logn)
 * Implemented with Binary Heap: insert O(logn), delete in O(1) and delete-min in O(logn). 
 *
 * If the setting is such that there are very faew insertions but a lot of findMin operations, it might make sense to use a sorted sequence.
 * 
 * 
 * 
 *  Binary Heaps: Binary Trees with store priorities of elements with two additional properties:
 * We add two properties:
 *
 * 1. All levels except the last level are full. Last level is left-filled. 
 * 2. Priority of node is atleast as large as its parent. 
 *
 *
 * Motivation: 
 * 
 * Can we do better than BST (where all operations take O(logn)? The Binary Search Property is introduced to optimize search. This property introduces more costs on other operations, e.g. if we were free of this constraint, we could implement insert in O(1). 
 *
 * We implement insert in O(1) by left-filling progressively. The corresponding property is:
 * 
 * 1. All levels except the last level are full. Last level is left-filled. 
 * 
 * Implemented with Binary tree where only last level is being filled: insert O(1). deletemin and min(): O(n).
 *
 * But now deletemin and min() are worse than in BST. Can we introduce another constraint which improves min() and deletemin() without worsening insert?
 *
 * We store min() in root (recusrively).
 * This gives the following property:
 * 2. Priority of node is atleast as large as its parent. 
 * 
 * So min takes O(1). deletemin takes O(logn) because deleting changes the heap property.   What happens to insert? Simple algorithm of inserting at bottom and progressively replacing with parent gives insert in O(logn). 
 *
 * So now we have: insert O(logn), delete and delete-min in O(1). 
 * 
 * Height of a Binary Heap with nodes n and height h:
 *
 * Such a heap has more nodes than a complete binary tree of height h-1 and less nodes than those of height h.
 * So 2^h-1 < n < 2^(h+1) - 1  
 *
 * ------
 * Insertion: Can bubble up the new node. (Or we can directly find the position, insert there, and push all descendents down -  argue correctness)
 * Can implement with  arrays. 
 * Heapify: Assume both subtrees are a heap, and node i violates heap property. successively replace with the lesser sibling. Traces a path down - argue correctneess. 
 *
 * Building a heap: we could insert successively, resulting in log1 + log2 + ...logn running time = O(n!) = O(nlogn)
 *  HeapSort: creat a heap, successively deleteMin and put it at the very end. 
 */