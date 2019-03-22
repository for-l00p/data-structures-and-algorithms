import java.text.MessageFormat;

class Outcome {

	private String name;
	private int odds;
	
	public Outcome (String name, int odds){
		 this.name = name;
		 this.odds = odds;
	}


	public String getName(){
		return this.name;
	}


	public int winAmount (int amount){
	 	return amount*odds;
	}

	@Override
	public boolean equals(Outcome other){
		return other.name == name;
	}

	@Override
	public String toString(){
		Object[] values = {name, new Integer(odds)};
		String messageTemplate = "{0} ({1}:1)";
		return MessageFormat.format(messageTemplate, values );
	}


}