package components;

class A {
	   int a = 9;
	}

	class B extends A {
	   int b = 4;
	}

	public class Demo {
	   public static void main(String args[]) {
	      B obj = new B();
	      Noeud ref1= new Noeud();
	      System.out.println("Value of a is: " + obj.a);
	      System.out.println("Value of b is: " + obj.b);
	   }
	}