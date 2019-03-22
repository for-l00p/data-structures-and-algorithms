class Bin {
	
	private Set<Outcome> outcomes;


	public Bin() {

	}

	public Bin(Outcome[] outcomes){
		this.outcomes = new TreeSet<>(outcomes);
	}

	public Set<Outcome> getOutcomes() {
		this.outcomes = new TreeSet<>();
	}

	public void add(Outcome outcome){
		this.outcomes.add(outcome);
	}


	@Override
	public String toString(){

	}

}