class Board {
	Cell[][] squares = new Cell[8][8];

	public Board(){
		for (int i = 0; i < 8; i++ ){
			for (int j = 0; j < 8; j++){
				if (i & 1 == j & 1){
					squares[i][j].colour = Colour.WHITE;
				} else {
					squares[i][j].colour = Colour.BLACK;
				}			
			}
		}
	}
}