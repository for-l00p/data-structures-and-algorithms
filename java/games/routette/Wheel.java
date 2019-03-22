import java.util.Random;

class Wheel {
	
	List<Bin> bins;
	Random rng;
	

	public Wheel(Random rng){
		this.rng = rng;
		this.bins = new ArrayList<>(38);


	}


	public Bin pickWiningBin() {
		int index = Random.nextInt(bins.size());
		return bins.get(index);
	}

}