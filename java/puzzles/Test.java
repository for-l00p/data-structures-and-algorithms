class Test {
	
	static int x = 0;

	static class Foo {
		int z = 10;

		public int get(){
			return z;
		}
	}


	public static void main(String[] args){
		Test y = new Test();
		Test t = new Test();
		System.out.println(t.Foo().get());
		//Test.Foo k = y.Foo;
	}

}