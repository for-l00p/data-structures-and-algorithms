

class Frame {
	
	int score;
	private int givesBonus;
	private int[] rolls;
	private boolean isBonus;
	private boolean isStrike; //Calculatng at construction time and caching it. Once a frame s created, this is never changed. 
	boolean isSpare = false;
	


	public Frame (char[] chars, boolean isBonus) {
		if (chars.length > 2) throw new Error("Frame has more than two rolls");
		this.isBonus = isBonus;
		this.rolls = new int[chars.length];
		for (int i = 0; i < chars.length; i++){
			Character c = chars[i];
			if (c.equals('X')){
				this.score = this.score + 10;
				rolls[i] = 10;
				if (!isBonus){
					this.givesBonus = 2;
					this.isStrike = true;
				}
				continue;
				
			}

			if(c.equals('/')){
				this.score = 10;
				if (!isBonus){
					this.givesBonus = 1;
					this.isSpare = true;
				}
				rolls[i] = 10 - rolls[0];
				continue;
				
			}

			if (Character.isDigit(c)){
				int value = Character.getNumericValue(c);
				this.score = this.score + value;
				rolls[i] = value;
			}
		}

	}

	public int bonus(){
		return givesBonus;
	}


	public int getFirstRollValue(){
		return rolls[0];
	}

	public  boolean isStrike(){
		return isStrike ;
	}

	public  boolean isSpare(){
		return isSpare ;
	}


	

}

