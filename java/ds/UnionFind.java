
/*
https://algs4.cs.princeton.edu/15uf/
https://www.hackerearth.com/practice/data-structures/disjoint-data-strutures/basics-of-disjoint-data-structures/tutorial/

 */

public class UnionFind{

	private int[] size;
	private int[] parent;
	private int count;


	public UnionFind(int N){

		size = new int[N];
		parent = new int[N];
		count = N;
		for(int i = 0; i < N; i++){
			parent[i] = i;
			size[i] = 1;
		}	 
	}


	public int find(int j){

		// Running time: O(depth of node in the tree). The number of array accesses used by find() in quick-union is 1 plus twice the depth of the node corresponding to the given site.    With path compression,  the amortized cost per operation for this algorithm (p) is known to be logarithmic. With union by rank invariant, the depth is O(logn).

		validateIndex(j);
		int root = j;
		while(parent[j] != root){
			root = parent[root];
		}

		int current = j;
		while(current != root){
			int temp = parent[current];
			parent[current] = root;
			current = temp;
		}
		//System.out.println("Result of find on " + j + " is " + root);
		return root;

	}

//The number of array accesses used by union() and connected() is the cost of the two find() operations (plus 1 for union() if the given sites are in different trees).
//
//Proof of why union by height will work: Prove that the height strategy will lead to a tree wih atleast 2^h nodes. A union operation between elements in different trees either leaves the height unchanged (if the two tree have different heights) or increase the height Noteby one (if the two tree are the same height). You can prove by induction that that the size of the tree is at least 2^height. Therefore, the height can increase at most lg N times. 
	public void union(int i, int j){
		//System.out.println("Called union on "  + i+ " and " + j);
		int firstRoot = find(i);
		int secondRoot = find(j);
		if(firstRoot == secondRoot) return;
		// suppose we set parent[root(i)] to j instead of parent[root(j)]. Would the resulting algorithm be correct? Answer. Yes. However, it would be increase the tree height, so the performance guarantee would be invalid.
		if(firstRoot != secondRoot){
			if(size[firstRoot] <= size[secondRoot]){
				parent[firstRoot] = secondRoot;
				//System.out.println("Assigning parent of "  + firstRoot + " to " + secondRoot);
				size[secondRoot] = size[secondRoot] + size[firstRoot];
			} else {
				parent[secondRoot] = firstRoot;
				//System.out.println("Assigning parent of "  + secondRoot + " to " + firstRoot);
				size[firstRoot] = size[secondRoot] + size[firstRoot];
			}
			count--;
		} 

	}

	public int count(){

		return this.count;

	}

	public boolean connected(int i, int j){

		validateIndex(i);
		validateIndex(j);

		return (find(i) == find(j)) ;

	}

	public void validateIndex(int index){

		if(index < 0 || index > parent.length) {
			throw new IllegalArgumentException("index out of bounds: " + index);
		}

	}


	public static void main(String[] args){

		UnionFind test = new UnionFind(10);
		test.union(0,1);
		test.union(1,2);
		test.find(0);
		test.find(2);
		test.union(0,2);
		System.out.println(test.connected(0,2));

	}




}