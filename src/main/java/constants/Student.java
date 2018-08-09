package constants;

public class Student {
	private String first_name;
	private static Integer abc=10;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(String first_name) {
		super();
		this.first_name = first_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	
	public static void main(String[] args) {
		Student s = new Student("riya");
		Student s2 = new Student("riyo");
		System.out.println(s.abc);
		System.out.println(s2.abc);
		s2.abc=12;
		System.out.println(s.abc);
		System.out.println(s2.abc);
		
	
	}
}
