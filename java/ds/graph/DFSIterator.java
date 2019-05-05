import java.util.Stack;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

class DFSIterator implements Iterator<Integer> {
	
	Stack<Integer> stack;
	int[] visited;
	List<Iterator<Integer>> adjListIterators;
	IntGraph g;
	Iterator<Integer> it;

	public DFSIterator(IntGraph g, int s){

		stack = new Stack<>();
		
		int n = g.numVertices;
		visited = new int[n];
		this.g = g;
		this.adjListIterators = new ArrayList<>();
		for (int i = 0; i < n; i++){
			adjListIterators.add(g.adj(i).iterator());
		}
		it = adjListIterators.get(s);
		stack.push(s);

	}


	public boolean hasNext(){
		return !stack.isEmpty();

	}


	public Integer next(){

		
		Integer v = stack.peek();
		//if (visited[v] == 1) throw new AssertionError("Alrady visited at top");
		visited[v] = 1;
		Integer next = v;
		while(visited[next] != 0){
			it = adjListIterators.get(v);
			if (it.hasNext()){
				next = it.next();
			} else {
				stack.pop();
				if (!stack.isEmpty()){
					next = stack.peek();
				} else {
					break;
				}
			}		
			
		}
		// visited[next] == 0 or stack became empty
		if (!stack.isEmpty()){
			stack.push(next);
		} else {
			for (int i = 0; i < visited.length; i++){
				if(visited[i] == 0){
					stack.push(i);
					break;
				}
			}
		}

		return v;
		

	}


}

