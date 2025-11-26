package springcore.homework;

import org.springframework.stereotype.Component;

@Component
public class Subject {
	private String title;
	public Subject() {};
	public Subject(String title) {
		this.title = title;
	}
	public String toString() {
		return String.format("This is %s Subject \n",title);
	}
}
