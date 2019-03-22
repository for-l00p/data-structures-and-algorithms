## Backtracking 



Backtracking can be applied only for problems which admit the concept of a "partial candidate solution" and a relatively quick test of whether it can possibly be completed to a valid solution.


Backtracking defines an implicit tree, whose nodes correspond to candidate solutions. The children of our node are the extensions. 


- P: Instance of the problem (information captured by original arguments)
- c belongs to solution space.
- root (P) -> c. Gives an initial candidate solution.
- canBeExtended (P,c) -> boolean
- isSolution (P,c) -> boolean
- processSolution (P,c) 
- generateFirstExtension (P,c) -> c
- next (P,c) -> (c)

Together, the root, first, and next functions define the set of partial candidates and the potential search tree. They should be chosen so that every solution of P occurs somewhere in the tree, and no partial candidate occurs more than once. 



~~~~java
candidate <- root(P);

bt(candidate){

  if canBeExtended(P, candidate) is false then return
  if isSolution(P, candidate) then process(P,candidate)
  nextCandidate ← generateFirstExtension(P,candidate)
  while nextCandidate ≠ NULL do
    bt(nextCandidate)
    nextCandidate ← next(P,nextCandidate)

}

~~~~




Early stopping variant:

~~~~
If you are already at a solution, report success.
for (every possible choice in the current position) {
	Make that choice and take one step along the path.
	Use recursion to solve the problem from the new position.
	If the recursive call succeeds, report the success to the next higher level.
	Back out of the current choice to restore the state at the beginning of the loop.
}
Report failure
~~~~




~~~~java
candidate <- root(P);
boolean bt(candidate)  
if canBeExtended(P, candidate) is false then return;
if isSolution(P, candidate) then process(P,candidate); report success;
nextCandidate ← generateFirstExtension(P,candidate)
  while nextCandidate ≠ NULL do
    successReported <- bt(nextCandidate)
    if (successReported) return true
    nextCandidate ← next(P,nextCandidate)
report failure
~~~~


###Examples


DFS Pre-Order

~~~~
canBeExtended(P, candidate) = true;
isSolution(P, candidate) = true; 
process = System.out.println(candidate)

DFS InOrder: Move process in between processing extensions. 
DFS Post-Order: Move process AFTER processing the extensions.
~~~~




> The difference between backtracking and DFS is that backtracking handles an implicit tree and DFS deals with an explicit one. When the search space of a problem is visited by backtracking, the implicit tree gets traversed and pruned in the middle of it. Yet for DFS, the tree/graph it deals with is explicitly constructed and unacceptable cases have already been thrown, i.e. pruned, away before any search is done. So it is a variant of depth-first traversal in which nodes are only expanded if they  are "promising" in the sense of being feasible precursors to a solution


MazePathFinding

~~~~java
DFS(grid, source, desination, visited)
	if (sourceIsAWall(grid, source)) return false;
	if (source.equals(destination)){
		return true;
	} 
	visited[source] = true;

for (Point neighbour: getNeighbours(grid, source)){
	if(visited[neighbour] == false){
		boolean checkThisBranch = DFS(grid, neighbour, destination, visited);
		if (checkThisBranch) {
			return true;
		}
	}
}
return false;
~~~~


Magic Square


N Queens


Permutatons


Subsequences










[^wiki]: https://en.wikipedia.org/wiki/Backtracking