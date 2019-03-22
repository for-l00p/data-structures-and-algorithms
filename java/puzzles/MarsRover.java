package puzzles;

import java.util.*;

// Can i make this class private? Package private?
public class MarsRover {
	

	private static enum Direction {

		NORTH('N'),
		EAST('E') ,
		SOUTH('S'),
		WEST('W');

		private char letter;

		// Can I make this public?
		private Direction (char letter){
			this.letter = letter;

		}

		private static Direction[] directions = values();
		
		//What happens if I put this inside object
		private static Map<Character, Direction> charMap = new HashMap<>();

		static {
			for(Direction d: directions){
				charMap.put(d.toChar(), d);	
			}
		}

		private static Direction of(char c){
			return charMap.get(c);
		}

/*
		Alternative:

		private static Direction of(char c){
			Direction result = null;
			switch(c){
				case 'N': 
					result = NORTH;
					break;
				case 'S': 
					result = SOUTH;
					break;
				case 'E': 
					result = EAST;
					break;
				case 'W': 
					result = WEST;
					break;
			}
			return result;
		}*/

	

		private char toChar(){
			return this.letter;
		}

		private Direction next(){
			return directions[(this.ordinal()+1) % directions.length];
		}

		private Direction previous(){
			//System.out.println("Next index: " +(this.ordinal() + directions.length - 1));
			return directions[(this.ordinal() + directions.length - 1) % directions.length];
		}

		
	}


	private static class Rover {

		private Direction direction;
		private int x;
		private int y;

		private Rover(String roverInfo){
			x = Character.getNumericValue(roverInfo.charAt(0));
			y = Character.getNumericValue(roverInfo.charAt(1));
			direction = Direction.of(roverInfo.charAt(2));
			direction.toString();
		}


		private String getPosition(){
			StringBuilder s = new StringBuilder();
			s.append(x);
			s.append(y);
			s.append(direction.toChar());
			return String.valueOf(s);
		}

		private void move(){
			if(direction == Direction.NORTH){
				y++;
			}

			if(direction == Direction.EAST){
				x++;
			}

			if(direction == Direction.SOUTH){
				y--;
			}

			if(direction == Direction.WEST){
				x--;
			}
		}
		
		private void turnLeft(){
			direction = direction.previous();
		}

		private void turnRight(){
			direction = direction.next();
		}

		private void execute(String instructions){
			for(int i = 0; i < instructions.length(); i++){
				execute(instructions.charAt(i));
			}
		}

		private void execute(char c){
			if (c != 'L' && c!= 'R' && c != 'M') throw new IllegalArgumentException("Instruction to change direction must be either left or right");

			if(c == 'L'){
				turnLeft();
			}

			if (c == 'R'){
				turnRight();
			}

			if (c == 'M'){
				move();
			}
		}
	}



	public static String[] rove(String[] input){

		String dimensions = input[0];
		int numRows = Character.getNumericValue(dimensions.charAt(0));
		int numColumn = Character.getNumericValue(dimensions.charAt(1));
		int[][] grid = new int[numRows][numColumn];
		int i = 1;
		List<Rover> rovers = new ArrayList<Rover>();
		Rover currentRover;
		while(i < input.length){
			String roverInfo = input[i];
			currentRover = new Rover(roverInfo);
			currentRover.execute(input[i+1]);
			rovers.add(currentRover);
			i = i + 2;
		}

		String[] result = new String[rovers.size()];
		for(i = 0; i < rovers.size(); i++){
			result[i] = rovers.get(i).getPosition();
		}
		return result;
	}

	public static void main(String[] args){
		//System.out.println(Direction.of('N'));
		System.out.println(Direction.NORTH.toChar());


	}


}