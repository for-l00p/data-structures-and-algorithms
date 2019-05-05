import java.util.*;

public class GraphSearch {

	/**
	 *
	 *
	 * Iterative DFS is tricky: 
	 * There are three approaches:
	 * 1. Emulating Recursion exactly with the help of iterators, not pushing all neighbours at once. Thiis preserves order.
	 * 2. Pushing allneighbours at once: possble duplicates on stack when encountering cycle, so essential checking and marking on popping: O(n+m) space needed. This reverses order from recursiive dfs if nodes are pushed on stack in order of adj list.
	 * 3. Pushing allneighbours at once, but Deleting duplicate from stack when encounteriing a cycle. If you use customized indexed stack, you can delete in O(1).
	 *
	 * If you want to make a DFS Iterator: then you have to clear up the stack (backtrack etc.) and prepare for the next() in advance: because hasNext() depends on stack state, and a simple implementation of hasNext is to just check is stack is empty or not. When next() is called, just return the topmost node on the stack, and then do all the processing. 


	 * There's a buggy approach: marking a node before pushing it on stack. See Bug: https://stackoverflow.com/questions/25990706/breadth-first-search-the-timing-of-checking-visitation-status or Sedgewick exercics
	 * 
	 * Strategy:  (1) Only push unvisited on the stack, (2) Mark visited before pushing on the stack. and  (3) no checking after popping (4) no marking  after popping
	 * Bug: When encountering a node marked as visited, the node might be one from a different branch. If the DFS does not push the node again, this will amount to it backtracking. This is not DFS: DFS moves forward when encountering a cycle.
	 * Resolution Suggestion: Flip(1): Push visited on the stack as well. This would require us to flip (2), (3) (4) as well: If we continue marking before pushing, then every popped would have been visited. If we process all visiited, then an infinite loop results on encountering a cycle. If we process only unvisited, then we do not process any. So (4) flips. No we push all and do not mark. Now all on stack are unvisited, and we only need to proccess visited - else infinite loop. so we need to flp (3) and (4)  If we  introduce a check on popping: else if push the neighbours of a node in the cycle again and again and run into an infiniite loop.
	 * Final: Flip all: Check after popping, if unvisited, mark, Push all on stack.
	 * Would this work? To be checked, but this introduces duplicate (visited) nodes in the stack: auxillary space requirements increases from O(V) to O(V+E)?
	 *
	 *Resolution Suggestion 2:  
	 *
	 * Flip (2). Do not Mark visited before pushing on the stack. This would require us flipping (3), (4) as well: else this will happen: we push an invisited node. We encounter it again as port of a cycle. Since it is unvisited, we push it again. Since no check on popping, we would end up proessiing it twice. If we introduce a check, but no marking, even then we would end up processiing it twiice. So final strategy:
	 * Check and mark on pop, push only unvisited, and do not mark before.
	 * * Would this work? To be checked, but this introduces duplicate (unvisited nodes)nodes in the stack: auxillary space requirements increases from O(V) to O(V+E)?
	 * 
	 * So we NEED to check and mark after popping and not mark before pushing. The only difference is if we push all, or push only unvisited. Both introduce duplicate items: the former visited and unvisited both, the latter unvisited only.
	 *
	 * An elegant alternative is this: instead of pushing neighbours all at once, we push only 1 at a time. So when a node encounters a cycle, the node has not already been pushed. Only when branches are thoroughly explored do we push the nodes of other branches. This preserves the order of DFS: the result of DFS Iterative is the same as DFS recursiive
	 *
	 * A third alternative is that we maintan the state of 
	 *
	 * @param g [description]
	 * @param s [description]
	 */



	//Bug: https://stackoverflow.com/questions/25990706/breadth-first-search-the-timing-of-checking-visitation-status
	public static void dfsIterativeWithSubtleBug(IntGraph g, int s){

		int[] visited = new int[g.numVertices];
		Stack<Integer> stack = new Stack<>();
		visited[s] = 1;
		stack.push(s);

		int time = 0;

		while(!stack.isEmpty()){
			int current = stack.pop();	
			time++;
			System.out.println("Visited " +  current + " at time " + time);
			for (Integer n: g.adj(current)){
				if(visited[n] == 0){
					visited[n] = 1;
					stack.push(n);
				}
				
			}
			if (stack.isEmpty()){
				for (int i = 0; i < visited.length; i++){
					if(visited[i] == 0){
						stack.push(i);
						visited[i] = 1;
						break;
					}
				}	
			}	
		}

	}

	//Propah emulation of recrursion calls: O(V) memory
	 public static void dfsIterative(IntGraph g, int s){
	 	int n = g.numVertices();
		int[] visited = new int[n];
		List<Iterator<Integer>> adjIterators = new ArrayList<>();
		for (int i = 0; i < n; i++){
			adjIterators.add(g.adj(i).iterator());
		}
		Stack<Integer> stack = new Stack<>();
		Iterator<Integer> it;

		for (int i = 0; i < n; i++){
			int source = (i + s) % n;
			if(visited[source] == 0){
				stack.push(source);
				visited[source] = 1;
			}

			while(!stack.isEmpty()){
				int current = stack.peek();
				if (visited[current] == 1){
					visited[current] = 2;
					System.out.println("Visiting:" + current);
				}	
				it = adjIterators.get(current);
				if (it.hasNext()){
					int neighbour = it.next();
					if (visited[neighbour] == 0){
						visited[neighbour] = 1;
						stack.push(neighbour);
					}
				} else {
					stack.pop();
				}
			}
		
		
		}

	}

//Pushes duplicates when encountering a cycle: O(V+E)
 public static void dfsIterative2(IntGraph g, int s){

		int[] visited = new int[g.numVertices];
		Stack<Integer> stack = new Stack<>();
		stack.push(s);

		while(!stack.isEmpty()){
			int current = stack.pop();
			if(visited[current] == 0){
				visited[current] = 1;
				System.out.println("Visited " +  current);
				for (Integer n: g.adj(current)){
					if(visited[n] == 0){
						stack.push(n);
					}
					
				}
				
			}
			if (stack.isEmpty()){
				for (int i = 0; i < visited.length; i++){
					if(visited[i] == 0){
						stack.push(i);
						break;
					}
				}	
			}	
		}

	}




	public static void bfsIterative2(IntGraph g, int s){


		int n = g.numVertices();
		int[] visited = new int[n];
		Queue<Integer> bfsQueue = new LinkedList<>();

		for (int i = 0; i < n; i++){
			int source = (s + i) % n;
			if (visited[source] == 0){
				bfsQueue.add(source);
				visited[source] = 1;
				while(!bfsQueue.isEmpty()){
					int current = bfsQueue.poll();
					for(int neighbour: g.adj(current)){
						if (visited[neighbour] == 0){
							visited[neighbour] = 1;
							bfsQueue.add(neighbour);
						}
					}
				System.out.println("Visited " +  current);
				}
			
			}

		}

		
	}

	//Caution: dont allow this in iterator, because this allows unvisited duplicates in queue, so, it can happen that the queue is not empty but the bfs is finished. 
	public static void bfsIterative(IntGraph g, int s){

		int n = g.numVertices();
		int[] visited = new int[n];
		Queue<Integer> bfsQueue = new LinkedList<>();
		bfsQueue.add(s);
		while(!bfsQueue.isEmpty()){
			int current = bfsQueue.poll();
			if (visited[current] == 0){
				visited[current] = 1;
				System.out.println("Visited " +  current);
			}
			
			for(int neighbour: g.adj(current)){
				if (visited[neighbour] == 0){
					bfsQueue.add(neighbour);
				}
				
			}

			if (bfsQueue.isEmpty()){
				for (int i = 0; i < n; i++){
					if(visited[i] == 0){
						bfsQueue.add(i);
						break;
					}
				}
			}

		}

	}

	



}