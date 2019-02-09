public class APIGraph{
	
	
	private APIGraph(){
	}
	


	public static void main(String[] args){

		ModernAdjacencyList<String> apiGraph = new ModernAdjacencyList<>();
		apiGraph.addVertex("/onboarding/facebook");
		apiGraph.addVertex("onboarding.loginViaFb");
		apiGraph.addEdge("/onboarding/facebook", "onboarding.loginViaFb");
		apiGraph.addVertex("auth.authenticate");
		apiGraph.addVertex("onboarding.loginViaToken");

		System.out.println(apiGraph.getVertices());
		System.out.println(apiGraph.toString());


	}
}