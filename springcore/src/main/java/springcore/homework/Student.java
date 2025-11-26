package springcore.homework;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {
	@Autowired
	private List<Subject> subjects;
	
	public void Display() {
		System.out.println("Is is Studnet Bean");
	}
	public void DisplaySubjects() {
		subjects.stream().map(x -> x.toString())
				.forEach(System.out::print);
	}
}
