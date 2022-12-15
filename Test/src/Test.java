
class A {

	private String name;
	private int age;

	public A(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}
}

public class Test {

	static double squareNumber(int i) {
		double square = Math.pow(i, i);
		return square;
	}

	public static void main(String[] args) {

		A a = new A("Pranay", 29);
		System.out.println(a.getName() + " " + a.getAge());

	}

}
