package main.com.cap;

public class StudentService {
	public boolean isEligible(int age) {
		if(age<0) {
			throw new IllegalArgumentException("Age connot be negative");
		}
		return age>=18;
	}

}
