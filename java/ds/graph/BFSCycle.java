import java.util.*;

/**
 * A BFS could be reasonable if the graph is undirected (be my guest at showing an efficient algorithm using BFS that would report the cycles in a directed graph!), where each "cross edge" defines a cycle. If the cross edge is {v1, v2}, and the root (in the BFS tree) that contains those nodes is r, then the cycle is r ~ v1 - v2 ~ r (~ is a path, - a single edge), which can be reported almost as easily as in DFS.

The only reason to use a BFS would be if you know your (undirected) graph is going to have long paths and small path cover (in other words, deep and narrow). In that case, BFS would require proportionally less memory for its queue than DFS' stack (both still linear of course).

In all other cases, DFS is clearly the winner. It works on both directed and undirected graphs, and it is trivial to report the cycles - just concat any back edge to the path from the ancestor to the descendant, and you get the cycle. All in all, much better and practical than BFS for this problem.



 */
public class BFSCycle{
	
	private boolean[] visited;
	private int[] parent;
	int[] distance;
	private Map<Integer, Stack<Integer>> cycles;
	private boolean hasCycle;
	private int count;
	private int[] cc;
	private int cycleCount;



	public BFSCycle(IntGraph graph){

		int n = graph.vertexCount();
		visited = new boolean[n];
		parent = new int[n];
		distance = new int[n];
		cc = new int[n];
		hasCycle = false;
		count = 0;
		cycleCount = 0;
		cycles = new HashMap<Integer, Stack<Integer>>();

		for(int i = 0; i< n; i++){
			visited[i] = false;
		}

		for(int i = 0; i < n; i++){
			if(visited[i] == false){
				count++;
				cc[i] = count;
				traverseBFS(graph,i);
			}
			
		}
	}

	private void traverseBFS(IntGraph graph, int source){

		distance[source] = 0;
		Queue<Integer> bfsQueue = new LinkedList<>();
		bfsQueue.add(source);
		visited[source] = true;
		int current;
		while(!bfsQueue.isEmpty()){
			current = bfsQueue.poll();
			System.out.println("Now visiting neighbours of: " + current);
			for(int neighbour: graph.getNeighbours(current)){
				if(visited[neighbour] == false){
					parent[neighbour] = current;
					System.out.println("adding to queue: " + neighbour);
					bfsQueue.add(neighbour);
					visited[neighbour] = true;
					cc[neighbour] = count;
					distance[neighbour] = distance[current] + 1;
				} else {
					if(neighbour != parent[current]){
						System.out.println("Cycle edge detected: " + neighbour + " -> " + current);
						hasCycle = true;
						cycleCount++;
						Stack<Integer> thisCycle = new Stack<Integer>();
						int temp = current;
						while(temp != source){
							System.out.println("Pushing on stack: " + temp);
							thisCycle.push(temp);
							temp = parent[temp];
						}
						System.out.println("Pushing on stack: " + temp);
						thisCycle.push(temp);
						Stack<Integer> reverse = new Stack<Integer>();
						temp = neighbour;
						while(temp != source){
							System.out.println("Pushing on reverse stack: " + temp);
							reverse.push(temp);
							temp = parent[temp];
						}

						while(!reverse.isEmpty()){
							thisCycle.push(reverse.pop());
						}
						System.out.println("Pushing cycle no.: " + cycleCount);
						
						cycles.put(cycleCount, thisCycle);
					}
				}
			}
		}
	}


	public boolean hasCycle(){
		return hasCycle;
	}

	public void printCycles(){
		for(Map.Entry<Integer, Stack<Integer>> item : cycles.entrySet()){
			System.out.println("Cycle Number: " + item.getKey());
			Stack<Integer> thisCycle = item.getValue();
			StringBuilder s = new StringBuilder();
			for(Integer v: thisCycle){
				s.append(v + "-->");
			}

			System.out.println(s);
		}
	}

		public static void main(String[] args){
		IntGraph test = new IntGraph(11);
		test.addEdge(0,1);
		test.addEdge(2,3);
		test.addEdge(0,2);
		test.addEdge(0,4);
		test.addEdge(4,5);
		test.addEdge(0,9);
		test.addEdge(1,6);
		test.addEdge(9,8);
		test.addEdge(7,3);
		test.addEdge(7,8);
		test.addEdge(8,3);
		test.addEdge(7, 10);
		//test.addEdge(3, 3);

		System.out.println(test.toString());
		BFSCycle findCycle = new BFSCycle(test);
		System.out.println(findCycle.hasCycle());
		System.out.println(Arrays.toString(findCycle.distance));
		System.out.println(Arrays.toString(findCycle.parent));
		System.out.println(Arrays.toString(findCycle.visited));
		System.out.println(Arrays.toString(findCycle.cc));
		System.out.println(findCycle.count);
		findCycle.printCycles();

	}



	







}