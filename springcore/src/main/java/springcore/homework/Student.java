package springcore.homework;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {
	@Autowired
	private String[] favoriteColor;
	@Autowired
	private List<Subject> subjects;
	
	public void Display() {
		System.out.println("Is is Studnet Bean");
		System.out.println("My Favorite Colors Is : ");
		Arrays.stream(favoriteColor).forEach(System.out::println);
	}
	public void DisplaySubjects() {
		subjects.stream().map(x -> x.toString())
				.forEach(System.out::print);
	}
}
