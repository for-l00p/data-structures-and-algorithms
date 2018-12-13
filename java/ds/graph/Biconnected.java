import java.util.*;

/**
 * Suppose a graph has a bridge edge. Then consider one of its end points. By removing that vertex, we are also removing that edge and hence disconnecting the graph. So a graph has a bridge edge implies it has an articulation point.

Note that this proof has a minor flaw. It does not consider the case when one of the connected components obtained after removing the edge has a single vertex. So the above statement fails when the graph is just two vertices and an edge between them (or if the graph is disconnected, this is one connected component of it). This is in fact the only case where the above statement is false.

And we can see by simple examples that the converse, graph has articulation point implies it has bridge edge, is not true.


Consider a vertex v and its children. 

If any one of the subtrees rooted at its children do not have a backedge to a proper ancestor of v, then v is an articulation point (Except the special case of root, which does not have a special ancestor. A root is an articulation point iff it has more than one children.). Each such subtree corresponds to a biconnected component which includes the edge from v-> subtree (In the case of root, each of its children form a separate biconnected compoenent) If all the vertices have a backedge to a proper ancestor of v, then v is not an articulation point (Or if v is a root with one child). In this case there is only one biconnected compoenent which has an edge containing v.


If any one of the subtrees rooted at its children have a backedge to a proper ancestor of v, then the edge parent[v] -> v in a DFS tree cannot be a bridge. If all the subtrees rooted at its children do not have a backedge to a proper ancestor of v, AND v does not have a backedge to another ancester, then the  edge parent[v] -> v is a bridge (for the case of root there is no such thing as backedge to ancester, or ancestor or parent[v])

(A bridge of G is an edge whose removal disconnects G. An edge is a bridge iff it does not lie on any simple cycle of G. If any of a subtrees of children have an edge to a proper ancestor of v, then the edge incident on v lies on a simple cycle and cannot be a bridge. If no such backedges to proper ancestors exist, then the edge incident on v cannot lie on a simple cycle.) 

The biconnected components of G partition the non-bridge edges of G. 
If we want to print biconnected components, we can do so by maintaining a stack of edges as we traverse the DFS. We are only able to tell a biconneccted component as we track back (post order):  because only after having completely searched a subtree we are able to tell that it does not have a backedge. When we have done this, we remove the edges from biconnected component from the stack (alternatively, if we are not maintaining a stack, we could just delete the biconnected component from the graph. )

https://en.wikipedia.org/wiki/Biconnected_component#Linear_time_depth_first_search

 */

public class Biconnected{

	private boolean[] articulation;
	private int[] arrival; // We use arrival array to calculate earliestLink values. We could, instead, use a depth[v] or numberVerticesBeforeinDFS[v] for earliestLink values. depth[v] would work because there are no cross edges, so all the backedges would have lower depth. 
	private int[] parent; //  We use the parent array to glean information about visited or unvisited. If visited[v] == true <-> parent[v] != -1;
	private int[] earliestLink; // Because we do not need the earliestLink information of a subtree after we are done with its postorder processing, instead of storing these values in an array we can have our recursive dfs function have a return value. This would save O(V) space. 
	private int time;
	private Stack<Edge<Integer>> edgeStack;
	private Stack<Edge<Integer>> bridges;
	private int biCount;



	public Biconnected(Graph<Integer, ?> graph ){
		int n = graph.vertexCount();
		articulation = new boolean[n];
		arrival = new int[n];
		parent = new int[n];
		earliestLink = new int[n];
		edgeStack = new Stack<Edge<Integer>>();
		bridges = new Stack<Edge<Integer>>();
		biCount = 0;

		time = 0;
		for(int i = 0; i < n; i++){
			arrival[i] = -1;
		}

		for(int i = 0; i < n; i++){
			parent[i] = -1;
		}

		

		for(int i = 0; i < n; i++){
			if(arrival[i] == -1){
				dfs(graph, i);
				biCount++;
					//print biconnected component
				System.out.println("Printing last biconnected component. Printing biconnected component no. " + biCount);
				StringBuilder s = new StringBuilder();
				while(!edgeStack.isEmpty()){
					s.append(edgeStack.pop().print() + " ");	
				}
				System.out.println(s);
			}
		}
	}


	private void dfs(Graph<Integer,?> graph, Integer source){

		int children = 0;
		time++;
		arrival[source] = time;
		earliestLink[source] = arrival[source];
		for(Integer neighbour: graph.getNeighbours(source)){

			if(arrival[neighbour] == -1){
				//Pre-recusive calll processing. 
				
				edgeStack.push(new Edge<Integer>(source, neighbour));
				System.out.println("added to edgeStack:  " + source + " -> " + neighbour) ;
				children++;
				parent[neighbour] = source;
				//Recursive call. 
				dfs(graph, neighbour);

				// Post order processing. 
				earliestLink[source] = Math.min(earliestLink[source], earliestLink[neighbour]);
				// If subtree rooted at neighbour has no edge to a proper ancestor of source, then source is an articulation point (except for root which has no proper ancestor).
				if(earliestLink[neighbour] >= arrival[source] && parent[source] != -1){
					articulation[source] = true;
					biCount++;
					//print biconnected component
					System.out.println("Found biconnected component at articulation point: " + source + ". Printing biconnected component no. " + biCount);
					StringBuilder s = new StringBuilder();
					Edge<Integer> edge = edgeStack.peek();
					while(!(edge.contains(source) && edge.contains(neighbour))){
						s.append(edgeStack.pop().print() + " ");
						edge = edgeStack.peek();
					}
					s.append(edgeStack.pop().print());
					System.out.println(s);

				} else if(parent[source] == -1 && children > 1){
						articulation[source] = true;
						biCount++;
						//print biconnected component
						System.out.println("Found biconnected component at articulation point: " + source + "  Printing biconnected component no. " + biCount);
						StringBuilder s = new StringBuilder();
						Edge<Integer> edge = edgeStack.peek();
						while(!(edge.contains(source) && edge.contains(neighbour))){
							s.append(edgeStack.pop().print() + " ");
							edge = edgeStack.peek();
						}
						s.append(edgeStack.pop().print());
						System.out.println(s);
				}


			} else if(arrival[neighbour] != -1 && parent[source] != -1 && arrival[neighbour] < arrival[source] && neighbour != parent[source]){
				//  visited neighbours which are not parents and not forward edges. 
				System.out.println("added to edgeStack:  " + source + " -> " + neighbour) ;
				edgeStack.push(new Edge<Integer>(source, neighbour));
				earliestLink[source] = Math.min(earliestLink[source], arrival[neighbour]);
			}


								
				
			
		}


		if(earliestLink[source] >= arrival[source] && parent[source] != -1){
			Edge<Integer> edge = new Edge<Integer>(parent[source], source);
			System.out.println("Found bridge: " + parent[source] + " -> " + source );
			bridges.push(edge);
		}
	}




	public static void main(String[] args){
	  Graph<Integer, Edge<Integer>> testGraph = new ModernAdjacencyList<>();
      testGraph.addVertex(0);
      testGraph.addVertex(1);
      testGraph.addVertex(2);
      testGraph.addVertex(3);
      testGraph.addVertex(4);
      testGraph.addVertex(5);
      testGraph.addVertex(6);
      testGraph.addVertex(7);
      testGraph.addVertex(8);
      testGraph.addVertex(9);

      testGraph.addEdge(0, 1);
      testGraph.addEdge(0, 4);
      testGraph.addEdge(0, 9);
      testGraph.addEdge(0, 2);
      testGraph.addEdge(9, 8);
      testGraph.addEdge(8, 3);
      testGraph.addEdge(8, 7);
      testGraph.addEdge(3, 7);
      testGraph.addEdge(1, 6);
      testGraph.addEdge(4, 5);
      testGraph.addEdge(2, 3);

      // testGraph.addEdge(1, 0);
      // testGraph.addEdge(8, 5);
      // testGraph.addEdge(3, 6);
      // testGraph.addEdge(8, 9);
      // testGraph.addEdge(0, 5);
      // testGraph.addEdge(0, 4);
      // testGraph.addEdge(5, 4);
      // testGraph.addEdge(9, 4);
      // testGraph.addEdge(4, 7);
      System.out.println(testGraph.vertexCount());
      System.out.println(testGraph.edgeCount());
      System.out.println(testGraph.toString());
      Biconnected test = new Biconnected(testGraph);

	}





}