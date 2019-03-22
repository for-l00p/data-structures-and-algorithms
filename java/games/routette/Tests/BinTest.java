class BinTest {

	@Test
	public void binShouldBeCreatedWithOutcomes(){
		Outcome five = new Outcome("00-0-1-2-3", 6);
		Outcome[] zeroOutcomes = {new Outcome("0", 35), five};
		//Outcome[] zerozeroOutcomes = {new Outcome("00", 35,), five};
		Bin zero = new Bin(zeroOutcomes);
		Bin zerozero = new Bin(zerozeroOutcomes);
		//How to test?
	}

}