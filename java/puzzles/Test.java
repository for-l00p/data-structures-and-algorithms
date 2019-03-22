package puzzles;

public class Test {
	
	static int x = 0;

	static class Foo {
		int z = 10;

		public int get(){
			return z;
		}
	}

	public static void method(){
		int a = 5;
		int b;
		if (a > 10){
  			  b = 2;
		} else {
   			 // b = 4;
		}
		b *= 3;

	}


	public static void main(String[] args){
		Test y = new Test();
		Test t = new Test();
		//System.out.println(t.Foo().get());
		//Test.Foo k = y.Foo;
		StringBuilder test = new StringBuilder("[)");
		StringBuilder prev = new StringBuilder(test);
		System.out.println(test.equals(prev));
	}

}