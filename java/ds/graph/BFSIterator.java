import java.util.*;


class BFSIterator implements Iterator<Integer> {
	
	private final Queue<Integer> bfsQueue;
	IntGraph g;
	int[] visited;
	int[] parent;
	int[] distance;

	public BFSIterator (IntGraph g, int source){

		this.g = g;
		this.bfsQueue = new LinkedList<>();
		bfsQueue.add(source);
		int n = g.numVertices();
		visited = new int[n];
		distance = new int[n];
		parent = new int[n];

		for (int i = 0; i < n; i++){
			distance[i] = -1;
			parent[i] = -1;
		}
		distance[source] = 0;

	}


	public boolean hasNext(){
		return !bfsQueue.isEmpty();
	}

	public Integer next(){

		int current = bfsQueue.poll();
		visited[current] = 2;

		for (int neighbour: g.adj(current)){
			if(visited[neighbour] == 0){
				visited[neighbour] = 1;
				bfsQueue.add(neighbour);
				distance[neighbour] = distance[current] + 1;
			}
		}

		if (bfsQueue.isEmpty()){
			for (int i = 0; i < visited.length; i++){
				if(visited[i] == 0){
					bfsQueue.add(i);
					break;
				}
			}
		}
		return current;
	}

	


}