package puzzles;

class SaddlePoint {
	
	private static void find(int[][] input){
	
		boolean hasSaddle = false;

		for (int i = 0; i < input.length; i++){
			int columnOfMax = findColumnOfMax(input, i);
			int rowOfMin = findRowOfMin(input, columnOfMax);
			if (rowOfMin == i){
				System.out.println("saddle point found!: " + input[i][columnOfMax]);
				hasSaddle = true;
			}
		}
		if (hasSaddle == false){
			System.out.println("No saddle point found!");
		}
	}

	private static int findColumnOfMax(int[][] input, int row){
		int columnOfMax = 0;
		for (int j = 0; j < input[0].length;j++){
			if(input[row][j] > input[row][columnOfMax]){
				columnOfMax = j;
			}			
		}
		return columnOfMax;

	}

	private static int findRowOfMin(int[][] input, int column){
		int rowOfMin = 0;
		for (int i = 0; i < input.length; i++){
			if(input[i][column] < input[rowOfMin][column]){
				rowOfMin = i;
			}			
		}
		return rowOfMin;

	}

	

	public static void main(String[] args){

		int[] row0 = new int[]{1, 4, 7};
		int[] row1 = new int[]{2, 5, 18};
		int[] row2 = new int[]{3, 6, 9};
		int[][] input = {row0, row1, row2};
		find(input);

		row0 = new int[]{1, 4, 10};
		row1 = new int[]{2, 5, 18};
		row2 = new int[]{3, 6, 4};
		input = new int[][]{row0, row1, row2};
		find(input);

	
	}
}