/*
https://en.wikipedia.org/wiki/Josephus_problem
Can be solved by recursion for k = 2, and by dynamic programming in the general case. 


 */

public class Josephus{

	private static class Gunman{

		private int number;
		Gunman next;

		public Gunman(int i){
			this.number = i;
			this.next = null;
		}

		public int getNumber(){
			return number;
		}
	}


	public static void main(String[] args){
		
		int count = 1;
		Gunman firstGunman = new Gunman(1);
		Gunman currentGunman = firstGunman;
		for(int i = 2; i <= 100; i++){
			currentGunman.next = new Gunman(i);
			currentGunman = currentGunman.next;
			count++;
		}
		currentGunman.next = firstGunman;

		currentGunman = currentGunman.next;

		while(count > 1 && currentGunman.next != null){
			currentGunman.next = currentGunman.next.next;
			currentGunman = currentGunman.next;
			count--;
		}
		System.out.println("Answer: " + currentGunman.number + " Count: " + count);
	
	}


}


