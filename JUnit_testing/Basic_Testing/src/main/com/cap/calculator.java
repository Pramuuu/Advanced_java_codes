package main.com.cap;

public class calculator {
	
	public int add(int a, int b) {
		return a+b;
	}
	public int Sub(int a, int b) {
		return a-b;
	}
	public boolean isEven(int number) {
		return number % 2==0;
	}
	public int divide(int a, int b) {
		return a/b;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		calculator c = new calculator();
		System.out.println(c.add(2, 3));
		System.out.println(c.Sub(2, 3));
		System.out.println(c.isEven(4));
		System.out.println(c.divide(4, 2));
		

	}

}