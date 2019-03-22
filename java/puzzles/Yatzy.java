package puzzles;


import java.util.*;

public enum Yatzy {

		ONES("ones"){
			@Override
			int calculate(int[] input){
				return sumElementInArray(input, 1);
			}
		},
		TWOS("twos"){
			@Override
			int calculate(int[] input){
				return sumElementInArray(input, 2);
			}
		},
		THREES("threes"){
			@Override
			int calculate(int[] input){
				return sumElementInArray(input, 3);
			}
		},
		FOURS("fours"){
			@Override
			int calculate(int[] input){
				return sumElementInArray(input, 4);
			}
		},
		FIVES("fives"){
			@Override
			int calculate(int[] input){
				return sumElementInArray(input, 5);
			}
		},
		SIXES("sixes"){
			@Override
			int calculate(int[] input){
				return sumElementInArray(input, 6);
			}
		},
		CHANCE("chance"){
			@Override
			int calculate(int[] input){
				int answer = 0;
				for (int i = 0; i < input.length; i++){
					answer = answer + input[i];
				}
				return answer;
			}
		},
		YATZY("yatzy"){
			@Override
			int calculate(int[] input){
				int check = input[0];
				for (int i = 0; i < input.length; i++){
					if(input[i] != check) return 0;
				}
				return 50;
			}
		},
		TWO_OF_A_KIND("pair"){
			@Override
			int calculate(int[] input){
				Map<Integer, Integer> freqMap = getFrequencyMap(input);

				int pairValue = 0;
				for(Map.Entry entry: freqMap.entrySet()){
					if(entry.getValue().equals(2)){
						pairValue = Math.max(pairValue, (int)entry.getKey());
					}
				}
				return 2*pairValue;
			}
		},

		TWO_PAIR("two pairs"){

			@Override
			int calculate(int[] input){
				Map<Integer, Integer> freqMap = getFrequencyMap(input);

				int count = 0;

				int sum = 0;
				for(Map.Entry entry: freqMap.entrySet()){
					if(entry.getValue().equals(2)){
						sum = sum + 2*(int)entry.getKey();
						count++;
					}
					
				}
				if (count != 2) return 0;
				return sum;
			}

		},
		THREE_OF_A_KIND("three of a kind"){
			@Override
			int calculate(int[] input){
				Map<Integer, Integer> freqMap = getFrequencyMap(input);

				int tripsValue = 0;
				for(Map.Entry entry: freqMap.entrySet()){
					if(entry.getValue().equals(3)){
						tripsValue = (int) entry.getKey();
						break;
					}
				}
				return 3*(int)tripsValue;
			}
		},
		FOUR_OF_A_KIND("four of a kind"){
			@Override
			int calculate(int[] input){
				Map<Integer, Integer> freqMap = getFrequencyMap(input);
				int quadValue = 0;
				for(Map.Entry entry: freqMap.entrySet()){
					if(entry.getValue().equals(4)){
						quadValue = (int) entry.getKey();
						break;
					}
				}
				return (4*(int)quadValue);
			}
		},

		SMALL_STRAIGHT("small straight"){
			@Override
			int calculate(int[] input){
				Arrays.sort(input);
				for(int i = 0; i < input.length; i++){
					if(input[i] != i+1) return 0;
				}

				return 15;
			}
				
		},
		LARGE_STRAIGHT("large straight"){
			@Override
			int calculate(int[] input){
				Arrays.sort(input);
				for(int i = 0; i < input.length; i++){
					if(input[i] != i+2) return 0;
				}
				return 20;
			}
		},
		FULL_HOUSE("full house"){
			@Override
			int calculate(int[] input){
				Map<Integer, Integer> freqMap = getFrequencyMap(input);
				
				
				if (!freqMap.containsValue(2) || !freqMap.containsValue(3)){
					return 0;
				} 

				int sum = 0;

				for(Map.Entry entry: freqMap.entrySet()){
					if(entry.getValue().equals(2) || entry.getValue().equals(3)){
						sum = sum + (int)entry.getValue()*(int)entry.getKey();
					}
				}
				return sum;
			}
				
		};

		String name;

		private Yatzy(String name){
			this.name = name;
		}


		abstract int calculate(int[] input);


		public static int calc(int[] input, String name){
			Yatzy d = map.get(name);
			return d.calculate(input);
		}
		static Map<String, Yatzy> map = new HashMap<>();

		static {
			for(Yatzy d: values()){
				map.put(d.name, d);
			}
		}

		// static Directory of(String s){
		// 	return map.get(s);
		// }

		static Map<Integer, Integer> getFrequencyMap(int[] input){
			Map<Integer, Integer> freqMap = new HashMap<>();

			for (int i = 0; i < input.length; i++){
				Integer key = input[i];
				if(freqMap.containsKey(key)){
					Integer value = freqMap.get(key);
					freqMap.put(key, value + 1);
				} else {
					freqMap.put(key, 1);
				}
			}
			
			return freqMap;
		}

		static int sumElementInArray(int[] input, int element){
			int answer = 0;
			for (int i = 0; i < input.length; i++){
				if(input[i] == element){
					answer = answer + element;
				}	
			}
			return answer;
		}

}



