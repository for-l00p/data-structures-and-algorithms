package tests;

class Test {
	
	public static void changeBoolean(Boolean b){
		b = false;
	}

	public static void main(String[] args){
		Boolean x = true;
		changeBoolean(x);
		System.out.println(x);
	}
}